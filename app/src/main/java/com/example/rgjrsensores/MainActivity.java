package com.example.rgjrsensores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    //Instancias
    private TextView detalle;
    private SensorManager sensorManager;
    private Sensor sensor;
    private List<Sensor> listadoSensores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Asociar con componente grafico
        detalle = findViewById(R.id.txtDetalle);
    }//onCreate

    public void onClick(View v){
        Intent intent = null;
        switch (v.getId()){
            /*
            case R.id.btnSensorList:
                intent = new Intent(this,Sensors.class);
                break;
            */

            case R.id.btnSensorsEnvironment:
                intent = new Intent(this,EnviromenntActivity.class);
                break;
        }//switch
        startActivity(intent);

    }//onClick

    public void clickListado(View view){
        //Gestion de sensores
        //Gestionar los sensores del dispositivo
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //Obtener los detalles y colocar en una lista
        listadoSensores = sensorManager.getSensorList(Sensor.TYPE_ALL);
        //para cada uno de los sensores msotrar nombre
        for(Sensor sensor: listadoSensores){
            detalle.setText(detalle.getText()+ "\n" + sensor.getName());
        }//for
    }//clickListado

    public void clickMagnetico(View view){
        //Validar la existencia del sensor magnetico
        if (sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) !=null){
            Toast.makeText(getApplicationContext(),"El dispositivo tiene sensor magnetico.",
                    Toast.LENGTH_SHORT).show();
            //vincular con el sensor
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
            //Mostrar datos del sensor
            detalle.setBackgroundColor(Color.GRAY);
            detalle.setText(String.valueOf("Propiedades del sensor magnetico: \n" +
                    "Nombre: "+ String.valueOf(sensor.getName()) + "\n" +
                    "Version: " + String.valueOf(sensor.getVersion() + "\n") +
                    "Fabricante: " + String.valueOf(sensor.getVendor())));
        }else {
            Toast.makeText(getApplicationContext(),"El dispositivo no cuenta con sensor magnetico.", Toast.LENGTH_SHORT).show();
        }//if-else
    }//clickmagnetico

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        //obtiene el valor que arroja el sensor
        float valorCambio = sensorEvent.values[0];//si no sirve comentar esta linea
        //Evaluar el valor para realizar alguna accion
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }



}//class