package com.tp.covid2.api2.lmao.ninjabeans;

public class Stats
{
    private String recovered;

    private String confirmed;

    private String deaths;

    public String getRecovered ()
    {
        return recovered;
    }

    public void setRecovered (String recovered)
    {
        this.recovered = recovered;
    }

    public String getConfirmed ()
    {
        return confirmed;
    }

    public void setConfirmed (String confirmed)
    {
        this.confirmed = confirmed;
    }

    public String getDeaths ()
    {
        return deaths;
    }

    public void setDeaths (String deaths)
    {
        this.deaths = deaths;
    }

    @Override
    public String toString()
    {
        return "[recovered = "+recovered+", confirmed = "+confirmed+", deaths = "+deaths+"]";
    }
}
