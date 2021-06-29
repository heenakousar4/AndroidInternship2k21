package com.example.devicelocationdetails;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.Task;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    FusedLocationProviderClient locationProviderClient;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.textView);
        locationProviderClient = LocationServices.getFusedLocationProviderClient(this);
    }
    public void getDeviceLocation(View view){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION))!=PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED{
            return;
        }
    }
    FusedLocationProviderClient.getLastLocation().addOnCompleteListener(new .OnCompletionListener<Location>(){
        public void onComplete(Task<Location> task){
            Location location = task.getResult();
            Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
            try{
                List<Address> addresses=geocoder.getFromLocation(location.getLatitude(), location.getLongitude(),1);
                String latitude = String.valueOf(addresses.get(0).getLatitude());
                String longitude = String.valueOf(addresses.get(0).getLongitude());
                String countryName = String.valueOf(addresses.get(0).getCountryName());
                String countrycode = String.valueOf(addresses.get(0).getCountryCode());
                String locality = String.valueOf(addresses.get(0).getLocality());
                String address = String.valueOf(addresses.get(0).getAddressLine(0));
                String postalCode = String.valueOf(addresses.get(0).getPostalCode());
                tv.setText("Latitude:"+latitude+"\n"+"Longitude:"+longitude+"\n"+"Country Name:"+countryName+"\n"+
                        "Country Code:"+countrycode+"\n"+"Locality:"+locality+"\n"+"Address:"+address+"\n"+
                        "Posatal Code:"+postalCode+"\n");
            }catch ()
        }
    })
}