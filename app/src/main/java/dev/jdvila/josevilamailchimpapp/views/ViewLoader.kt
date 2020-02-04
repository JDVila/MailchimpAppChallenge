package dev.jdvila.josevilamailchimpapp.views

import android.widget.ImageView
import dev.jdvila.josevilamailchimpapp.model.Member

interface ViewLoader {

    fun loadMainView(listItems: List<ListItem>)

    fun updateMainView(listItems: List<ListItem>)

    fun loadDetailView(member: Member, imageView: ImageView)

}
