package com.tp.covid2.ui.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.tp.covid2.R;
import com.tp.covid2.api.bean.dayone.Countries;

import java.util.ArrayList;
import java.util.List;

public class CountrieAdapter extends RecyclerView.Adapter<CountrieAdapter.ViewHolder>  {
    Context context;
    Countries[] countries;
    ArrayList<Countries> countries_origin;

    public CountrieAdapter(Context context , Countries[] countries){
        this.context = context;
        this.countries = countries;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.countrie_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.AfficherView(countries[position]);
    }





    @Override
    public int getItemCount() {
        return countries.length;
    }



    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView flag_view;
        TextView nom ;
        TextView num_cases;
        TextView num_death;
        TextView num_recover;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            flag_view = itemView.findViewById(R.id.flag_view);
            nom = itemView.findViewById(R.id.nom);
            num_cases = itemView.findViewById(R.id.num_case);
            num_death=itemView.findViewById(R.id.num_death);
            num_recover=itemView.findViewById(R.id.num_recovered);

        }
        public void AfficherView(Countries c){
            String flagURL = "https://www.countryflags.io/"+c.getCountryCode().toLowerCase()+"/flat/64.png";
            Picasso.with(context).load(flagURL).into(flag_view);
            nom.setText(c.getCountry());
            num_cases.setText(c.getTotalConfirmed());
            num_death.setText(c.getTotalDeaths());
            num_recover.setText(c.getTotalRecovered());
        }
    }
}
