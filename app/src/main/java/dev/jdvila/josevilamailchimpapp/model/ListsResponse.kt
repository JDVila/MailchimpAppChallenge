package dev.jdvila.josevilamailchimpapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import java.io.Serializable
import kotlin.collections.List

class ListsResponse : Serializable {

    @SerializedName("lists")
    @Expose
    var lists: List<dev.jdvila.josevilamailchimpapp.model.List>? = null
    @SerializedName("total_items")
    @Expose
    var totalItems: Int? = null
    @SerializedName("constraints")
    @Expose
    var constraints: Constraints? = null
    @SerializedName("_links")
    @Expose
    var links: List<Link_>? = null

}
