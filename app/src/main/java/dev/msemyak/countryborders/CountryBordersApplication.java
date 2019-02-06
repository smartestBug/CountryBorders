package dev.msemyak.countryborders;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dev.msemyak.countryborders.di.component.AppComponent;
import dev.msemyak.countryborders.di.component.DaggerAppComponent;
import timber.log.Timber;

public class CountryBordersApplication extends DaggerApplication {

    private AppComponent appComponent;
    private static CountryBordersApplication appInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        appInstance = this;

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        appComponent = DaggerAppComponent
                .builder()
                .application(this)
                .build();

        appComponent.inject(this);

        return appComponent;

    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public static CountryBordersApplication getInstance() {
        return appInstance;
    }

}
