package dev.jdvila.josevilamailchimpapp.network;

import dev.jdvila.josevilamailchimpapp.model.ListsResponse;
import dev.jdvila.josevilamailchimpapp.model.Member;
import dev.jdvila.josevilamailchimpapp.model.MembersResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

public interface MailchimpService {

    @GET("3.0/lists")
    Call<ListsResponse> getListsCall();

    @GET("3.0/lists/{list_id}/members")
    Call<MembersResponse> getListMembersCall(@Path("list_id") String listId);

    @PATCH("3.0/lists/{list_id}/members/{subscriber_hash}")
    Call<Member> patchMemberData(@Body String body, @Path("list_id") String listId, @Path("subscriber_hash") String subscriberHash);


}
