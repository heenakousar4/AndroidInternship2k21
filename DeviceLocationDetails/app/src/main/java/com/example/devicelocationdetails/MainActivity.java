package com.example.devicelocationdetails;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {
    TextView tv;
    FusedLocationProviderClient fusedLocationProviderClient;
    GoogleMap map;
    LocationManager locationManager;
    SupportMapFragment fragment;
    double lat, lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.textView);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        LocationListener listener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                lat = location.getLatitude();
                lon = location.getLongitude();
                fragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                assert fragment != null;
                fragment.getMapAsync(MainActivity.this);
                fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(MainActivity.this);
            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 1, listener);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,1,1,listener);
    }

    public void getDeviceLocationDetails(View view) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location=task.getResult();
                Geocoder geocoder=new Geocoder(MainActivity.this, Locale.getDefault());
                try {
                    List<Address> addresses=geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                    String latitude=String.valueOf(addresses.get(0).getLatitude());
                    String longitude=String.valueOf(addresses.get(0).getLongitude());
                    String countryName=String.valueOf(addresses.get(0).getCountryName());
                    String countryCode=String.valueOf(addresses.get(0).getCountryCode());
                    String locality=String.valueOf(addresses.get(0).getLocality());
                    String address=String.valueOf(addresses.get(0).getAddressLine(0));
                    String postalCode=String.valueOf(addresses.get(0).getPostalCode());
                    tv.setText("Latitude value:"+latitude+"\n"+"Longitude :"+longitude+"\n"
                            +"Country name :"+countryName+"\n"+"CountryCode:+"+countryCode+"\n"+"Locality:"+locality+"\n"
                            +"Address:"+address+"\n"+"Postalcode:"+postalCode);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}