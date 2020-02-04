package dev.jdvila.josevilamailchimpapp.views.recyclerviews.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import dev.jdvila.josevilamailchimpapp.R;
import dev.jdvila.josevilamailchimpapp.views.ListItem;
import dev.jdvila.josevilamailchimpapp.views.TitleListItem;
import dev.jdvila.josevilamailchimpapp.views.recyclerviews.adapter.MembersListRecyclerViewAdapter;

public class MembersListRecyclerViewTitleViewHolder extends MembersListRecyclerViewViewHolder {
    private TextView titleTextView;

    public MembersListRecyclerViewTitleViewHolder(@NonNull View itemView) {
        super(itemView);
        titleTextView = itemView.findViewById(R.id.titleTextView);
    }

    @Override
    public void onBind(@NotNull ListItem listItem, @NotNull MembersListRecyclerViewAdapter.OnMemberItemViewListener onMemberItemViewListener) {
        String title = ((TitleListItem) listItem).getTitle();
        titleTextView.setText(title);
    }
}
