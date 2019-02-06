package dev.msemyak.countryborders.ui.base;

import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BasePresenter<V extends DaggerAppCompatActivity> {

    private V view;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void attachView(V view) {
        this.view = view;
    }

    public void detachView() {
        compositeDisposable.clear();
        view = null;
    }

    protected V getView() {
        return view;
    }

    protected void addSubscription(Disposable subscription) {
        this.compositeDisposable.add(subscription);
    }

}
