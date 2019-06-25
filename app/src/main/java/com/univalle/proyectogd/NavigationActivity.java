package com.univalle.proyectogd;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.SupportActivity;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar; //libreria para el toolbar se genera automaticamente
import android.view.Menu;
import android.widget.Toast; //Toast son los mensajes que se muetran por un tiempo

//import com.google.android.gms.maps.SupportMapFragment;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AFragment.OnFragmentInteractionListener, BFragment.OnFragmentInteractionListener, FormularioFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = findViewById(R.id.toolbar); //se genera
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        Fragment fragment = new FormularioFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.content_main, fragment).commit();


        navigationView.setNavigationItemSelectedListener(this);

        //Esto nos sirve para el mapa en pantalla
       //SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //menu del clostadito
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) { //Ajustes
            Intent siguiente  = new Intent(NavigationActivity.this,MainActivity.class);
            Toast.makeText(NavigationActivity.this,"Ajustes...", Toast.LENGTH_SHORT).show();//nos da un mensaje
            startActivity(siguiente);
            finish();
            return true;
        }
        if (id == R.id.action_salir){ //si presiona salir
            AlertDialog.Builder builder = new AlertDialog.Builder(NavigationActivity.this);
            builder.setIcon(R.mipmap.ic_launcher).setTitle("Alerta!!!").setMessage("Esta seguro de cerrar la aplicacion...").
                    setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(NavigationActivity.this,"Salida confirmada...", Toast.LENGTH_SHORT).show();
                            Intent salida = new Intent( Intent.ACTION_MAIN); //Llamando a la activity principal
                            finish(); // La cerramos.
                        }
                    }).
                    setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(NavigationActivity.this,"Salida cancelada...", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) { //menu principal
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment miFragment = null;
        boolean fragmentSeleccionado = false;

        if (id == R.id.nav_home) {
            // Handle the camera action
            miFragment = new FormularioFragment();//si es cierto se va al fragmento formulario
            fragmentSeleccionado = true;
        } else if (id == R.id.nav_gallery) {
            miFragment = new AFragment(); //se va  a pedidos
            fragmentSeleccionado = true;
        } else if (id == R.id.nav_slideshow) {
            miFragment = new BFragment();// se va a registrarse
            fragmentSeleccionado = true;
        }  else if (id == R.id.nav_share) {
            Uri uri = Uri.parse("https://www.facebook.com/"); //declaranado variable
            Intent intent = new Intent(Intent.ACTION_VIEW, uri); // se va  a facebook
            startActivity(intent);
            fragmentSeleccionado = true;
        } else if (id == R.id.nav_send) { // se va  atwiter
            Uri uri = Uri.parse("https://twitter.com/");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
            fragmentSeleccionado = true;
        }

        if(fragmentSeleccionado = true){ //si selecciona algo
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main,miFragment).commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout); //
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
