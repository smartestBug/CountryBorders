package dev.msemyak.countryborders.ui.main;

import javax.inject.Inject;

import dev.msemyak.countryborders.R;
import dev.msemyak.countryborders.data.repository.CountriesRepositoryImpl;
import dev.msemyak.countryborders.ui.base.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class MainPresenter extends BasePresenter<MainActivity> implements MainContract.Presenter {

    private final CountriesRepositoryImpl countriesRepository;

    @Inject
    public MainPresenter(CountriesRepositoryImpl countriesRepository) {
        this.countriesRepository = countriesRepository;
    }

    @Override
    public void loadCountries() {

        getView().showSpinner();

        // get countries list from the server
        addSubscription(countriesRepository.getAllCountries()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> getView().hideSpinner())
                .subscribe(response -> {
                            getView().showCountries(response);
                        },
                        error -> {
                            Timber.e(getView().getString(R.string.error_fetching));
                            error.printStackTrace();
                        }));
    }
}
