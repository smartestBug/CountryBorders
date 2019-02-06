package dev.msemyak.countryborders.ui.main;

import java.util.List;

import dev.msemyak.countryborders.data.model.CountryInfo;

interface MainContract {

    interface View {
        void showCountries(List<CountryInfo> countries);

        void showSpinner();

        void hideSpinner();
    }

    interface Presenter {
        void loadCountries();
    }

    interface ItemClickListener {
        void onItemClick(String countryName, List<String> countryBorders);
    }
}
