package dev.msemyak.countryborders.di.modules;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dev.msemyak.countryborders.di.scope.ActivityScoped;
import dev.msemyak.countryborders.ui.main.MainActivity;

@Module
public abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector
    abstract MainActivity provideMainActivity();

}