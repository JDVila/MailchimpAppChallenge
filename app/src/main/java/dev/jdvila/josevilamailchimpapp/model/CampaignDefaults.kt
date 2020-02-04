package dev.jdvila.josevilamailchimpapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import java.io.Serializable

class CampaignDefaults : Serializable {

    @SerializedName("from_name")
    @Expose
    var fromName: String? = null
    @SerializedName("from_email")
    @Expose
    var fromEmail: String? = null
    @SerializedName("subject")
    @Expose
    var subject: String? = null
    @SerializedName("language")
    @Expose
    var language: String? = null

}
