package com.tallahassee.pandaraiders.Funciona;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.tallahassee.pandaraiders.Competicion.Competicion;
import com.tallahassee.pandaraiders.R;
import com.tallahassee.pandaraiders.objetos.Car;
import com.tallahassee.pandaraiders.objetos.ImagenPerfil;
import com.tallahassee.pandaraiders.objetos.User;
import com.tallahassee.pandaraiders.objetos.UserProfile;

public class MenuPrincipal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Fragment fragment;


    //--objetos vacios
    public static User user = new User();
    public static UserProfile userProfile = new UserProfile();
    public static Car car = new Car();
    public static ImagenPerfil imgPerfil = new ImagenPerfil();

    //--Navigation Bar
    private ImageView imagenPerfil;
    public  TextView userName;
    public TextView carMarca;
    public TextView carModelo;
    public TextView carAno;
    public ActionBar actionBar;
    //---Fin navigation

    //---imagen Perfil
    private static final int SELECT_PICTURE = 1;
    private ImageView imagenDeGaleria;
    private ImageView imagenDeGaleriaPost;
    private String selectedImagePath;

    //------------
    public static String identificadorUsuario;

    public static String rutaGeneral = "https://pandaraiders.firebaseio.com//";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //---------------Nuevo Codigo
        Firebase.setAndroidContext(this);
        Firebase pathGeneral = new Firebase(rutaGeneral);


        //---Cast Navgation Bar

        //imagenDeGaleriaPost = (ImageView) findViewById(R.id.idImagenPerfil);
        //imagenDeGaleria = (ImageView) findViewById(R.id.idImagenPerfil);

        //--- Crear Objetos
        user.setEmail("a@prueba.com");
        user.setPassword("b");
        //user = new User("enric@prueba.com", "b");
        userProfile.setEmail(user.getEmail());
        userProfile.setName("Enric");
        //userProfile = new UserProfile(user.getEmail(),"Enric");
        car.setEmail(userProfile.getEmail());
        car.setMarca("SEAT");
        car.setModelo("Marbella");
        car.setAno("1989");
        //car = new Car(userProfile.getEmail(), "SEAT", "Marbella", "1989");
        //introducirDatosUsuarioEnFirebase(pathGeneral, user, userProfile, car);//Introducimos los datos en Firebase
        extraerDatosUsuarioParaPerfil(pathGeneral, userProfile, car);
        //introducirUsuariosPrueba();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);


        imagenPerfil = (ImageView) findViewById(R.id.idImagenPerfil);
        //extraerCodigoFotoPerfil();
        //imagenPerfil.setImageResource(R.drawable.insertphotohere);



        return true;
    }

    public void cargarBitmapStringToImageView(String imagenString){
        try {
            byte[] encodeByte = Base64.decode(imagenString, Base64.DEFAULT);
            Bitmap bitmapConvertido = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            imagenPerfil.setImageBitmap(bitmapConvertido);
        }catch (Exception e){
            e.getMessage();
            System.out.println("Ha fallado al pasar String a Bitmap");
        }
    }

    public void extraerCodigoFotoPerfil(){
        String identificador = userProfile.getEmail();
        final String identificadorOk = identificador.replace(".", "%");
        System.out.println("IDENTIFICADOR Foto: " + identificadorOk);
        Firebase ref = new Firebase(rutaGeneral);
        //Firebase ref = new Firebase("https://pruebaparafirebase.firebaseio.com/bitmapPhotoSun%20Mar%2006%2018%3A48%3A16%20CET%202016");

        ref.child("usersProfiles").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println("La foto: " + snapshot.getValue());
                String valorFoto = snapshot.child("userProfile_" + identificadorOk).child("fotoPerfil").getValue().toString();
                System.out.println("VALOR FOTO: "+valorFoto);
                //cargarBitmapStringToImageView(valorFoto);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });
    }

    public void montarDatosMenuPerfil(String nombreUserProfile, String marcaCar, String modeloCar, String anoCar){
        //--- Se castean los valores para el menú del usuario
        userName = (TextView) findViewById(R.id.idUserName);
        carMarca = (TextView) findViewById(R.id.idCarMarca);
        carModelo = (TextView) findViewById(R.id.idCarModelo);
        carAno = (TextView) findViewById(R.id.idCarAno);
        //se asignan los valores
        userName.setText(nombreUserProfile);
        carMarca.setText(marcaCar);
        carModelo.setText(modeloCar);
        carAno.setText(anoCar);


    }

    public void extraerDatosUsuarioParaPerfil(Firebase ruta, final UserProfile userProfileExtract, Car carExtract){
        String identificador = userProfileExtract.getEmail();
        final String identificadorOk = identificador.replace(".", "%");
        System.out.println("IDENTIFICADOR: " + identificadorOk);

        ruta.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println(snapshot.getValue());
                snapshot.child("usersProfiles").child("userProfile_" + identificadorOk).child("name").getValue();

                String nombreUserProfile = snapshot.child("usersProfiles").child("userProfile_" + identificadorOk).child("name").getValue().toString();
                String marcaCar = snapshot.child("cars").child("car_" + identificadorOk).child("marca").getValue().toString();
                String modeloCar = snapshot.child("cars").child("car_" + identificadorOk).child("modelo").getValue().toString();
                String anoCar = snapshot.child("cars").child("car_" + identificadorOk).child("ano").getValue().toString();

                System.out.println("VALORES PERFIL: " + nombreUserProfile + ", " + marcaCar + ", " + modeloCar + ", " + anoCar);
                montarDatosMenuPerfil(nombreUserProfile, marcaCar, modeloCar, anoCar);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });
    }

    public void introducirDatosUsuarioEnFirebase(Firebase ruta, User userSave, UserProfile userProfileSave, Car carSave){
        String identificador = userProfileSave.getEmail();

        String identificadorOk = identificador.replace(".", "%");
        System.out.println("IDENTIFICADOR: "+identificadorOk);

        ruta.child("users").child("user_"+identificadorOk).setValue(userSave);
        ruta.child("usersProfiles").child("userProfile_"+identificadorOk).setValue(userProfileSave);
        ruta.child("cars").child("car_"+identificadorOk).setValue(carSave);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        boolean transaccion= false;


        if (id == R.id.nav_camara) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            fragment = new Comunidad(userProfile);
            transaccion = true;

        } else if (id == R.id.nav_slideshow) {
            fragment = new MisMensajes(userProfile);
            transaccion = true;

        } else if (id == R.id.nav_manage) {
            Intent intent = new Intent(this, Competicion.class);
            startActivity(intent);


        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        if(transaccion){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_frame, fragment)
                    .commit();

            item.setChecked(true);
            getSupportActionBar().setTitle(item.getTitle());

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void introducirUsuariosPrueba(){
        Firebase pathGeneral = new Firebase(rutaGeneral);
        User p1 = new User("userPrueba1@prueba.com", "b");
        UserProfile up1 = new UserProfile(p1.getEmail(), "userPrueba1");
        Car c1 = new Car(up1.getEmail(), "FIAT", "Panda", "1990");
        introducirDatosUsuarioEnFirebase(pathGeneral, p1, up1, c1);

        User p2 = new User("userPrueba2@prueba.com", "b");
        UserProfile up2 = new UserProfile(p2.getEmail(), "userPrueba2");
        Car c2 = new Car(up2.getEmail(), "FIAT", "Panda 4x4", "1990");
        introducirDatosUsuarioEnFirebase(pathGeneral, p2, up2, c2);

        User p3 = new User("userPrueba3@prueba.com", "b");
        UserProfile up3 = new UserProfile(p3.getEmail(), "userPrueba3");
        Car c3 = new Car(up3.getEmail(), "SEAT", "Terra", "1985");
        introducirDatosUsuarioEnFirebase(pathGeneral, p3, up3, c3);

        User p4 = new User("userPrueba4@prueba.com", "b");
        UserProfile up4 = new UserProfile(p4.getEmail(), "userPrueba4");
        Car c4 = new Car(up4.getEmail(), "SEAT", "Marbella Special", "1988");
        introducirDatosUsuarioEnFirebase(pathGeneral, p4, up4, c4);
    }



}
