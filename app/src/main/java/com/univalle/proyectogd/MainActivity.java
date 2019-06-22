package com.univalle.proyectogd;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Singleton
    private static MainActivity Plataforma=null;
    public static MainActivity getInstance(){
        if (Plataforma==null){
            Plataforma=new MainActivity();
        }
        return Plataforma;
    }
    //fin del singleton

    Button btnIngresar;


    EditText txtEmail, txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //CARGANDO IMAGENES CON GLIDE
        //ImageView imagen1=findViewById(R.id)



        //private final int REQUEST_ACCESS_FINE = 0;

        Button btnSalir;

        btnIngresar = (Button) findViewById(R.id.btnIngresar);
        btnSalir = (Button) findViewById(R.id.btnCerrarAplicacion);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPassword = (EditText) findViewById(R.id.txtPassword);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e = txtEmail.getText().toString();
                String p = txtPassword.getText().toString();
                Intent siguiente  = new Intent(MainActivity.this,NavigationActivity.class);
                if (e.equals("") && p.equals("")){
                    Toast.makeText(MainActivity.this,"Secion iniciada correctamente...", Toast.LENGTH_SHORT).show();
                    startActivity(siguiente);
                    finish();
                }
                else {
                    Toast.makeText(MainActivity.this,"Error en el Inicio de secion...", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setIcon(R.mipmap.ic_launcher).setTitle("Alerta!!!").setMessage("Esta seguro de cerrar la aplicacion...").
                        setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this,"Salida confirmada...", Toast.LENGTH_SHORT).show();
                                Intent salida = new Intent( Intent.ACTION_MAIN); //Llamando a la activity principal
                                finish(); // La cerramos.
                            }
                        }).
                        setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this,"Salida cancelada...", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }
}