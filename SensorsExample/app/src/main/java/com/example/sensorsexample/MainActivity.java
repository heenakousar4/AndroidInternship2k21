package com.example.sensorsexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.github.nisrulz.sensey.LightDetector;
import com.github.nisrulz.sensey.Sensey;
import com.github.nisrulz.sensey.ShakeDetector;

public class MainActivity extends AppCompatActivity {
    Switch shake,touch,light;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        shake = findViewById(R.id.shake);
        touch = findViewById(R.id.touch);
        light = findViewById(R.id.light);
        tv= findViewById(R.id.mysensor);
        Sensey.getInstance().init(this);
        ShakeDetector.ShakeListener listener = new ShakeDetector.ShakeListener() {
            @Override
            public void onShakeDetected() {
                tv.setText("Shake Sensor Detched");

            }

            @Override
            public void onShakeStopped() {
                tv.setText("Shake Sensor Stopped");

            }
        };
        shake.setOnCheckedChanged(CompoundButton buttonView,boolean isChecked){
            public void onCheckChanged(CompoundButton buttonView,boolean isChecked){
                if(isChecked){
                    Sensey.getInstance().startShakeDetection(listener);
                }else{
                    Sensey.getInstance().stopShakeDetection(listener);
                }
            }
        });
        LightDetector.LightListener lightListener = new LightDetector.LightListener() {
            @Override
            public void onDark() {
                tv.setText("Is it dark Here");
            }

            @Override
            public void onLight() {
                tv.setText("Is is bright Here");

            }
        };
        light.setOnCheckedChangeListener(CompoundButton buttonView, boolean isChecked){
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked){
                if(isChecked){
                    Sensy.get

                }
            }
        }
    }
}