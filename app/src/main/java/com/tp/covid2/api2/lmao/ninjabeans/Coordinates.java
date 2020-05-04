package com.tp.covid2.api2.lmao.ninjabeans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Coordinates
{

    private String latitude;

    private String longitude;

    public String getLatitude ()
    {
        return latitude;
    }

    public void setLatitude (String latitude)
    {
        this.latitude = latitude;
    }

    public String getLongitude ()
    {
        return longitude;
    }

    public void setLongitude (String longitude)
    {
        this.longitude = longitude;
    }
    @Override
    public String toString()
    {
        return " [latitude = "+latitude+", longitude = "+longitude+"]";
    }
}