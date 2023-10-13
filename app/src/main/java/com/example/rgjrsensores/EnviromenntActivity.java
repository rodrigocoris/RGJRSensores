package com.example.rgjrsensores;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class EnviromenntActivity extends AppCompatActivity implements SensorEventListener {
    SensorManager sensorManager;
    Sensor sensor;
    Button  tempBtn,otherBtn,gravityBtn;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviromennt);

        tempBtn = findViewById(R.id.btnEnviromentTemperature);
        //otherBtn = findViewById(R.id.btnEnviromentOther);
        gravityBtn  = findViewById(R.id.btnEnvironmentGravity);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);


        /*
        tempBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);

                if(sensor == null){
                    Toast.makeText(EnviromenntActivity.this, "You don't have a temperature sensor", Toast.LENGTH_SHORT).show();
                }else{
                    sensorManager.registerListener(EnviromenntActivity.this,sensor,SensorManager.SENSOR_DELAY_NORMAL);
                }
            }
        });//oncliclklistener
        */
/*
        otherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
                if(sensor == null){
                    Toast.makeText(EnviromenntActivity.this, "El dispositivo No tiene sensor magnetico", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(getBaseContext(),CompassActivity.class);
                    startActivity(intent);
                }
            }
        });
        */

        gravityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
                if(sensor == null){
                    Toast.makeText(EnviromenntActivity.this, "El dispositivo tiene sensor de gravedad", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(EnviromenntActivity.this, "El dispositivo tiene sensor de gravedad", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getBaseContext(),GravityActivity.class);
                    startActivity(intent);

                }
            }
        });
    }//onCreate

    @Override
    public void onSensorChanged(SensorEvent event) {
        float value = event.values[0];
        String message = null;
        switch (event.sensor.getType()){
            case Sensor.TYPE_LIGHT:
                message= value + "lux units";
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
                break;
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                message = value + " degrees Celsius";
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                break;
        }
        sensorManager.unregisterListener(this);

    }
    public void Sensor2(View v) {
        Intent intent = null;

        if (v.getId() == R.id.btnEnviromentTemperature) {
            intent = new Intent(this, ProximityActivity.class);


            startActivity(intent);
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}///class