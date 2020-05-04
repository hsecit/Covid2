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
import com.tp.covid2.api2.lmao.ninjabeans.StateCovidAllCountries;
import com.tp.covid2.helper.NumFormatter;

import java.util.ArrayList;
import java.util.Collection;

public class CovidCountriesAdapter extends RecyclerView.Adapter<CovidCountriesAdapter.CardCovidHolder> implements Filterable {
    Context context;
    ArrayList<StateCovidAllCountries> allCountries;
    ArrayList<StateCovidAllCountries> allCountries_origin;

    public CovidCountriesAdapter(Context context,ArrayList<StateCovidAllCountries> allCountries ){
        this.context =context;
        this.allCountries = allCountries;
        this.allCountries_origin = new ArrayList<>(allCountries);
    }
    @NonNull
    @Override
    public CardCovidHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.countrie_card, parent, false);
        return new CardCovidHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CardCovidHolder holder, int position) {
        holder.AfficherView(allCountries.get(position));
    }



    @Override
    public int getItemCount() {
        return allCountries.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }
    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<StateCovidAllCountries> FiltredList = new ArrayList<StateCovidAllCountries>();
            String filterPatern = constraint.toString().toLowerCase().trim();

            if (constraint == null || constraint.length()==0){
                FiltredList.addAll(allCountries_origin);
            }else {
                for (StateCovidAllCountries c :allCountries_origin){
                    if (c.getCountry().contains(filterPatern) || c.getCountryInfo().getFlag().contains(filterPatern)){
                        FiltredList.add(c);
                    }
                }
            }

            FilterResults results =new FilterResults();
            results.values = FiltredList;
            return  results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            allCountries.clear();
            allCountries.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };


    /// calss view holder
    class CardCovidHolder extends RecyclerView.ViewHolder {
        ImageView flag_view;
        TextView nom ;
        TextView num_cases;
        TextView num_death;
        TextView num_recover;
        TextView num_cases_today;
        TextView num_death_today;
        TextView num_recover_today;
        public CardCovidHolder(@NonNull View itemView) {
            super(itemView);
            flag_view = itemView.findViewById(R.id.flag_view);
            nom = itemView.findViewById(R.id.nom);
            num_cases = itemView.findViewById(R.id.num_case);
            num_death=itemView.findViewById(R.id.num_death);
            num_recover=itemView.findViewById(R.id.num_recovered);
            num_cases_today = itemView.findViewById(R.id.cases_num_today);
            num_death_today=itemView.findViewById(R.id.death_num_today);
            num_recover_today=itemView.findViewById(R.id.recovred_num_today);

        }
        public void AfficherView(StateCovidAllCountries c){
            String flagURL = c.getCountryInfo().getFlag();
            Picasso.with(context).load(flagURL).into(flag_view);
            nom.setText(c.getCountry());
            num_cases.setText(new NumFormatter(c.getCases()).formatNum());
            num_death.setText(new NumFormatter(c.getDeaths()).formatNum());
            num_recover.setText(new NumFormatter(c.getRecovered()).formatNum());
            num_cases_today.setText(new NumFormatter(c.getTodayCases()).formatNum());
            num_death_today.setText(new NumFormatter(c.getTodayDeaths()).formatNum());
            num_recover_today.setText("");
        }
    }
}
