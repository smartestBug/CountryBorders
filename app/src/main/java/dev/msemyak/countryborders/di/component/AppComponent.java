package dev.msemyak.countryborders.di.component;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dev.msemyak.countryborders.CountryBordersApplication;
import dev.msemyak.countryborders.di.modules.ActivityBindingModule;
import dev.msemyak.countryborders.di.modules.ApiModule;
import dev.msemyak.countryborders.di.modules.AppModule;


@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        ApiModule.class,
        ActivityBindingModule.class
})

public interface AppComponent extends AndroidInjector<CountryBordersApplication> {

    @Override
    void inject(CountryBordersApplication instance);

    @Component.Builder
    interface Builder {
        @BindsInstance
        AppComponent.Builder application(CountryBordersApplication application);

        AppComponent build();
    }
}