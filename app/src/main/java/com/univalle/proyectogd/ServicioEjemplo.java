package com.univalle.proyectogd;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class ServicioEjemplo extends Service {
    public ServicioEjemplo() {
    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {

        Log.d("ServicioI","El servicio a Comenzado");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("ServicioF","El servicio a Terminado");
    }
}
