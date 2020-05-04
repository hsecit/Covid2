package com.tp.covid2.api2.lmao.ninjabeans;

public class MapStateCovidPerCountry
{
    private String country;

    private String province;

    private Stats stats;

    private Coordinates coordinates;

    private String updatedAt;

    public String getCountry ()
    {
        return country;
    }

    public void setCountry (String country)
    {
        this.country = country;
    }

    public String getProvince ()
    {
        return province;
    }

    public void setProvince (String province)
    {
        this.province = province;
    }

    public Stats getStats ()
    {
        return stats;
    }

    public void setStats (Stats stats)
    {
        this.stats = stats;
    }

    public Coordinates getCoordinates ()
    {
        return coordinates;
    }

    public void setCoordinates (Coordinates coordinates)
    {
        this.coordinates = coordinates;
    }

    public String getUpdatedAt ()
    {
        return updatedAt;
    }

    public void setUpdatedAt (String updatedAt)
    {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [country = "+country+", province = "+province+", stats = "+stats+", coordinates = "+coordinates+", updatedAt = "+updatedAt+"]";
    }
}
