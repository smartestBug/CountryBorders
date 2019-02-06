package dev.msemyak.countryborders.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dev.msemyak.countryborders.data.api.RestCountriesApi;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    private final static String SERVER_API_BASE_URL = "https://restcountries.eu/rest/v2/";

    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(SERVER_API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    RestCountriesApi provideRestCountriesApi(Retrofit retrofit) {
        return retrofit.create(RestCountriesApi.class);
    }

}