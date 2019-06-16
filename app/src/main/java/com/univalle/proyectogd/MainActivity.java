package com.univalle.proyectogd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSalir = findViewById(R.id.btnCerrarAplicacion);
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent salida = new Intent( Intent.ACTION_MAIN); //Llamando a la activity principal
                finish(); // La cerramos.
            }
        });
    }
}
