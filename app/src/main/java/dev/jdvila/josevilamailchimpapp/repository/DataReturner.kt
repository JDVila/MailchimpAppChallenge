package dev.jdvila.josevilamailchimpapp.repository

import dev.jdvila.josevilamailchimpapp.model.Member

interface DataReturner {

    fun returnData(dataMap: Map<String, List<Member>>)
}
