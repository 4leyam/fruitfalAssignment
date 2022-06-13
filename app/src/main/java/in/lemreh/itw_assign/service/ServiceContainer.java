package in.lemreh.itw_assign.service;

import org.jetbrains.annotations.Contract;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ServiceContainer {



    private static final String baseUrl = "https://api.github.com";
    private static Retrofit retrofit = null;
    private static final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private static final GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create();


    /**
     * this function allows to get the service's base url (useful in case the app adopts a
     * microservice type of architecture)
     * @param <T> generic type.
     * @return url string
     */
    @Contract(pure = true)
    private static <T> String getCurrentBaseUrl() {
        return baseUrl;
    }


    /**
     * this function allows to create a service to handle http requests (in a more generic fashion)
     * @param serviceClass the class from where the service has to created from
     * @param <serviceType> the generic class param
     * @return a retrofit object that encapsulates the service
     */
    public static <serviceType> serviceType createService(
            Class<serviceType> serviceClass) {


        ServiceContainer.retrofit = new Retrofit.Builder()
                .baseUrl(
                        getCurrentBaseUrl()
                )
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(gsonConverterFactory)
                .client(httpClient.readTimeout(60 , TimeUnit.SECONDS)
                        .connectTimeout(60 , TimeUnit.SECONDS)
                        .writeTimeout(60 , TimeUnit.SECONDS)
                        .callTimeout(60 , TimeUnit.SECONDS)
                        .retryOnConnectionFailure(false)
                        .build())
                .build();



        return ServiceContainer.retrofit.create(serviceClass);


    }

}

