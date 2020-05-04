package com.tp.covid2.api2.lmao;

import android.content.Context;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.tp.covid2.api2.lmao.ninjabeans.GlobalStateCovid;
import com.tp.covid2.api2.lmao.ninjabeans.MapStateCovidPerCountry;
import com.tp.covid2.api2.lmao.ninjabeans.StateCovidAllCountries;
import com.tp.covid2.helper.NumFormatter;
import com.tp.covid2.ui.dashboard.CovidCountriesAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CovidLmoaNinjaV2 {

    String url_base ="https://corona.lmao.ninja/";

    /// for recyclerview variables
    CovidCountriesAdapter covidCountriesAdapter;

    public void getGlobaleStateCovidData(final TextView cases, final TextView cases_today, final
    TextView death , final TextView death_today, final TextView recover , final TextView recover_today){
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(url_base)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ///init interface
        CovidApiV2 covidApiV2 =retrofit.create(CovidApiV2.class);
        Call<GlobalStateCovid> call = covidApiV2.getGlobaleStateCovid();

        call.enqueue(new Callback<GlobalStateCovid>() {
            @Override
            public void onResponse(Call<GlobalStateCovid> call, Response<GlobalStateCovid> response) {
                GlobalStateCovid summary_global =response.body();
                cases.setText(new NumFormatter(summary_global.getCases()).formatNum());
                cases_today.setText(new NumFormatter(summary_global.getTodayCases()).formatNum());

                death.setText(new NumFormatter(summary_global.getDeaths()).formatNum());
                death_today.setText(new NumFormatter(summary_global.getTodayDeaths()).formatNum());

                recover.setText(new NumFormatter(summary_global.getRecovered()).formatNum());
//                recover_today.setText("");
            }

            @Override
            public void onFailure(Call<GlobalStateCovid> call, Throwable throwable) {

            }
        });
    }



    public void getCovidStateAllCountries(final Context context , final RecyclerView recyclerView , final SearchView searchView){

        //initilize retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url_base)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //init api
        CovidApiV2 covidApiV2 = retrofit.create(CovidApiV2.class);
        Call<List<StateCovidAllCountries>> call= covidApiV2.getCovidStatebyCountry();

        call.enqueue(new Callback<List<StateCovidAllCountries>>() {
            @Override
            public void onResponse(Call<List<StateCovidAllCountries>> call, Response<List<StateCovidAllCountries>> response) {
                //get data from server
                ArrayList<StateCovidAllCountries> stateCovidAllCountries = (ArrayList<StateCovidAllCountries>) response.body();

                covidCountriesAdapter = new CovidCountriesAdapter(context,stateCovidAllCountries);
                recyclerView.setAdapter(covidCountriesAdapter);
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        covidCountriesAdapter.getFilter().filter(newText);
                        return false;
                    }
                });
            }

            @Override
            public void onFailure(Call<List<StateCovidAllCountries>> call, Throwable throwable) {

            }
        });

    }



    //the map section

    public void  getMapMatkersCovidStateData(final GoogleMap Mmap){
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(url_base)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CovidApiV2 covidApiV2 = retrofit.create(CovidApiV2.class);
        Call<List<MapStateCovidPerCountry>> call =  covidApiV2.getMapMatkersCovidState();
        call.enqueue(new Callback<List<MapStateCovidPerCountry>>() {
            @Override
            public void onResponse(Call<List<MapStateCovidPerCountry>> call, Response<List<MapStateCovidPerCountry>> response) {
                ArrayList<MapStateCovidPerCountry> mapStateCovidPerCountries = (ArrayList<MapStateCovidPerCountry>) response.body();

                try{

                    for(MapStateCovidPerCountry mapData :mapStateCovidPerCountries){
                        //init marker option
                        MarkerOptions markerOptions = new MarkerOptions();
                        LatLng latLng =new LatLng(Double.parseDouble(mapData.getCoordinates().getLatitude()),Double.parseDouble(mapData.getCoordinates().getLongitude()));
                        markerOptions.position(latLng);
                        markerOptions.title(mapData.getCountry());
                        markerOptions.snippet(mapData.getStats().toString());
                        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                        Mmap.addMarker(markerOptions);
                        Mmap.animateCamera(CameraUpdateFactory.zoomTo(11));
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<MapStateCovidPerCountry>> call, Throwable throwable) {

            }
        });

    }
}
