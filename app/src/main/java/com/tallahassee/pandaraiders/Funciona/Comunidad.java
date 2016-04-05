package com.tallahassee.pandaraiders.Funciona;


import android.content.Intent;
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
import com.tallahassee.pandaraiders.objetos.UserProfile;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Comunidad extends Fragment {
    UserProfile userProf;

    public Comunidad(UserProfile userProf) {
        this.userProf = userProf;
    }

    public static String rutaGeneral = "https://pandaraiders.firebaseio.com//";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_comunidad, container, false);
        Firebase pathGeneral = new Firebase(rutaGeneral);

        //final ArrayList<UserProfile> listContact = GetlistContact(pathGeneral, userProf);
        final ArrayList<UserProfile> listContact = new ArrayList<>();
        ListView lv = (ListView) rootView.findViewById(R.id.listView);
        final ListviewContactAdapter adaptador = new ListviewContactAdapter(getActivity(), listContact);
        lv.setAdapter(adaptador);


        pathGeneral.child("usersProfiles").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    System.out.println("HIJOS: " + child.child("name").getValue());
                    String nombres = child.child("name").getValue().toString();
                    String emails = child.child("email").getValue().toString();
                    //UserProfile up = child.getValue(UserProfile.class);
                    if (emails.equalsIgnoreCase(Comunidad.this.userProf.getEmail())) {
                        System.out.println(emails + " és el nom de l'usuari");
                    } else if (!emails.equalsIgnoreCase(Comunidad.this.userProf.getEmail())) {
                        UserProfile up = new UserProfile(emails, nombres);
                        listContact.add(up);
                        System.out.println("LOS DATOS DEL LISTVIEW: " + listContact);
                        adaptador.notifyDataSetChanged();

                    }


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

                UserProfile userElegido = (UserProfile) parent.getItemAtPosition(position);
                String emailElegido = userElegido.getEmail();
                String nombreElegido = userElegido.getName();

                Intent intent = new Intent(getActivity(), PerfilVisita.class);
                intent.putExtra("emailElegido",emailElegido);
                intent.putExtra("usuarioEmail", userProf.getEmail());
                intent.putExtra("usuarioNombre", userProf.getName());
                startActivity(intent);

            }
        });





        return rootView;
    }
}
