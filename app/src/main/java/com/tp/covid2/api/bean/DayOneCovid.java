package com.tp.covid2.api.bean;

// URL https://api.covid19api.com/dayone/country/{county}/status/{status}/live
// {
//         "Country": "Morocco",
//         "CountryCode": "MA",
//         "Province": "",
//         "City": "",
//         "CityCode": "",
//         "Lat": "31.79",
//         "Lon": "-7.09",
//         "Cases": 1,
//         "Status": "confirmed",
//         "Date": "2020-03-02T00:00:00Z"
//         },

import com.tp.covid2.api.bean.dayone.Countries;
import com.tp.covid2.api.bean.dayone.Global;

public class DayOneCovid {
    private String CityCode;

    private String Status;

    private String Country;

    private String Lon;

    private String City;

    private String CountryCode;

    private String Province;

    private String Lat;

    private String Cases;

    private String Date;

    public String getCityCode ()
    {
        return CityCode;
    }

    public void setCityCode (String CityCode)
    {
        this.CityCode = CityCode;
    }

    public String getStatus ()
    {
        return Status;
    }

    public void setStatus (String Status)
    {
        this.Status = Status;
    }

    public String getCountry ()
    {
        return Country;
    }

    public void setCountry (String Country)
    {
        this.Country = Country;
    }

    public String getLon ()
    {
        return Lon;
    }

    public void setLon (String Lon)
    {
        this.Lon = Lon;
    }

    public String getCity ()
    {
        return City;
    }

    public void setCity (String City)
    {
        this.City = City;
    }

    public String getCountryCode ()
    {
        return CountryCode;
    }

    public void setCountryCode (String CountryCode)
    {
        this.CountryCode = CountryCode;
    }

    public String getProvince ()
    {
        return Province;
    }

    public void setProvince (String Province)
    {
        this.Province = Province;
    }

    public String getLat ()
    {
        return Lat;
    }

    public void setLat (String Lat)
    {
        this.Lat = Lat;
    }

    public String getCases ()
    {
        return Cases;
    }

    public void setCases (String Cases)
    {
        this.Cases = Cases;
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
        return " "+CityCode+", Status = "+Status+", Country = "+Country+", Lon = "+Lon+", City = "+City+", CountryCode = "+CountryCode+", Province = "+Province+", Lat = "+Lat+", Cases = "+Cases+", Date = "+Date+"]";
    }
}
