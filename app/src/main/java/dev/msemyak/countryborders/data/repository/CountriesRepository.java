package dev.msemyak.countryborders.data.repository;

import java.util.List;

import dev.msemyak.countryborders.data.model.CountryInfo;
import io.reactivex.Single;

interface CountriesRepository {
    Single<List<CountryInfo>> getAllCountries();
}
