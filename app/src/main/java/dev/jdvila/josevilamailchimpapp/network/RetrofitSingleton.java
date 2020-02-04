package dev.jdvila.josevilamailchimpapp.network;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitSingleton {
    private static Retrofit retrofitInstance;

    private RetrofitSingleton() {
    }

    public static boolean isInitialized() {
        return null != retrofitInstance;
    }

    public static Retrofit getRetrofitInstance() throws InstantiationException {
        if (null == retrofitInstance) {
            throw new InstantiationException("Error - Retrofit Instance not Initialized!");
        }
        return retrofitInstance;
    }

    public static void init(String baseUrl, Converter.Factory factory, OkHttpClient okHttpClient) throws InstantiationException {
        if (null == retrofitInstance) {
            retrofitInstance = new Retrofit.Builder().baseUrl(baseUrl).client(okHttpClient).addConverterFactory(ScalarsConverterFactory.create()).addConverterFactory(factory).build();
            return;
        }
        throw new InstantiationException("Error - Retrofit Instance already Initialized!");
    }

    public static OkHttpClient getOkHttpClient(final String authHeaderKey, final String authPrefix, final String apiKey) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder().addNetworkInterceptor(new Interceptor() {
            @Override
            public  @NotNull okhttp3.Response intercept(@NotNull Chain chain) throws IOException {
                return chain.proceed(
                        chain
                                .request()
                                .newBuilder()
                                .header(
                                        authHeaderKey,
                                        authPrefix + " " + apiKey
                                )
                                .build());
            }
        });
        return builder.build();
    }
}
