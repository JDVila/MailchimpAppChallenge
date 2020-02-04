package dev.jdvila.josevilamailchimpapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import java.io.Serializable

class MergeFields : Serializable {

    @SerializedName("FNAME")
    @Expose
    private var fName: String? = null
    @SerializedName("LNAME")
    @Expose
    private var lName: String? = null
    @SerializedName("ADDRESS")
    @Expose
    var address: Address? = null
    @SerializedName("PHONE")
    @Expose
    var phone: String? = null
    @SerializedName("COMPANY")
    @Expose
    var company: String? = null
    @SerializedName("CITY")
    @Expose
    var city: String? = null
    @SerializedName("COUNTRY")
    @Expose
    var country: String? = null
    @SerializedName("PHOTO")
    @Expose
    var photo: String? = null
    @SerializedName("BIRTHDAY")
    @Expose
    var birthday: String? = null
    @SerializedName("LANGUAGE")
    @Expose
    var language: String? = null

    fun getfName(): String? {
        return fName
    }

    fun setfName(fName: String) {
        this.fName = fName
    }

    fun getlName(): String? {
        return lName
    }

    fun setlName(lName: String) {
        this.lName = lName
    }
}