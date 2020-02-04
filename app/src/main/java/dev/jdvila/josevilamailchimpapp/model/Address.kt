package dev.jdvila.josevilamailchimpapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import java.io.Serializable

class Address : Serializable {

    @SerializedName("addr1")
    @Expose
    var addr1: String? = null
    @SerializedName("addr2")
    @Expose
    var addr2: String? = null
    @SerializedName("city")
    @Expose
    var city: String? = null
    @SerializedName("state")
    @Expose
    var state: String? = null
    @SerializedName("zip")
    @Expose
    var zip: String? = null
    @SerializedName("country")
    @Expose
    var country: String? = null
}
