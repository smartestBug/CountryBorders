package dev.msemyak.countryborders.data.repository;

import java.util.List;

import javax.inject.Inject;

import dev.msemyak.countryborders.data.api.RestCountriesApi;
import dev.msemyak.countryborders.data.model.CountryInfo;
import io.reactivex.Single;

public class CountriesRepositoryImpl implements CountriesRepository {

    private final RestCountriesApi restCountriesApi;

    private List<CountryInfo> countriesCached;

    @Inject
    public CountriesRepositoryImpl(RestCountriesApi restCountriesApi) {
        this.restCountriesApi = restCountriesApi;
    }

    @Override
    public Single<List<CountryInfo>> getAllCountries() {
        if (countriesCached == null) {
            return restCountriesApi.getAllCountries()
                    .toObservable()
                    .flatMapIterable(country -> country)
                    .map(CountryInfo::new)
                    .toList()
                    .doOnSuccess(countryInfoList -> countriesCached = countryInfoList);
        } else {
            return Single.just(countriesCached);
        }
    }
}
