package dev.jdvila.josevilamailchimpapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.transition.TransitionInflater;
import android.widget.ImageView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import dev.jdvila.josevilamailchimpapp.model.Member;
import dev.jdvila.josevilamailchimpapp.repository.MembersRepository;
import dev.jdvila.josevilamailchimpapp.repository.MembersRepositoryImpl;
import dev.jdvila.josevilamailchimpapp.views.DataReturnedListener;
import dev.jdvila.josevilamailchimpapp.views.DataUpdatedListener;
import dev.jdvila.josevilamailchimpapp.views.ListItem;
import dev.jdvila.josevilamailchimpapp.views.MemberListItem;
import dev.jdvila.josevilamailchimpapp.views.TitleListItem;
import dev.jdvila.josevilamailchimpapp.views.ViewLoader;
import dev.jdvila.josevilamailchimpapp.views.fragments.MemberDetailFragment;
import dev.jdvila.josevilamailchimpapp.views.fragments.MemberListFragment;

public class MainActivity extends AppCompatActivity implements ViewLoader, DataReturnedListener, DataUpdatedListener, MemberListFragment.OnListFragmentInteractionListener, MemberDetailFragment.OnFragmentMemberEditListener {

    private MembersRepository membersRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        membersRepository = new MembersRepositoryImpl();
        membersRepository.onAttach(this);
        membersRepository.getData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        membersRepository.onDetach();
    }

    @Override
    public void onDataReturned(@NotNull Map<String, ? extends List<Member>> dataMap) {
        List<ListItem> completeList = new ArrayList<>();
        for (String title : dataMap.keySet()) {
            TitleListItem titleListItem = new TitleListItem(title);
            completeList.add(titleListItem);

            for (Member member : Objects.requireNonNull(dataMap.get(title))) {
                MemberListItem memberListItem = new MemberListItem(member);
                completeList.add(memberListItem);
            }
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        if(null != fragmentManager.findFragmentByTag(getResources().getString(R.string.member_list_fragment_tag))) {
            updateMainView(completeList);
        } else {
            loadMainView(completeList);
        }
    }

    @Override
    public void loadDetailView(@NotNull Member member, ImageView imageView) {
       final Bitmap bitmap = ((RoundedBitmapDrawable) imageView.getDrawable()).getBitmap();
        MemberDetailFragment memberDetailFragment = MemberDetailFragment.newInstance(member, bitmap);

        memberDetailFragment.setSharedElementEnterTransition(TransitionInflater.from(
                this).inflateTransition(R.transition.hero));
        memberDetailFragment.setSharedElementReturnTransition(TransitionInflater.from(
                this).inflateTransition(R.transition.hero));

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setReorderingAllowed(false);
        fragmentTransaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
        fragmentTransaction.addSharedElement(imageView, member.getId());
        fragmentTransaction.replace(R.id.fragmentContainerFrameLayout, memberDetailFragment);
        fragmentTransaction.addToBackStack(getResources().getString(R.string.member_detail_fragment_tag));
        fragmentTransaction.commit();
    }

    @Override
    public void onListFragmentInteraction(@NonNull Member member, ImageView imageView) {
        loadDetailView(member, imageView);
    }

    @Override
    public void onFragmentMemberEdit(String listId, String memberId, String firstName, String lastName, String email) {
        membersRepository.updateData(listId, memberId, firstName, lastName, email);
    }

    @Override
    public void loadMainView(@NotNull List<? extends ListItem> listItems) {
        MemberListFragment memberListFragment = MemberListFragment.newInstance(listItems);

        memberListFragment.setSharedElementEnterTransition(TransitionInflater.from(
                this).inflateTransition(R.transition.hero));
        memberListFragment.setSharedElementReturnTransition(TransitionInflater.from(
                this).inflateTransition(R.transition.hero));
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
        fragmentTransaction.replace(R.id.fragmentContainerFrameLayout, memberListFragment, getResources().getString(R.string.member_list_fragment_tag));
        fragmentTransaction.commit();
    }

    @Override
    public void updateMainView(@NotNull List<? extends ListItem> listItems) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        MemberListFragment memberListFragment = (MemberListFragment) fragmentManager.findFragmentByTag(getResources().getString(R.string.member_list_fragment_tag));
        Objects.requireNonNull(memberListFragment).updateList(listItems);
    }

    @Override
    public void onDataUpdated(@NotNull String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
