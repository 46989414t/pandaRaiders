package com.tallahassee.pandaraiders.Competicion;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.tallahassee.pandaraiders.R;
import com.tallahassee.pandaraiders.objetos.Localizacion;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompeticionFragment2 extends Fragment {
    public String nombre;
    public String email;

    public CompeticionFragment2(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }

    public static String rutaGeneral = "https://pandaraiders.firebaseio.com//";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_competicion_fragment2, container, false);
        Firebase pathGeneral = new Firebase(rutaGeneral);

        extraerCorredores(rootView, pathGeneral);



        return rootView;
    }

    private void posicionarCorredores(Localizacion compLoc) {
        LatLng position = new LatLng(compLoc.getLatitud(), compLoc.getLongitud());
        // Instantiating MarkerOptions class
        MarkerOptions options = new MarkerOptions();

        // Setting position for the MarkerOptions
        options.position(position);

        // Setting title for the MarkerOptions
        options.title(compLoc.getEmail());

        // Setting snippet for the MarkerOptions
        options.snippet("Latitude:"+compLoc.getLatitud()+",Longitude:"+compLoc.getLongitud());

        // Getting Reference to SupportMapFragment of activity_map.xml
        GoogleMap fm = ((SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map)).getMap();

        fm.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        // Getting reference to google map
        //GoogleMap googleMap = fm.getMap();

        // Adding Marker on the Google Map
        fm.addMarker(options);

        // Creating CameraUpdate object for position
        CameraUpdate updatePosition = CameraUpdateFactory.newLatLngZoom(position, 4);
        fm.animateCamera(updatePosition);
    }

    private void extraerCorredores(View rootView, Firebase pathGeneral) {
        final ArrayList<Localizacion> listCompetidores = new ArrayList<>();
        ListView lv = (ListView) rootView.findViewById(R.id.idListaCorredoresActivos);
        final AdaptadorCorredoresActivosLista adapterComp = new AdaptadorCorredoresActivosLista(getActivity(), listCompetidores);
        lv.setAdapter(adapterComp);

        pathGeneral.child("locations").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    System.out.println("HIJOS: " + child.child("email").getValue());
                    String compEmails = child.child("email").getValue().toString();

                    String compLat = child.child("latitud").getValue().toString();
                    String compLong = child.child("longitud").getValue().toString();
                    System.out.println("LOS EMAILS DE LOCATIONS "+compEmails+" "+compLat+" "+compLong);
                    double dobLat = Double.parseDouble(compLat);
                    double dobLong= Double.parseDouble(compLong);
                    if (compEmails.equalsIgnoreCase(email)) {
                        System.out.println(email + " és el email de l'usuari");
                    } else if (!compEmails.equalsIgnoreCase(email)) {
                        Localizacion compLoc = new Localizacion(compEmails,dobLat,dobLong);
                        listCompetidores.add(compLoc);
                        System.out.println("LOS DATOS DEL LISTVIEW: " + listCompetidores);
                        posicionarCorredores(compLoc);
                        adapterComp.notifyDataSetChanged();

                    }

                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }

        });
    }


}
