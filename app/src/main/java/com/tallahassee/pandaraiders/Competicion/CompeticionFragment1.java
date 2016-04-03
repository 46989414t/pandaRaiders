package com.tallahassee.pandaraiders.Competicion;


import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tallahassee.pandaraiders.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompeticionFragment1 extends Fragment implements LocationListener{
    public String email;
    public String nombre;
    /*public CompeticionFragment1(UserProfile userProfile) {
        // Required empty public constructor
    }*/

    public CompeticionFragment1(String email, String nombre) {
        this.email = email;
        this.nombre = nombre;
    }

    public TextView nombreUsuario;
    public TextView latitud;
    public TextView longitud;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_competicion_fragment1, container, false);
        System.out.println("Estoy en competicion 1");

        nombreUsuario = (TextView) rootView.findViewById(R.id.idUserNameCompeticion);
        nombreUsuario.setText(nombre);

        GPSTracker gps = new GPSTracker(getActivity());

        double latitude = gps.getLatitude();
        double longitude = gps.getLongitude();

        String strLatitud = String.valueOf(latitude);
        String strLongitud = String.valueOf(longitude);

        System.out.println("RESULTADOS LAT "+latitude+" LONG "+longitude);

        latitud = (TextView) rootView.findViewById(R.id.idLatitud);
        longitud = (TextView) rootView.findViewById(R.id.idLongitud);

        latitud.setText(strLatitud);
        longitud.setText(strLongitud);



        /*LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        MyCurrentLoctionListener locationListener = new MyCurrentLoctionListener();


        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);*/

        // Inflate the layout for this fragment
        return rootView;


    }

    @Override
    public void onLocationChanged(Location location) {
        System.out.println("Estoy en location");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
class MyCurrentLoctionListener implements android.location.LocationListener {

    @Override
    public void onLocationChanged(Location location) {
        System.out.println("Estoy en location");
        location.getLatitude();
        location.getLongitude();

        String myLocation = "Latitude = " + location.getLatitude() + " Longitude = " + location.getLongitude();
        Log.e("MY CURRENT LOCATION", myLocation);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
