package com.univalle.proyectogd;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SplashActivity extends AppCompatActivity {

    View Imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Imagen = findViewById(R.id.Imagen);
        Imagen.animate().scaleX(10).scaleY(10).setDuration(1300).start();//apariencia de la animacion de 1.3 segundos

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this,NavigationActivity.class);// header para ir a otra actividad en este caso a Navigation
                startActivity(intent); //comienz a ejecutar el intent
                finish();
            }
        },1300); //todo el proceo lo hace en 1.3 seg.
    }
}
