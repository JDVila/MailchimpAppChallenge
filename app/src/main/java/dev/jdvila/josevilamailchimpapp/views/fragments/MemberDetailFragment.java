package dev.jdvila.josevilamailchimpapp.views.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatToggleButton;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import androidx.fragment.app.Fragment;

import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import dev.jdvila.josevilamailchimpapp.R;
import dev.jdvila.josevilamailchimpapp.model.Member;

public class MemberDetailFragment extends Fragment {
    private static final String ARG_PARAM1 = "member";
    private static final String ARG_PARAM2 = "bitmap";

    private Member member;
    private Bitmap bitmap;

    private OnFragmentMemberEditListener OnFragmentMemberEditListener;

    public MemberDetailFragment() {
    }

    public static MemberDetailFragment newInstance(Member member, Bitmap bitmap) {
        MemberDetailFragment fragment = new MemberDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, member);
        args.putParcelable(ARG_PARAM2, bitmap);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            member = (Member) getArguments().getSerializable(ARG_PARAM1);
            bitmap = getArguments().getParcelable(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_member_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final ImageView profileImageView = view.findViewById(R.id.memberDetailImageView);
        final EditText firstNameEditText = view.findViewById(R.id.memberDetailFirstNameEditText);
        final EditText lastNameEditText = view.findViewById(R.id.memberDetailLastNameEditText);
        final EditText emailEditText = view.findViewById(R.id.memberDetailEmailEditText);
        Toolbar toolbar = view.findViewById(R.id.memberDetailToolbar);
        ConstraintLayout memberDetailsConstraintLayout = view.findViewById(R.id.memberDetailsConstraintLayout);
        AppCompatToggleButton appCompatToggleButton = view.findViewById(R.id.memberDetailEditSaveToggleButton);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AppCompatActivity)view.getContext()).onBackPressed();
            }
        });

        String fullName = member.getMergeFields().getfName() + " " + member.getMergeFields().getlName();
        toolbar.setTitle(fullName);

        Display display = view.getDisplay();
        Point size = new Point();
        display.getSize(size);
        int height = size.y;

        TypedValue tv = new TypedValue();
        int actionBarHeight = 0;
        if (getActivity().getTheme().resolveAttribute(R.attr.actionBarSize, tv, true))
        {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,getResources().getDisplayMetrics());
        }

        memberDetailsConstraintLayout.setLayoutParams(new CardView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (height-actionBarHeight)));
        profileImageView.setLayoutParams(new CollapsingToolbarLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height/3));

        profileImageView.setTransitionName(member.getId());

        profileImageView.setImageBitmap(bitmap);

        firstNameEditText.setText(member.getMergeFields().getfName());
        lastNameEditText.setText(member.getMergeFields().getlName());
        emailEditText.setText(member.getEmailAddress());

        appCompatToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    firstNameEditText.setEnabled(true);
                    firstNameEditText.getBackground().mutate().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
                    firstNameEditText.requestFocus();

                    lastNameEditText.setEnabled(true);
                    lastNameEditText.getBackground().mutate().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);

                    emailEditText.setEnabled(true);
                    emailEditText.getBackground().mutate().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
                } else {
                    firstNameEditText.setEnabled(false);
                    firstNameEditText.getBackground().mutate().setColorFilter(getResources().getColor(R.color.colorTransparent), PorterDuff.Mode.CLEAR);

                    lastNameEditText.setEnabled(false);
                    lastNameEditText.getBackground().mutate().setColorFilter(getResources().getColor(R.color.colorTransparent), PorterDuff.Mode.CLEAR);

                    emailEditText.setEnabled(false);
                    emailEditText.getBackground().mutate().setColorFilter(getResources().getColor(R.color.colorTransparent), PorterDuff.Mode.CLEAR);

                    onSavePressed(member.getListId(), member.getId(), firstNameEditText.getText().toString(), lastNameEditText.getText().toString(), emailEditText.getText().toString());
                }
            }
        });
    }

    private void onSavePressed(String listId, String memberId, String firstName, String lastName, String email) {
        if (OnFragmentMemberEditListener != null) {
            OnFragmentMemberEditListener.onFragmentMemberEdit(listId, memberId, firstName, lastName, email);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentMemberEditListener) {
            OnFragmentMemberEditListener = (OnFragmentMemberEditListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentMemberEditListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        OnFragmentMemberEditListener = null;
    }

    public interface OnFragmentMemberEditListener {
        void onFragmentMemberEdit(String listId, String memberId, String firstName, String lastName, String email);
    }
}
