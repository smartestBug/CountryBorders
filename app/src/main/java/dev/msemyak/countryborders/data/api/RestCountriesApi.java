package dev.msemyak.countryborders.data.api;

import java.util.List;

import dev.msemyak.countryborders.data.model.Country;
import io.reactivex.Single;
import retrofit2.http.GET;

public interface RestCountriesApi {

    @GET("all")
    Single<List<Country>> getAllCountries();

}
