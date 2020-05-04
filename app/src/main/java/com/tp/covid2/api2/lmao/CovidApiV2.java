package com.tp.covid2.api2.lmao;

import com.tp.covid2.api2.lmao.ninjabeans.GlobalStateCovid;
import com.tp.covid2.api2.lmao.ninjabeans.MapStateCovidPerCountry;
import com.tp.covid2.api2.lmao.ninjabeans.StateCovidAllCountries;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CovidApiV2 {

    @GET("v2/countries?yesterday&sort")
    Call<List<StateCovidAllCountries>> getCovidStatebyCountry();

    @GET("v2/all?yesterday")
    Call<GlobalStateCovid> getGlobaleStateCovid();

    @GET("v2/jhucsse")
    Call<List<MapStateCovidPerCountry>> getMapMatkersCovidState();

}
