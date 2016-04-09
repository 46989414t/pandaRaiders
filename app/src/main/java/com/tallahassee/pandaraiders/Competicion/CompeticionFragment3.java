package com.tallahassee.pandaraiders.Competicion;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.tallahassee.pandaraiders.R;
import com.tallahassee.pandaraiders.objetos.Etapa;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompeticionFragment3 extends Fragment {
    public CompeticionFragment3() {
    }

    public static String rutaGeneral = "https://pandaraiders.firebaseio.com//";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_competicion_fragment3, container, false);
        final Firebase pathGeneral = new Firebase(rutaGeneral);
        //introducirEtapas(pathGeneral);
        extraerEtapas(rootView, pathGeneral);


        // Inflate the layout for this fragment
        return rootView;
    }
    private void extraerEtapas(View rootView, Firebase pathGeneral) {
        final ArrayList<Etapa> listEtapas = new ArrayList<>();
        ListView lv = (ListView) rootView.findViewById(R.id.idListaEtapas);
        final AdaptadorEtapasLista adapterComp = new AdaptadorEtapasLista(getActivity(), listEtapas);
        lv.setAdapter(adapterComp);

        pathGeneral.child("etapas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    System.out.println("HIJOS: " + child.child("nombre").getValue());
                    //String compEmails = child.child("email").getValue().toString();

                    String nom = child.child("nombre").getValue().toString();
                    String inici = child.child("inicio").getValue().toString();
                    String fin = child.child("fin").getValue().toString();
                    String descrip = child.child("descripcion").getValue().toString();
                    String compLatIni = child.child("latIncio").getValue().toString();
                    String compLongIni = child.child("longIncio").getValue().toString();
                    String compLatFin = child.child("latFinal").getValue().toString();
                    String compLongFin = child.child("longFinal").getValue().toString();
                    //System.out.println("LOS EMAILS DE LOCATIONS "+compEmails+" "+compLat+" "+compLong);
                    double dobLatIni = Double.parseDouble(compLatIni);
                    double dobLongIni = Double.parseDouble(compLongIni);
                    double dobLatFi = Double.parseDouble(compLatFin);
                    double dobLongFi = Double.parseDouble(compLongFin);
                    Etapa etapa = new Etapa(nom, inici, fin, descrip, dobLatIni, dobLongIni, dobLatFi, dobLongFi);
                    listEtapas.add(etapa);
                    adapterComp.notifyDataSetChanged();


                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }

        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("ha clicado un item de la lista");
                //Objeto elegido
                Etapa menElegido = (Etapa) parent.getItemAtPosition(position);
                final double latInicio = menElegido.getLatIncio();
                final double longInicio = menElegido.getLongIncio();
                final double latFinal = menElegido.getLatFinal();
                final double longFinal = menElegido.getLongFinal();
                //--------

                LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
                View promptView = layoutInflater.inflate(R.layout.pop_up_mapa_etapa, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                alertDialogBuilder.setView(promptView);
               /* LatLng salida = new LatLng(menElegido.getLatIncio(),menElegido.getLongIncio());
                LatLng llegada = new LatLng(menElegido.getLatFinal(),menElegido.getLongFinal());

                MarkerOptions option1 = new MarkerOptions();
                MarkerOptions option2 = new MarkerOptions();

                option1.position(salida);
                option2.position(llegada);

                option1.title(menElegido.getInicio());
                option2.title(menElegido.getFin());
                SupportMapFragment fm = ((SupportMapFragment) getActivity().getSupportFragmentManager().
                        findFragmentById(R.id.map));
                fm.getMapAsync((OnMapReadyCallback) getActivity());

                GoogleMap fm = ((SupportMapFragment) getChildFragmentManager()
                        .findFragmentById(R.id.map)).getMap();

                fm.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                // Getting reference to google map
                //GoogleMap googleMap = fm.getMap();

                // Adding Marker on the Google Map
                fm.addMarker(option1);
                fm.addMarker(option2);*/




/*

                GoogleMap googleMap;

                googleMap = ((SupportMapFragment) getChildFragmentManager()
                        .findFragmentById(R.id.map)).getMap();

                googleMap
                        .addPolyline((new PolylineOptions())
                                .add(salida, llegada).width(5).color(Color.BLUE)
                                .geodesic(true));
                // move camera to zoom on map
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(salida,
                        5));*/

                //cargarPuntos(menElegido);
                //final TextView usuarioRespuesta = (TextView)promptView.findViewById(R.id.idUsuarioRespuesta);
                //usuarioRespuesta.setText(menRemitente);
                //final EditText editText = (EditText) promptView.findViewById(R.id.edittext);
                // setup a dialog window

                alertDialogBuilder.setCancelable(false)
                        .setNegativeButton("Salir",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                // create an alert dialog
                AlertDialog alert = alertDialogBuilder.create();
                alert.show();
            }
        });

    }

    private void cargarPuntos(Etapa menElegido) {
 /*       LatLng salida = new LatLng(menElegido.getLatIncio(),menElegido.getLongIncio());
        LatLng llegada = new LatLng(menElegido.getLatFinal(),menElegido.getLongFinal());


        googleMap = ((SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map)).getMap();

        googleMap
                .addPolyline((new PolylineOptions())
                        .add(salida, llegada).width(5).color(Color.BLUE)
                        .geodesic(true));
        // move camera to zoom on map
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(salida,
                5));*/

    }

    private void introducirEtapas(Firebase pathGeneral) {
        //Firebase rutaEtapas = new Firebase(pathGeneral);

        String descripcion0 = "Paseo de Camoes, Madrid, 8.30 a.m. Panda Raid está preparado para recibir a sus participantes. Las verificaciones comienzan en 3, 2, 1…";
        Etapa e0 = new Etapa("etapa-0","Madrid","Algeciras",descripcion0,40.416775,-3.70379,36.140759,-5.456233
        );
        pathGeneral.child("etapas").child(e0.getNombre()).setValue(e0);
        String descripcion1 = "Puerto de Algeciras, 7.00 a.m. Todos los coches están colocados en fila, dispuestos a presentar documentación y billetes para coger el ferry que les llevará a vivir el sueño que llevan preparando tantos meses atrás.";
        Etapa e1 = new Etapa("etapa-1","Tánger Med","El Hajeb",descripcion1,35.891206,-5.494752,33.685735,-5.367784
        );
        pathGeneral.child("etapas").child(e1.getNombre()).setValue(e1);
        String descripcion2 = "06.34 a.m. Amanece en El Hajeb después de una noche gélida que ha dejado las tiendas de los participantes totalmente heladas. El aroma del desayuno atrae a pilotos y copilotos, que reponen fuerzas y se ponen en marcha para continuar la hazaña Panda Raid.";
        Etapa e2 = new Etapa("etapa-2","El Hajeb","Maadid",descripcion2,33.685735,-5.367784,31.48917,-4.219823
        );
        pathGeneral.child("etapas").child(e2.getNombre()).setValue(e2);
        String descripcion3 = "06.34 a.m. Amanece en El Hajeb después de una noche gélida que ha dejado las tiendas de los participantes totalmente heladas. El aroma del desayuno atrae a pilotos y copilotos, que reponen fuerzas y se ponen en marcha para continuar la hazaña Panda Raid.";
        Etapa e3 = new Etapa("etapa-3","Maadid","Merezouga",descripcion3,31.48917,-4.219823,31.080168,-4.013361
        );
        pathGeneral.child("etapas").child(e3.getNombre()).setValue(e3);
        String descripcion4 = "Amanece en Merzouga y la primera tanda de Pandas se dispone a salir a las 7.30 a.m. Ha sido una buena noche, sin mucho frío, lo que ha permitido el descanso de todos. Por delante hay 248 kilómetros, ¡esto ya está hecho!";
        Etapa e4 = new Etapa("etapa-4","Merezouga","Tazulait",descripcion4,31.080168,-4.013361,31.103585,-4.871512
        );
        pathGeneral.child("etapas").child(e4.getNombre()).setValue(e4);
        String descripcion5 = "El sol despunta en el horizonte dando la bienvenida a la quinta etapa de Panda Raid. Mientras unos se asean, otros participantes recogen las tiendas y desayunan para reponer fuerzas y poder enfrentar así la penúltima prueba del raid. La etapa será dura y requerirá una coordinación muy precisa de piloto y copiloto. ¿Cuántos Pandas sobrevivirán a esta jornada?";
        Etapa e5 = new Etapa("etapa-5","Tazulait","Tansikht",descripcion5,31.103585,-4.871512,30.6837,-6.181205
        );
        pathGeneral.child("etapas").child(e5.getNombre()).setValue(e5);
        String descripcion6 = "Última vez que suenan los despertadores en el campamento de Panda Raid 2016.  Todo el mundo, equipos y organización, se despiden de esta convivencia extrema durante casi una semana, en la que los Pandas y el compañerismo han sido los verdaderos protagonistas. Una cosa está clara: “Haré todo lo posible por estar aquí en la próxima edición”.";
        Etapa e6 = new Etapa("etapa-6","Tansikht","Marrakech",descripcion6,30.6837,-6.181205,31.629472,-7.981084
        );
        pathGeneral.child("etapas").child(e6.getNombre()).setValue(e6);
    }


}
