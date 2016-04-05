package com.tallahassee.pandaraiders.Competicion;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.tallahassee.pandaraiders.R;
import com.tallahassee.pandaraiders.objetos.Localizacion;
//AIzaSyDwhz-Ge1ArCyR6rTEc7cPnlPTdapPcdmg
/**
 * A simple {@link Fragment} subclass.
 */
public class CompeticionFragment1 extends Fragment implements GoogleMap.OnMapClickListener {
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
    public static String rutaGeneral = "https://pandaraiders.firebaseio.com//";
    private Button update;

    private double latitude;
    private double longitude;

    MapView mapView;
    private GoogleMap mapa;
    private final LatLng UPV = new LatLng(39.481106, -0.340987);
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_competicion_fragment1, container, false);
        System.out.println("Estoy en competicion 1");

        final Firebase pathGeneral = new Firebase(rutaGeneral);


        nombreUsuario = (TextView) rootView.findViewById(R.id.idUserNameCompeticion);
        nombreUsuario.setText(nombre);

        latitud = (TextView) rootView.findViewById(R.id.idLatitud);
        longitud = (TextView) rootView.findViewById(R.id.idLongitud);
        update = (Button) rootView.findViewById(R.id.idUpdate);

      update.setOnClickListener(new View.OnClickListener() {

           @Override
            public void onClick(View v) {
                System.out.println("Actualizando...");
                actualizarCoordenadas(pathGeneral);
            }
        });

        actualizarCoordenadas(pathGeneral);

        LatLng position = new LatLng(latitude, longitude);
        // Instantiating MarkerOptions class
        MarkerOptions options = new MarkerOptions();

        // Setting position for the MarkerOptions
        options.position(position);

        // Setting title for the MarkerOptions
        options.title("Position");

        // Setting snippet for the MarkerOptions
        options.snippet("Latitude:"+latitude+",Longitude:"+longitude);

        // Getting Reference to SupportMapFragment of activity_map.xml
        GoogleMap fm = ((SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map)).getMap();

        // Getting reference to google map
        //GoogleMap googleMap = fm.getMap();

        // Adding Marker on the Google Map
        fm.addMarker(options);

        // Creating CameraUpdate object for position
        CameraUpdate updatePosition = CameraUpdateFactory.newLatLng(position);

        // Creating CameraUpdate object for zoom
        CameraUpdate updateZoom = CameraUpdateFactory.zoomBy(4);

        // Updating the camera position to the user input latitude and longitude
        fm.moveCamera(updatePosition);

        // Applying zoom to the marker position
        fm.animateCamera(updateZoom);


        // Inflate the layout for this fragment
        return rootView;
    }

    private void actualizarCoordenadas(Firebase pathGeneral) {
        GPSTracker gps = new GPSTracker(getActivity());
        latitude = gps.getLatitude();
        longitude = gps.getLongitude();

        String strLatitud = String.valueOf(latitude);
        String strLongitud = String.valueOf(longitude);

        System.out.println("RESULTADOS LAT "+latitude+" LONG "+longitude);

        latitud.setText(strLatitud);
        longitud.setText(strLongitud);

        String idEmail = email.replace(".", "%");
        Localizacion loc = new Localizacion(email,latitude, longitude);
        subirCoordenadas(idEmail, loc, pathGeneral);


    }



    private void subirCoordenadas(String idEmail, Localizacion loc, Firebase pathGeneral) {
        Firebase locations = pathGeneral.child("locations");
        Firebase location = locations.child("loc_"+idEmail);
        location.setValue(loc);
    }

    @Override
    public void onMapClick(LatLng latLng) {

    }
}
