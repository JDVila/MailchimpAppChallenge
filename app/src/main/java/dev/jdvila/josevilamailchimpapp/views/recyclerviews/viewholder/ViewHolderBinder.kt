package dev.jdvila.josevilamailchimpapp.views.recyclerviews.viewholder

import dev.jdvila.josevilamailchimpapp.views.ListItem
import dev.jdvila.josevilamailchimpapp.views.recyclerviews.adapter.MembersListRecyclerViewAdapter

interface ViewHolderBinder {
    fun onBind(listItem: ListItem, onMemberItemViewListener: MembersListRecyclerViewAdapter.OnMemberItemViewListener)
}
