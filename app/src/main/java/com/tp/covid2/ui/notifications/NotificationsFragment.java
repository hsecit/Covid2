package com.tp.covid2.ui.notifications;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.tp.covid2.R;
import com.tp.covid2.api2.lmao.CovidApiV2;
import com.tp.covid2.api2.lmao.CovidLmoaNinjaV2;
import com.tp.covid2.api2.lmao.ninjabeans.MapStateCovidPerCountry;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NotificationsFragment extends Fragment implements OnMapReadyCallback {
    String url_base ="https://corona.lmao.ninja/";

    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;

    ArrayList<MapStateCovidPerCountry> mapStateCovidPerCountries;
    private MapView mapView;
    private NotificationsViewModel notificationsViewModel;
    GoogleMap map ;

    CovidLmoaNinjaV2 covidLmoaNinjaV2 = new CovidLmoaNinjaV2();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        /////////////////////////////////////////////////////////////Locatio
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    currentLocation = location;
                    Toast.makeText(getContext(), currentLocation.getLatitude() + "" + currentLocation.getLongitude(), Toast.LENGTH_SHORT).show();
                    SupportMapFragment mapFragment = (SupportMapFragment) NotificationsFragment.this.getChildFragmentManager()
                            .findFragmentById(R.id.map);
                    mapFragment.getMapAsync((OnMapReadyCallback) getContext());
                }
            }
        });



        //////////////////////////////////////////////////////////////







        //

        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map=googleMap;

        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(url_base)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CovidApiV2 covidApiV2 = retrofit.create(CovidApiV2.class);
        Call<List<MapStateCovidPerCountry>> call =  covidApiV2.getMapMatkersCovidState();
        call.enqueue(new Callback<List<MapStateCovidPerCountry>>() {
            @Override
            public void onResponse(Call<List<MapStateCovidPerCountry>> call, Response<List<MapStateCovidPerCountry>> response) {
                mapStateCovidPerCountries = new ArrayList<MapStateCovidPerCountry>(response.body()) ;
                Log.d("hamzamap",mapStateCovidPerCountries.toString());
                Toast.makeText(getContext(), "Succes", Toast.LENGTH_SHORT).show();

                for(MapStateCovidPerCountry mapData :mapStateCovidPerCountries){
                        //init marker option
                        MarkerOptions markerOptions = new MarkerOptions();
                        LatLng latLng =new LatLng(Double.parseDouble(mapData.getCoordinates().getLatitude()),Double.parseDouble(mapData.getCoordinates().getLongitude()));
                        markerOptions.position(latLng);
                        markerOptions.title(mapData.getCountry());
                        markerOptions.snippet(mapData.getStats().toString());
                        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
                        map.addMarker(markerOptions);
                        map.animateCamera(CameraUpdateFactory.zoomTo(11));
                        LatLng latlong = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
                        map.moveCamera(CameraUpdateFactory.newLatLng(latlong));
                    }


            }

            @Override
            public void onFailure(Call<List<MapStateCovidPerCountry>> call, Throwable throwable) {

                Toast.makeText(getContext(),  "Problem of select" + currentLocation.getLongitude(), Toast.LENGTH_SHORT).show();

            }
        });




    }





}
