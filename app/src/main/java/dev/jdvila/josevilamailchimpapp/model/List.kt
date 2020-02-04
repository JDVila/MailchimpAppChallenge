package dev.jdvila.josevilamailchimpapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import java.io.Serializable

class List : Serializable {

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("web_id")
    @Expose
    var webId: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("contact")
    @Expose
    var contact: Contact? = null
    @SerializedName("permission_reminder")
    @Expose
    var permissionReminder: String? = null
    @SerializedName("use_archive_bar")
    @Expose
    var useArchiveBar: Boolean? = null
    @SerializedName("campaign_defaults")
    @Expose
    var campaignDefaults: CampaignDefaults? = null
    @SerializedName("notify_on_subscribe")
    @Expose
    var notifyOnSubscribe: String? = null
    @SerializedName("notify_on_unsubscribe")
    @Expose
    var notifyOnUnsubscribe: String? = null
    @SerializedName("date_created")
    @Expose
    var dateCreated: String? = null
    @SerializedName("list_rating")
    @Expose
    var listRating: Int? = null
    @SerializedName("email_type_option")
    @Expose
    var emailTypeOption: Boolean? = null
    @SerializedName("subscribe_url_short")
    @Expose
    var subscribeUrlShort: String? = null
    @SerializedName("subscribe_url_long")
    @Expose
    var subscribeUrlLong: String? = null
    @SerializedName("beamer_address")
    @Expose
    var beamerAddress: String? = null
    @SerializedName("visibility")
    @Expose
    var visibility: String? = null
    @SerializedName("double_optin")
    @Expose
    var doubleOptin: Boolean? = null
    @SerializedName("has_welcome")
    @Expose
    var hasWelcome: Boolean? = null
    @SerializedName("marketing_permissions")
    @Expose
    var marketingPermissions: Boolean? = null
    @SerializedName("modules")
    @Expose
    var modules: kotlin.collections.List<List>? = null
    @SerializedName("stats")
    @Expose
    var stats: Stats? = null
    @SerializedName("_links")
    @Expose
    var links: kotlin.collections.List<Link>? = null

}
