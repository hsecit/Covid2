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
import java.util.Collection;
import java.util.List;

public class CountrieAdapter extends RecyclerView.Adapter<CountrieAdapter.ViewHolder> implements Filterable  {
    Context context;
    ArrayList<Countries> countries;
    ArrayList<Countries> countries_origin;

    public CountrieAdapter(Context context , ArrayList<Countries> countries){
        this.context = context;
        this.countries = countries;
        this.countries_origin = new ArrayList<Countries>(countries);
    }

    public CountrieAdapter() {

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.countrie_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.AfficherView(countries.get(position));
    }


    @Override
    public int getItemCount() {
        return countries.size();
    }
//// filter section
    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            ArrayList<Countries> FilterList =  new ArrayList<Countries>();
            String filterPattern = constraint.toString().toLowerCase().trim();
            if (constraint == null || constraint.length()==0){
                FilterList.addAll(countries_origin);

            }else {
                for (Countries  c : countries_origin ){
                    if (c.getCountry().contains(filterPattern)){
                        FilterList.add(c);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = FilterList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            countries.clear();
            countries.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

//////
    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView flag_view;
        TextView nom ;
        TextView num_cases;
        TextView num_death;
        TextView num_recover;
        TextView num_cases_today;
        TextView num_death_today;
        TextView num_recover_today;
        public ViewHolder(@NonNull View itemView) {
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
        public void AfficherView(Countries c){
            String flagURL = "https://www.countryflags.io/"+c.getCountryCode().toLowerCase()+"/flat/64.png";
            Picasso.with(context).load(flagURL).into(flag_view);
            nom.setText(c.getCountry());
            num_cases.setText(c.getTotalConfirmed());
            num_death.setText(c.getTotalDeaths());
            num_recover.setText(c.getTotalRecovered());
            num_cases_today.setText(c.getNewConfirmed());
            num_death_today.setText(c.getNewDeaths());
            num_recover_today.setText(c.getNewRecovered());
        }
    }
}
