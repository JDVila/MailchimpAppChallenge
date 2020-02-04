package dev.jdvila.josevilamailchimpapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import java.io.Serializable

class Location : Serializable {

    @SerializedName("latitude")
    @Expose
    var latitude: Int? = null
    @SerializedName("longitude")
    @Expose
    var longitude: Int? = null
    @SerializedName("gmtoff")
    @Expose
    var gmtoff: Int? = null
    @SerializedName("dstoff")
    @Expose
    var dstoff: Int? = null
    @SerializedName("country_code")
    @Expose
    var countryCode: String? = null
    @SerializedName("timezone")
    @Expose
    var timezone: String? = null

}
