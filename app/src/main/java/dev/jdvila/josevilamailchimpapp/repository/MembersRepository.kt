package dev.jdvila.josevilamailchimpapp.repository

import android.content.Context

interface MembersRepository {

    fun updateData(listId: String, memberId: String, firstName: String, lastName: String, email: String)

    fun getData()

    fun onAttach(context: Context)

    fun onDetach()

}
