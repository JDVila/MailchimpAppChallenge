package dev.jdvila.josevilamailchimpapp.views.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.List;

import dev.jdvila.josevilamailchimpapp.R;
import dev.jdvila.josevilamailchimpapp.model.Member;
import dev.jdvila.josevilamailchimpapp.views.ListItem;
import dev.jdvila.josevilamailchimpapp.views.recyclerviews.adapter.MembersListRecyclerViewAdapter;

public class MemberListFragment extends Fragment implements MembersListRecyclerViewAdapter.OnMemberItemViewListener {
    private static final String ARG_PARAM1 = "memberList";

    private List<ListItem> memberList;

    private MembersListRecyclerViewAdapter adapter;

    private OnListFragmentInteractionListener onListFragmentInteractionListener;

    public MemberListFragment() {

    }

    public static MemberListFragment newInstance(@NotNull List<? extends ListItem> memberList) {
        MemberListFragment fragment = new MemberListFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, (Serializable) memberList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            memberList = (List<ListItem>) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_member_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView memberListRecyclerView = view.findViewById(R.id.listFragmentRecyclerView);
        adapter = new MembersListRecyclerViewAdapter(memberList, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        memberListRecyclerView.setAdapter(adapter);
        memberListRecyclerView.setLayoutManager(linearLayoutManager);
    }


    @Override
    public void OnMemberItemViewClick(Member member, ImageView imageView) {
        if (onListFragmentInteractionListener != null) {
            onListFragmentInteractionListener.onListFragmentInteraction(member, imageView);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            onListFragmentInteractionListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onListFragmentInteractionListener = null;
    }

    public void updateList(@NotNull List<? extends ListItem> newItemList) {
        adapter.updateRecyclerViewList(newItemList);
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(Member member, ImageView imageView);
    }
}
