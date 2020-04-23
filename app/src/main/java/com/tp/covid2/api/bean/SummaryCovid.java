package com.tp.covid2.api.bean;


// URL https://api.covid19api.com/summary
// this class take info from json
//  {
//          "Country": "Afghanistan",
//          "CountryCode": "AF",
//          "Slug": "afghanistan",
//          "NewConfirmed": 66,
//          "TotalConfirmed": 1092,
//          "NewDeaths": 0,
//          "TotalDeaths": 36,
//          "NewRecovered": 15,
//          "TotalRecovered": 150,
//          "Date": "2020-04-22T15:53:39Z"
//          }


import com.tp.covid2.api.bean.dayone.Countries;
import com.tp.covid2.api.bean.dayone.Global;

public class SummaryCovid {

    private Global Global;

    private Countries[] Countries;

    private String Date;

    public Countries[] getCountries ()
    {
        return Countries;
    }

    public void setCountries (Countries[] Countries)
    {
        this.Countries = Countries;
    }

    public Global getGlobal ()
    {
        return Global;
    }

    public void setGlobal (com.tp.covid2.api.bean.dayone.Global Global)
    {
        this.Global = Global;
    }

    public String getDate ()
    {
        return Date;
    }

    public void setDate (String Date)
    {
        this.Date = Date;
    }

    @Override
    public String toString()
    {
        return " [Countries = "+Countries+", Global = "+Global+", Date = "+Date+"]";
    }
}
