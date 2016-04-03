package com.tallahassee.pandaraiders.Funciona;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.tallahassee.pandaraiders.R;
import com.tallahassee.pandaraiders.objetos.Mensaje;
import com.tallahassee.pandaraiders.objetos.UserProfile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class MisMensajes extends Fragment {
    UserProfile userProf;

    public MisMensajes(UserProfile userProf) {this.userProf = userProf;
        // Required empty public constructor
    }
    public static String rutaGeneral = "https://pandaraiders.firebaseio.com//";

    //private Button limpiar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_mis_mensajes, container, false);
        final Firebase pathGeneral = new Firebase(rutaGeneral);

        //final ArrayList<UserProfile> listContact = GetlistContact(pathGeneral, userProf);
        final ArrayList<Mensaje> listMensajes= new ArrayList<>();
        ListView lv = (ListView) rootView.findViewById(R.id.listView2);
        final AdaptadorListaMensajes adaptador = new AdaptadorListaMensajes(getActivity(), listMensajes);
        lv.setAdapter(adaptador);

        String idUserProfile = "userProfile_"+userProf.getEmail().toString().replace(".","%");

        //----El popUp
        final AlertDialog.Builder dialogoEliminar = new AlertDialog.Builder(getActivity());
        dialogoEliminar.setTitle("Eliminar");
        dialogoEliminar.setMessage("¿Desea eliminar el elemento seleccionado?");
        //final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //final EditText subEditText = (EditText)subView.findViewById(R.id.dialogEditText);

        //--Boton Limpiar
        Button limpiar = (Button) rootView.findViewById(R.id.idBotonLimpiar);
        limpiar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogoEliminar = new AlertDialog.Builder(getActivity());
                //dialogoEliminar.setTitle("Eliminar");
                dialogoEliminar.setMessage("¿Desea limpiar la lista de mensajes?");
                dialogoEliminar.setPositiveButton("Limpiar", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listMensajes.clear();
                    }
                }).setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

            }
        });


        pathGeneral.child("mensajes").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                adaptador.notifyDataSetChanged();
                System.out.println("ha actualizado los datos del adapter");

                Mensaje mensaje = dataSnapshot.getValue(Mensaje.class);
                if (mensaje.getDestinatario().equalsIgnoreCase(userProf.getEmail())) {
                    listMensajes.add(mensaje);
                }
                System.out.println("LOS DESTINATARIOS: " + mensaje.getDestinatario());
                System.out.println("LOS MENSAJES: " + mensaje);
                System.out.println("MIS MENSAJES: " + listMensajes);
                //System.out.println("LOS KEYS: "+dataSnapshot.getKey());
                //String keyMessage =dataSnapshot.getKey().toString();

                //String recibido = dataSnapshot.child(keyMessage).child("mensaje").getValue().toString();
                //System.out.println("RECIBIDOS: "+recibido);
                //listMensajes.add(recibido);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                adaptador.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final Mensaje elegido = (Mensaje) parent.getItemAtPosition(position);
                System.out.println("Llega al click largo con Objeto seleccionado: " + elegido);

                dialogoEliminar.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //elimina el objeto
                        listMensajes.remove(elegido);
                        //lo elimina de la base de datos
                        /*pathGeneral.child("mensajes").getKey().get
                        referencia.child("videoObject_" + elegido.getTitle().toString()).removeValue();

                        adapter.notifyDataSetChanged();
                        System.out.println("ha actualizado los datos del adapter");*/
                   }
                }).setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                dialogoEliminar.show();

                return true;
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("ha clicado un item de la lista");
                //Objeto elegido
                Mensaje menElegido = (Mensaje) parent.getItemAtPosition(position);
                final String menRemitente = menElegido.getRemitente();
                System.out.println("Remitente a responder: "+menRemitente);
                //--------

                LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
                View promptView = layoutInflater.inflate(R.layout.pop_up_input_message, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                alertDialogBuilder.setView(promptView);
                final TextView usuarioRespuesta = (TextView)promptView.findViewById(R.id.idUsuarioRespuesta);
                usuarioRespuesta.setText(menRemitente);
                final EditText editText = (EditText) promptView.findViewById(R.id.edittext);
                // setup a dialog window
                alertDialogBuilder.setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //resultText.setText("Hello, " + editText.getText());
                                enviarMensaje(menRemitente, editText, pathGeneral);
                            }
                        })
                        .setNegativeButton("Cancel",
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


        return rootView;
    }

    private void enviarMensaje(String menRemitente, EditText editText, Firebase pathGeneral) {
        String idEmailUser = userProf.getEmail().replace(".","%");
        try {
            Date data = new Date();
            SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yy_H:mm:ss:SSS");
            String fecha = formater.format(data);
            System.out.println("estoy en click");
            String mensajeParaEnviar = editText.getText().toString();
            System.out.println("Enviará el mensaje: " + mensajeParaEnviar);
            if(!mensajeParaEnviar.equalsIgnoreCase("")){
                Mensaje mensajeEnviar = new Mensaje(mensajeParaEnviar,userProf.getEmail(),menRemitente);
                Firebase mensajes = pathGeneral.child("mensajes");
                Firebase newMensajes = mensajes.child(fecha+"_"+idEmailUser);
                newMensajes.setValue(mensajeEnviar);
                Toast.makeText(getActivity(), "Mensaje enciado correctamente",
                        Toast.LENGTH_SHORT).show();
                editText.setText("");
            }else{
                Toast.makeText(getActivity(), "No puede enviar mensajes vacíos",
                        Toast.LENGTH_SHORT).show();
            }
        }
        catch(Exception e){

        }
    }




}
