package dev.jdvila.josevilamailchimpapp.model

import java.io.Serializable

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlin.collections.List

class MembersResponse : Serializable {

    @SerializedName("members")
    @Expose
    var members: List<Member>? = null
    @SerializedName("list_id")
    @Expose
    var listId: String? = null
    @SerializedName("total_items")
    @Expose
    var totalItems: Int? = null
    @SerializedName("_links")
    @Expose
    var links: List<Link_>? = null

}
