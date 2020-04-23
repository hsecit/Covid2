package com.tp.covid2.api;

import android.content.Context;
import android.content.pm.LabeledIntent;
import android.util.Log;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.tp.covid2.api.bean.CountriesNames;
import com.tp.covid2.api.bean.DayOneCovid;
import com.tp.covid2.api.bean.SummaryCovid;
import com.tp.covid2.api.bean.dayone.Countries;
import com.tp.covid2.api.bean.dayone.Global;
import com.tp.covid2.ui.dashboard.CountrieAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CovidParam {
    public final String url_api = "https://api.covid19api.com";

    public  final String flag_api = "https://www.countryflags.io/{country code}/flat/64.png";
    // call adapter
    CountrieAdapter countrieAdapter;


    // LIST HOLD DATA
    SummaryCovid summaryCovid ;

    // LIST HOLD DATA
    ArrayList<DayOneCovid> dayOneCovids;

    // LIST HOLD DATA
    ArrayList<CountriesNames> countriesNames;

// this method to get summary of covid19
    public void getDataSummary(final TextView cases, final TextView cases_today, final
    TextView death , final TextView death_today, final TextView recover , final TextView recover_today){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url_api)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CovidApi covidApi=retrofit.create(CovidApi.class);
        Call<SummaryCovid> call = covidApi.getCovidSummary();
        call.enqueue(new Callback<SummaryCovid>() {
            @Override
            public void onResponse(Call<SummaryCovid> call, Response<SummaryCovid> response) {
                summaryCovid = response.body();

                Global summary_global = summaryCovid.getGlobal();
                cases.setText(summary_global.getTotalConfirmed());
                cases_today.setText(summary_global.getNewConfirmed());

                death.setText(summary_global.getTotalDeaths());
                death_today.setText(summary_global.getNewDeaths());

                recover.setText(summary_global.getTotalRecovered());
                recover_today.setText(summary_global.getNewRecovered());

            }

            @Override
            public void onFailure(Call<SummaryCovid> call, Throwable throwable) {

            }
        });


    }
    //// for the adapter module
    public void getDataSummaryRecycler(final Context context , final RecyclerView recyclerView, final SearchView searchView){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url_api)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CovidApi covidApi=retrofit.create(CovidApi.class);
        Call<SummaryCovid> call = covidApi.getCovidSummary();
        call.enqueue(new Callback<SummaryCovid>() {
            @Override
            public void onResponse(Call<SummaryCovid> call, Response<SummaryCovid> response) {
                summaryCovid = response.body();

                ArrayList<Countries> countries = summaryCovid.getCountries();

                countrieAdapter = new CountrieAdapter(context,countries);

                recyclerView.setAdapter(countrieAdapter);


                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        countrieAdapter.getFilter().filter(newText);
                        return false;
                    }
                });


            }

            @Override
            public void onFailure(Call<SummaryCovid> call, Throwable throwable) {

            }
        });


    }


    // this method for get data of one country from day one
    public void getDataDayOne(String country , String status, final Context context){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url_api)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CovidApi covidApi = retrofit.create(CovidApi.class);
        Call<List<DayOneCovid>> call = covidApi.getDayOneCovidData(country,status);
        call.enqueue(new Callback<List<DayOneCovid>>() {
            @Override
            public void onResponse(Call<List<DayOneCovid>> call, Response<List<DayOneCovid>> response) {
                dayOneCovids = new ArrayList<DayOneCovid>(response.body());
            }

            @Override
            public void onFailure(Call<List<DayOneCovid>> call, Throwable throwable) {
                Toast.makeText(context,"something wrong",Toast.LENGTH_SHORT).show();
            }
        });
    }

    // this method responsable to serve countries name

    void getCountriesDataName(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url_api)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CovidApi covidApi = retrofit.create(CovidApi.class);
        Call<List<CountriesNames>> call = covidApi.getCountriesName();
        call.enqueue(new Callback<List<CountriesNames>>() {
            @Override
            public void onResponse(Call<List<CountriesNames>> call, Response<List<CountriesNames>> response) {
                countriesNames = new ArrayList<>(response.body());
            }

            @Override
            public void onFailure(Call<List<CountriesNames>> call, Throwable throwable) {

            }
        });
    }
}


