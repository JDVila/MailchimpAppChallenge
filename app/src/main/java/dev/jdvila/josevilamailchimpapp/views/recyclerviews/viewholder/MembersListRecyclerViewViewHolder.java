package dev.jdvila.josevilamailchimpapp.views.recyclerviews.viewholder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class MembersListRecyclerViewViewHolder extends RecyclerView.ViewHolder implements ViewHolderBinder {
    MembersListRecyclerViewViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}
