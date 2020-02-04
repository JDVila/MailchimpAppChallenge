package dev.jdvila.josevilamailchimpapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import java.io.Serializable

class Constraints : Serializable {

    @SerializedName("may_create")
    @Expose
    var mayCreate: Boolean? = null
    @SerializedName("max_instances")
    @Expose
    var maxInstances: Int? = null
    @SerializedName("current_total_instances")
    @Expose
    var currentTotalInstances: Int? = null

}
