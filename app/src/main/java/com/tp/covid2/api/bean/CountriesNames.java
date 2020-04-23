package com.tp.covid2.api.bean;

public class CountriesNames {
    private String Country;

    private String Slug;

    private String ISO2;

    public String getCountry ()
    {
        return Country;
    }

    public void setCountry (String Country)
    {
        this.Country = Country;
    }

    public String getSlug ()
    {
        return Slug;
    }

    public void setSlug (String Slug)
    {
        this.Slug = Slug;
    }

    public String getISO2 ()
    {
        return ISO2;
    }

    public void setISO2 (String ISO2)
    {
        this.ISO2 = ISO2;
    }

    @Override
    public String toString()
    {
        return " [Country = "+Country+", Slug = "+Slug+", ISO2 = "+ISO2+"]";
    }
}
