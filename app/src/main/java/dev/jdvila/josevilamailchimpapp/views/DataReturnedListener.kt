package dev.jdvila.josevilamailchimpapp.views

import dev.jdvila.josevilamailchimpapp.model.Member

interface DataReturnedListener {

    fun onDataReturned(dataMap: Map<String, List<Member>>)
}
