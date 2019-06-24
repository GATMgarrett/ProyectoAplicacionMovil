package com.univalle.proyectogd;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
    ImageButton atras;
    Button servicio;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //CARGANDO IMAGENES CON GLIDE
        //Ir a la actividad de Imagenes
        Button img = findViewById(R.id.btnImagenes);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent imagenes = new Intent(MainActivity.this, com.univalle.proyectogd.imagenes.class);
                startActivity(imagenes);
                finish();
            }
        });

        //private final int REQUEST_ACCESS_FINE = 0;
        //hola

        //final Button servicio=findViewById(R.id.btnIniciarServicios);
        servicio = (Button)findViewById(R.id.btnIniciarServicio);

        servicio.setOnClickListener(new View.OnClickListener() {
            boolean ban=false;
            @Override
            public void onClick(View view) {

                if (ban==false){
                    Toast toast = Toast.makeText(getApplicationContext(), "Servicio Iniciado", Toast.LENGTH_SHORT);
                    toast.show();
                    startService(new Intent(MainActivity.this,ServicioEjemplo.class));
                    ban=true;
                }
                else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Servicio Finalizado", Toast.LENGTH_SHORT);
                    toast.show();
                    stopService(new Intent(MainActivity.this,ServicioEjemplo.class));
                    ban=false;
                }
            }
        });
    }
}