package dev.jdvila.josevilamailchimpapp.model

import java.io.Serializable

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlin.collections.List

class Member : Serializable {

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("email_address")
    @Expose
    var emailAddress: String? = null
    @SerializedName("unique_email_id")
    @Expose
    var uniqueEmailId: String? = null
    @SerializedName("web_id")
    @Expose
    var webId: Int? = null
    @SerializedName("email_type")
    @Expose
    var emailType: String? = null
    @SerializedName("status")
    @Expose
    var status: String? = null
    @SerializedName("merge_fields")
    @Expose
    var mergeFields: MergeFields? = null
    @SerializedName("stats")
    @Expose
    var stats: Stats? = null
    @SerializedName("ip_signup")
    @Expose
    var ipSignup: String? = null
    @SerializedName("timestamp_signup")
    @Expose
    var timestampSignup: String? = null
    @SerializedName("ip_opt")
    @Expose
    var ipOpt: String? = null
    @SerializedName("timestamp_opt")
    @Expose
    var timestampOpt: String? = null
    @SerializedName("member_rating")
    @Expose
    var memberRating: Int? = null
    @SerializedName("last_changed")
    @Expose
    var lastChanged: String? = null
    @SerializedName("language")
    @Expose
    var language: String? = null
    @SerializedName("vip")
    @Expose
    var vip: Boolean? = null
    @SerializedName("email_client")
    @Expose
    var emailClient: String? = null
    @SerializedName("location")
    @Expose
    var location: Location? = null
    @SerializedName("source")
    @Expose
    var source: String? = null
    @SerializedName("tags_count")
    @Expose
    var tagsCount: Int? = null
    @SerializedName("tags")
    @Expose
    var tags: List<Tag>? = null
    @SerializedName("list_id")
    @Expose
    var listId: String? = null
    @SerializedName("_links")
    @Expose
    var links: List<Link>? = null
}
