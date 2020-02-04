package dev.jdvila.josevilamailchimpapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import java.io.Serializable

class Link_ : Serializable {

    @SerializedName("rel")
    @Expose
    var rel: String? = null
    @SerializedName("href")
    @Expose
    var href: String? = null
    @SerializedName("method")
    @Expose
    var method: String? = null
    @SerializedName("targetSchema")
    @Expose
    var targetSchema: String? = null
    @SerializedName("schema")
    @Expose
    var schema: String? = null

}