package com.tp.covid2.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tp.covid2.R;
import com.tp.covid2.api.CovidParam;
import com.tp.covid2.api2.lmao.CovidLmoaNinjaV2;

public class DashboardFragment extends Fragment {
    SearchView searchView;
    RecyclerView recyclerView;
    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        //final TextView textView = root.findViewById(R.id.text_dashboard);
        searchView = root.findViewById(R.id.search);
        recyclerView = root.findViewById(R.id.listCont);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

//        CovidParam covidParam = new CovidParam();
//        covidParam.getDataSummaryRecycler(getContext(),recyclerView,searchView);

        ////call the RecyclerView
        CovidLmoaNinjaV2 covidLmoaNinjaV2 = new CovidLmoaNinjaV2();
        covidLmoaNinjaV2.getCovidStateAllCountries(getContext(),recyclerView,searchView);

        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //  textView.setText(s);
            }
        });

        return root;
    }
}
