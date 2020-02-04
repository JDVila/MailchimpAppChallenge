package dev.jdvila.josevilamailchimpapp.views.recyclerviews.adapter;

import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import dev.jdvila.josevilamailchimpapp.R;
import dev.jdvila.josevilamailchimpapp.model.Member;
import dev.jdvila.josevilamailchimpapp.views.ListItem;
import dev.jdvila.josevilamailchimpapp.views.recyclerviews.viewholder.MembersListRecyclerViewMemberViewHolder;
import dev.jdvila.josevilamailchimpapp.views.recyclerviews.viewholder.MembersListRecyclerViewTitleViewHolder;
import dev.jdvila.josevilamailchimpapp.views.recyclerviews.viewholder.MembersListRecyclerViewViewHolder;

import static dev.jdvila.josevilamailchimpapp.views.ListItem.MEMBER_ITEM;
import static dev.jdvila.josevilamailchimpapp.views.ListItem.TITLE_ITEM;

public class MembersListRecyclerViewAdapter extends RecyclerView.Adapter<MembersListRecyclerViewViewHolder> {

    private List<ListItem> memberList;
    private MembersListRecyclerViewAdapter.OnMemberItemViewListener onMemberItemViewListener;

    public MembersListRecyclerViewAdapter(List<ListItem> memberList, MembersListRecyclerViewAdapter.OnMemberItemViewListener onMemberItemViewListener) {
        this.memberList = memberList;
        this.onMemberItemViewListener = onMemberItemViewListener;
    }

    @NonNull
    @Override
    public MembersListRecyclerViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MembersListRecyclerViewViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case TITLE_ITEM:
                View titleItemView = inflater.inflate(R.layout.title_item_view, parent,
                        false);
                viewHolder = new MembersListRecyclerViewTitleViewHolder(titleItemView);
                break;
            case MEMBER_ITEM:
                View memberItemView = inflater.inflate(R.layout.member_item_view, parent, false);
                viewHolder = new MembersListRecyclerViewMemberViewHolder(memberItemView);
                break;
            default:
                throw new InflateException(((Fragment)onMemberItemViewListener).getContext().getResources().getString(R.string.invalid_list_item_view_type_error));
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MembersListRecyclerViewViewHolder holder, int position) {
        holder.onBind(memberList.get(position), onMemberItemViewListener);
    }

    @Override
    public int getItemCount() {
        return memberList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return memberList.get(position).getType();
    }

    public void updateRecyclerViewList(@NotNull List<? extends ListItem> newList) {
        memberList.clear();
        memberList.addAll(newList);
        notifyDataSetChanged();
    }

    public interface OnMemberItemViewListener {
        void OnMemberItemViewClick(Member member, ImageView imageView);
    }
}
