package dev.jdvila.josevilamailchimpapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import java.io.Serializable

class Contact : Serializable {

    @SerializedName("company")
    @Expose
    var company: String? = null
    @SerializedName("address1")
    @Expose
    var address1: String? = null
    @SerializedName("address2")
    @Expose
    var address2: String? = null
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
    @SerializedName("phone")
    @Expose
    var phone: String? = null

}
