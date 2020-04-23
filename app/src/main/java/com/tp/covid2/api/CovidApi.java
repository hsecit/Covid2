package com.tp.covid2.api;

import com.tp.covid2.api.bean.CountriesNames;
import com.tp.covid2.api.bean.DayOneCovid;
import com.tp.covid2.api.bean.SummaryCovid;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CovidApi {

    @GET("summary")
    Call<SummaryCovid> getCovidSummary();

    @GET("dayone/country/{country}/status/{status}/live")
    Call<List<DayOneCovid>> getDayOneCovidData(@Path("country") String county,@Path("status") String status);

    @GET("countries")
    Call<List<CountriesNames>> getCountriesName();

}
