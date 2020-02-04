package dev.jdvila.josevilamailchimpapp.repository;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dev.jdvila.josevilamailchimpapp.BuildConfig;
import dev.jdvila.josevilamailchimpapp.R;
import dev.jdvila.josevilamailchimpapp.model.ListsResponse;
import dev.jdvila.josevilamailchimpapp.model.Member;
import dev.jdvila.josevilamailchimpapp.model.MembersResponse;
import dev.jdvila.josevilamailchimpapp.network.MailchimpService;
import dev.jdvila.josevilamailchimpapp.network.RetrofitSingleton;
import dev.jdvila.josevilamailchimpapp.repository.enums.RepositoryMapState;
import dev.jdvila.josevilamailchimpapp.views.DataReturnedListener;
import dev.jdvila.josevilamailchimpapp.views.DataUpdatedListener;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MembersRepositoryImpl implements MembersRepository, NetworkDataLoader, DataReturner, NetworkDataUpdater, UpdateDataConfirmer {
    private static final String API_KEY = BuildConfig.API_KEY;
    private Context context;
    private volatile Map<String, List<Member>> dataMap = new HashMap<>();
    private volatile RepositoryMapState mapState = RepositoryMapState.INITIAL;

    @Override
    public void getData() {
        loadDataFromNetwork();
    }

    @Override
    public void onAttach(@NotNull Context context) {
        this.context = context;
    }

    @Override
    public void onDetach() {
        context = null;
    }

    @Override
    public void loadDataFromNetwork() {
        final OkHttpClient client = RetrofitSingleton.getOkHttpClient(context.getResources().getString(R.string.authorization_header_key),
                context.getResources().getString(R.string.authorization_prefix),
                API_KEY);
        try {
            if (!RetrofitSingleton.isInitialized()) {
                RetrofitSingleton.init(context.getResources().getString(R.string.base_url), GsonConverterFactory.create(), client);
            }
            final Retrofit retrofitSingleton = RetrofitSingleton.getRetrofitInstance();
            final MailchimpService service = retrofitSingleton.create(MailchimpService.class);
            service.getListsCall().enqueue(new Callback<ListsResponse>() {
                @Override
                public void onResponse(@NonNull Call<ListsResponse> call, @NonNull Response<ListsResponse> response) {
                    List<dev.jdvila.josevilamailchimpapp.model.List> mailchimpLists = response.body().getLists();
                    for (final dev.jdvila.josevilamailchimpapp.model.List mailchimpList : mailchimpLists) {
                        service.getListMembersCall(mailchimpList.getId()).enqueue(new Callback<MembersResponse>() {
                            @Override
                            public void onResponse(@NonNull Call<MembersResponse> call, @NonNull Response<MembersResponse> response) {
                                dataMap.put(mailchimpList.getName(), response.body().getMembers());
                                updateDataMapState(mapState);
                                if (mapState == RepositoryMapState.FINAL_CALL) {
                                    returnData(dataMap);
                                }
                            }

                            @Override
                            public void onFailure(@NonNull Call<MembersResponse> call, @NonNull Throwable t) {
                                updateDataMapState(mapState);
                                if (mapState == RepositoryMapState.FINAL_CALL) {
                                    returnData(dataMap);
                                }
                            }
                        });
                    }

                }

                @Override
                public void onFailure(@NonNull Call<ListsResponse> call, @NonNull Throwable t) {
                }
            });
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void returnData(@NotNull Map<String, ? extends List<Member>> dataMap) {
        if (null != context && context instanceof DataReturnedListener) {
            DataReturnedListener dataReturnedListener = (DataReturnedListener) context;
            dataReturnedListener.onDataReturned(dataMap);
            mapState = RepositoryMapState.INITIAL;
        }
    }

    private void updateDataMapState(RepositoryMapState repositoryMapState) {
        switch (repositoryMapState) {
            case INITIAL:
                mapState = RepositoryMapState.FIRST_CALL;
                break;
            case FIRST_CALL:
                mapState = RepositoryMapState.SECOND_CALL;
                break;
            case SECOND_CALL:
                mapState = RepositoryMapState.THIRD_CALL;
                break;
            case THIRD_CALL:
                mapState = RepositoryMapState.FINAL_CALL;
                break;
            case FINAL_CALL:
                break;
        }
    }

    @Override
    public void updateNetworkData(@NotNull String listId, @NotNull String memberId, @NotNull String firstName, @NotNull String lastName, @NotNull String email) {
        Map<String, Object> requestObject = new HashMap<>();
        Map<String, Object> mergeFieldsObject = new HashMap<>();
        mergeFieldsObject.put(context.getResources().getString(R.string.fname_key), firstName);
        mergeFieldsObject.put(context.getResources().getString(R.string.lname_key), lastName);
        requestObject.put(context.getResources().getString(R.string.email_address_key), email);
        requestObject.put(context.getResources().getString(R.string.merge_fields_key), mergeFieldsObject);
        Gson gson = new Gson();
        String jsonObject = gson.toJson(requestObject);
        final OkHttpClient client = RetrofitSingleton.getOkHttpClient(context.getResources().getString(R.string.authorization_header_key),
                context.getResources().getString(R.string.authorization_prefix),
                API_KEY);
        try {
            if (!RetrofitSingleton.isInitialized()) {
                RetrofitSingleton.init(context.getResources().getString(R.string.base_url), GsonConverterFactory.create(), client);
            }
            final Retrofit retrofitSingleton = RetrofitSingleton.getRetrofitInstance();
            final MailchimpService service = retrofitSingleton.create(MailchimpService.class);
            service.patchMemberData(jsonObject, listId, memberId).enqueue(new Callback<Member>() {
                @Override
                public void onResponse(@NonNull Call<Member> call, @NonNull Response<Member> response) {
                    onDataUpdateConfirmed(context.getResources().getString(R.string.update_confirmation_positive));
                }

                @Override
                public void onFailure(@NonNull Call<Member> call, @NonNull Throwable t) {
                    onDataUpdateConfirmed(context.getResources().getString(R.string.update_confirmation_negative));
                }
            });
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateData(@NotNull String listId, @NotNull String memberId, @NotNull String firstName, @NotNull String lastName, @NotNull String email) {
        updateNetworkData(listId, memberId, firstName, lastName, email);
    }

    @Override
    public void onDataUpdateConfirmed(@NotNull String message) {
        if (null != context && context instanceof DataUpdatedListener) {
            DataUpdatedListener dataUpdatedListener = (DataUpdatedListener) context;
            dataUpdatedListener.onDataUpdated(message);
        }
        loadDataFromNetwork();
    }
}
