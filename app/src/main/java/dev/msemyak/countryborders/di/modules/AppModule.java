package dev.msemyak.countryborders.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dev.msemyak.countryborders.data.repository.CountriesRepositoryImpl;
import dev.msemyak.countryborders.data.api.RestCountriesApi;

@Module
public class AppModule {

    @Provides
    @Singleton
    CountriesRepositoryImpl provideCountriesRepository(RestCountriesApi restCountriesApi) {
        return new CountriesRepositoryImpl(restCountriesApi);
    }

}