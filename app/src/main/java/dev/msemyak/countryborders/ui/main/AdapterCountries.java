package dev.msemyak.countryborders.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import dev.msemyak.countryborders.R;
import dev.msemyak.countryborders.data.model.CountryInfo;

public class AdapterCountries extends RecyclerView.Adapter<AdapterCountries.myViewHolder> {

    private List<CountryInfo> countries;
    private final MainContract.ItemClickListener clickListener;

    public AdapterCountries(List<CountryInfo> countries, MainContract.ItemClickListener clickListener) {
        this.countries = countries;
        this.clickListener = clickListener;
    }

    public void setNewData(List<CountryInfo> countries) {
        this.countries = countries;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        myViewHolder vh;

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.v_country_item, parent, false);
        vh = new myViewHolder(v, clickListener);

        return vh;
    }

    @Override
    public void onBindViewHolder(AdapterCountries.myViewHolder holder, int position) {

        CountryInfo countryForBind = countries.get(position);

        holder.tvCountryName.setText(countryForBind.getName());

        holder.countryName = countryForBind.getName();
        holder.countryBorders = countryForBind.getBorders();
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    // --- inner classes

    static class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final TextView tvCountryName;
        String countryName;
        List<String> countryBorders;

        final MainContract.ItemClickListener clickListener;

        myViewHolder(View v, MainContract.ItemClickListener clickListener) {
            super(v);
            tvCountryName = v.findViewById(R.id.tv_country);
            this.clickListener = clickListener;
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClick(countryName, countryBorders);
        }
    }
}


