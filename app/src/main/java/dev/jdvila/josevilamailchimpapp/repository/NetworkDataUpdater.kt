package dev.jdvila.josevilamailchimpapp.repository

interface NetworkDataUpdater {
    fun updateNetworkData(listId: String, memberId: String, firstName: String, lastName: String, email: String)
}
