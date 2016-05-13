package com.tallahassee.pandaraiders.Funciona;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.tallahassee.pandaraiders.R;
import com.tallahassee.pandaraiders.objetos.Mensaje;

import java.text.SimpleDateFormat;
import java.util.Date;


public class PerfilVisita extends AppCompatActivity {
    private TextView emailVisita;
    public static String rutaGeneral = "https://pandaraiders.firebaseio.com/";
    private TextView indicadorUsusario;
    private EditText mensaje;
    private Button enviar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_visita);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Firebase.setAndroidContext(this);
        final Firebase pathGeneral = new Firebase(rutaGeneral);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        emailVisita = (TextView) findViewById(R.id.idEmailVisita);
        Bundle bundle = getIntent().getExtras();
        final String emailExtraido = bundle.getString("emailElegido");
        emailVisita.setText(emailExtraido);
        final String idUserToVisit = "userProfile_"+emailExtraido.replace(".","%");
        String idCarToVisit = "car_"+emailExtraido.replace(".","%");
        buscarDatosUsuarioVisita(pathGeneral, idUserToVisit);
        buscarCocheUsuarioVisita(pathGeneral, idCarToVisit);

        //EXTRAER USUARIO
        final String emailUser = bundle.getString("usuarioEmail");
        final String idEmailUser = emailUser.replace(".","%");
        System.out.println("mi emai en perfil visita " + emailUser);
        final String nombreUser = bundle.getString("usuarioNombre");
        System.out.println("mi nombre en perfil visita " + nombreUser);

        indicadorUsusario = (TextView) findViewById(R.id.idNombreUsuario);
        indicadorUsusario.setText(nombreUser + ":");


        final String rutaEnvio = "usersProfiles/"+idUserToVisit+"/"+"mensajes/";

        enviar = (Button)findViewById(R.id.idBotonEnviar);

        enviar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    Date data = new Date();
                    SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yy_H:mm:ss:SSS");
                    String fecha = formater.format(data);
                    System.out.println("estoy en click");
                    mensaje = (EditText) findViewById(R.id.idMensaje);
                    String mensajeParaEnviar = mensaje.getText().toString();
                    System.out.println("Enviará el mensaje: " + mensajeParaEnviar);
                    System.out.println("Enviarlo a: " + idUserToVisit);
                    System.out.println("La ruta: " + pathGeneral + "/usersProfiles/" + idUserToVisit);
                    if(!mensajeParaEnviar.equalsIgnoreCase("")){
                       /* pathGeneral.child("mensajes").child(fecha+"_"+idEmailUser).child("mensaje").setValue(mensajeParaEnviar);
                        pathGeneral.child("mensajes").child(fecha+"_"+idEmailUser).child("remitente").setValue(emailUser);
                        pathGeneral.child("mensajes").child(fecha+"_"+idEmailUser).child("destinatario").setValue(emailExtraido);*/
                        Mensaje mensajeEnviar = new Mensaje(mensajeParaEnviar,emailUser,emailExtraido);
                        Firebase mensajes = pathGeneral.child("mensajes");
                        Firebase newMensajes = mensajes.child(fecha+"_"+idEmailUser);
                        newMensajes.setValue(mensajeEnviar);
                        Toast.makeText(getApplicationContext(), "Mensaje enciado correctamente",
                                Toast.LENGTH_SHORT).show();
                        mensaje.setText("");
                    }else{
                        Toast.makeText(getApplicationContext(), "No puede enviar mensajes vacíos",
                                Toast.LENGTH_SHORT).show();
                    }
                }
                catch(Exception e){

                }
            }
        });


    }

    private void buscarCocheUsuarioVisita(Firebase pathGeneral, String idCarToVisit) {
        System.out.println("ID CAR TO VISIT: "+idCarToVisit);

        pathGeneral.child("cars").child(idCarToVisit).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("COCHE: " + dataSnapshot.getValue());

                String infoCoche = dataSnapshot.child("marca").getValue().toString() + ", "
                        +dataSnapshot.child("modelo").getValue().toString()+", "
                        +dataSnapshot.child("ano").getValue().toString();
                TextView cocheVisita = (TextView)findViewById(R.id.idCocheVisita);
                cocheVisita.setText(infoCoche);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }


    private void buscarDatosUsuarioVisita(Firebase pathGeneral, String idUserToVisit) {
        System.out.println("ID USER TO VISIT: "+idUserToVisit);
        //Firebase pathUser = new Firebase(pathGeneral+"usersProfiles/");

        pathGeneral.child("usersProfiles").child(idUserToVisit).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("DATOS PERFIL: " + dataSnapshot.getValue());
                String nombreVisita = dataSnapshot.child("name").getValue().toString();

                TextView printNombreVisita = (TextView) findViewById(R.id.idNombreVisita);
                printNombreVisita.setText(nombreVisita);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

/*
    public void onEnviar(View view) {
        Firebase envioPath = new Firebase("https://pandaraiders.firebaseio.com/usersProfiles/userProfile_userPrueba1%40prueba%25com/mensajes");
        Date data = new Date();
        System.out.println("estoy en enviar");
        mensaje = (EditText)findViewById(R.id.idMensaje);
        String mensajeParaEnviar = mensaje.getText().toString();
        System.out.println("Mensaje que se envia: " + mensajeParaEnviar);
        String nombreMensaje = data.toString();

        envioPath.child("holaaa").setValue(mensajeParaEnviar);
    }*/
}
