package dev.jdvila.josevilamailchimpapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import java.io.Serializable

class Stats : Serializable {

    @SerializedName("member_count")
    @Expose
    var memberCount: Int? = null
    @SerializedName("unsubscribe_count")
    @Expose
    var unsubscribeCount: Int? = null
    @SerializedName("cleaned_count")
    @Expose
    var cleanedCount: Int? = null
    @SerializedName("member_count_since_send")
    @Expose
    var memberCountSinceSend: Int? = null
    @SerializedName("unsubscribe_count_since_send")
    @Expose
    var unsubscribeCountSinceSend: Int? = null
    @SerializedName("cleaned_count_since_send")
    @Expose
    var cleanedCountSinceSend: Int? = null
    @SerializedName("campaign_count")
    @Expose
    var campaignCount: Int? = null
    @SerializedName("campaign_last_sent")
    @Expose
    var campaignLastSent: String? = null
    @SerializedName("merge_field_count")
    @Expose
    var mergeFieldCount: Int? = null
    @SerializedName("avg_sub_rate")
    @Expose
    var avgSubRate: Int? = null
    @SerializedName("avg_unsub_rate")
    @Expose
    var avgUnsubRate: Int? = null
    @SerializedName("target_sub_rate")
    @Expose
    var targetSubRate: Int? = null
    @SerializedName("open_rate")
    @Expose
    var openRate: Int? = null
    @SerializedName("click_rate")
    @Expose
    var clickRate: Int? = null
    @SerializedName("last_sub_date")
    @Expose
    var lastSubDate: String? = null
    @SerializedName("last_unsub_date")
    @Expose
    var lastUnsubDate: String? = null

}
