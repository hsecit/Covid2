package com.tp.covid2.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.squareup.picasso.Picasso;
import com.tp.covid2.R;
import com.tp.covid2.api.CovidParam;
import com.tp.covid2.api.bean.SummaryCovid;
import com.tp.covid2.api.bean.dayone.Global;

public class HomeFragment extends Fragment {
    ImageView corona_bg;
    ImageView flag;
    TextView cases;
    TextView cases_today;

    TextView death;
    TextView death_today;

    TextView recover;
    TextView recover_today;


    public SummaryCovid summaryCovid ;
    private HomeViewModel homeViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        /// define the component from the layout
         corona_bg = root.findViewById(R.id.corona_main);
         flag = root.findViewById(R.id.flag);
        cases = root.findViewById(R.id.cases_num);
        cases_today = root.findViewById(R.id.cases_today);

        death = root.findViewById(R.id.death_num);
        death_today = root.findViewById(R.id.death_today);

        recover = root.findViewById(R.id.recoverde_num);
        recover_today = root.findViewById(R.id.recover_today);

        /// end
        //// bind data to view components

       // corona_bg.setImageResource(R.drawable.ic_launcher_background);
        Picasso.with(getContext()).load("https://cdn.pixabay.com/photo/2013/07/12/13/55/earth-147591_960_720.png")
                .into(flag);
        Picasso.with(getContext()).
                load("https://cdn.pixabay.com/photo/2020/04/22/09/55/coronavirus-5076990_960_720.png")
                .into(corona_bg);
        CovidParam covidParam = new CovidParam();
        covidParam.getDataSummary(cases,cases_today,death,death_today,recover,recover_today);



        /// end
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
              //  textView.setText(s);
            }
        });
        return root;
    }



}
