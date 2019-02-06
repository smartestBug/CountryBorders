package dev.msemyak.countryborders.ui.main;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;
import dev.msemyak.countryborders.R;
import dev.msemyak.countryborders.data.model.CountryInfo;

public class MainActivity extends DaggerAppCompatActivity implements MainContract.View, MainContract.ItemClickListener {

    @Inject
    MainPresenter presenter;

    private MaterialDialog waitDialog;

    private List<CountryInfo> countriesList;

    @BindView(R.id.rv_countries)
    RecyclerView recyclerViewCountries;

    private AdapterCountries countriesAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attachView(this);

        presenter.loadCountries();

    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.detachView();
    }

    @Override
    public void showCountries(List<CountryInfo> countriesList) {

        this.countriesList = countriesList;

        if (countriesAdapter == null) {
            recyclerViewCountries.setHasFixedSize(true);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerViewCountries.setLayoutManager(layoutManager);
            countriesAdapter = new AdapterCountries(countriesList, this);
            recyclerViewCountries.setAdapter(countriesAdapter);
            recyclerViewCountries.setNestedScrollingEnabled(false);

            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerViewCountries.getContext(), layoutManager.getOrientation());
            recyclerViewCountries.addItemDecoration(dividerItemDecoration);

        } else {
            countriesAdapter.setNewData(countriesList);
            countriesAdapter.notifyDataSetChanged();
            recyclerViewCountries.scrollToPosition(0);
        }

    }

    @Override
    public void showSpinner() {
        waitDialog = new MaterialDialog.Builder(this)
                .title(R.string.spinner_title)
                .content(R.string.spinner_content)
                .progress(true, 0)
                .cancelable(false)
                .show();
    }

    @Override
    public void hideSpinner() {
        waitDialog.hide();
    }

    @Override
    public void onItemClick(String countryName, List<String> countryBorders) {

        new MaterialDialog.Builder(this)
                .title(String.format(getString(R.string.borders_title), countryName))
                .content(makeBordersString(countryBorders))
                .cancelable(true)
                .positiveText(R.string.ok)
                .onPositive((dialog, which) -> dialog.dismiss())
                .show();
    }

    private String makeBordersString(List<String> countryBorders) {
        StringBuilder sb = new StringBuilder();

        if (countryBorders.isEmpty()) {
            sb.append(getString(R.string.none));
        } else {
            int bordersFound = 0;

            for (CountryInfo country : countriesList) {
                if (countryBorders.contains(country.getCode())) {
                    sb.append(country.getName());
                    if (++bordersFound == countryBorders.size()) break;
                    sb.append(getString(R.string.lf));
                }
            }
        }

        return sb.toString();
    }

}
