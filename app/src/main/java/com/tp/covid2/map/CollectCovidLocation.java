package com.tp.covid2.map;
// TODO: 4/29/2020 need to add process
import com.tp.covid2.api.CovidApi;
import com.tp.covid2.api.bean.CountriesNames;
import com.tp.covid2.api.bean.StateCovidPerCountry;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CollectCovidLocation {
    public  List<StateCovidPerCountry> stateCovidPerCountryList;
    List<CountriesNames> countriesNamesList;
    public final String url_api = "https://api.covid19api.com";

    /// get List of info per country

    public void ListCountrieMarkers(){
        Retrofit retrofit = new  Retrofit.Builder()
                .baseUrl(url_api)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CovidApi covidApi = retrofit.create(CovidApi.class);
        Call<List<CountriesNames>> call = covidApi.getCountriesName();

        call.enqueue(new Callback<List<CountriesNames>>() {
            @Override
            public void onResponse(Call<List<CountriesNames>> call, Response<List<CountriesNames>> response) {
               countriesNamesList = response.body();

                for (CountriesNames cn : countriesNamesList){
                    String countryid = cn.getCountry();
                    //call the process that can generate marker per given country
                    ListCovidPerCountry(countryid);
                }

            }

            @Override
            public void onFailure(Call<List<CountriesNames>> call, Throwable throwable) {

            }
        });
    }

    ////// get info about the country

    public void ListCovidPerCountry(String countryid){
        Retrofit retrofit = new  Retrofit.Builder()
                .baseUrl(url_api)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CovidApi covidApi = retrofit.create(CovidApi.class);
        Call<List<StateCovidPerCountry>> call = covidApi.getStateCovidPerDay(countryid);

        call.enqueue(new Callback<List<StateCovidPerCountry>>() {
            @Override
            public void onResponse(Call<List<StateCovidPerCountry>> call, Response<List<StateCovidPerCountry>> response) {
                // TODO: 4/29/2020
               stateCovidPerCountryList = response.body();

            }

            @Override
            public void onFailure(Call<List<StateCovidPerCountry>> call, Throwable throwable) {

            }
        });
    }
}
