package com.example.amy074944.hackson3;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    Fire testA = new Fire(25.033076, 121.543619, 1, new Date(1539975840192L));
    Fire testB = new Fire(25.032765, 121.518212, 2, new Date(1539989440192L));
    ArrayList<Fire> fireList = new ArrayList<Fire>();
    ArrayList<Marker> markerList = new ArrayList<Marker>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        fireList.add(testA);
        fireList.add(testB);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void init(){
        SupportMapFragment mapFragment = ((SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map));
        mapFragment.getMapAsync(new OnMapReadyCallback(){
            @Override
            public void onMapReady(GoogleMap googleMap){
                mMap = googleMap;
                LatLng Taipei = new LatLng(25.02661111, 121.5271944);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(Taipei));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(14));
                for (Fire fire : fireList) {
                    Log.v("test", String.valueOf(fireList.size()));
                    Marker temp = mMap.addMarker(new MarkerOptions().position(new LatLng(fire.getLati(), fire.getLongti())).title("testA").icon(BitmapDescriptorFactory.defaultMarker(fire.chooseColor())));
                    markerList.add(temp);
                    Log.v("test", "Add");
                }
            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng Taipei = new LatLng(25.02661111, 121.5271944);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Taipei));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(14));
        for (Fire fire : fireList) {
            Marker temp = mMap.addMarker(new MarkerOptions().position(new LatLng(fire.getLati(), fire.getLongti())).title("testA").icon(BitmapDescriptorFactory.defaultMarker(fire.chooseColor())));
            markerList.add(temp);
        }
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                int index = markerList.indexOf(marker);
                final Fire tempf = fireList.get(index);
                new AlertDialog.Builder(MainActivity.this).setTitle("Approve?").setMessage(String.format("At (%.3f, %.3f)\nFire size: %d\nHappened time: %s", tempf.getLongti(), tempf.getLati(), tempf.getFsize(), tempf.getHappentime().toString()
                        )).setPositiveButton("Approved", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        tempf.approve();
                    }
                }).setNegativeButton("Disapproved", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        tempf.disapprove();
                    }
                }).setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }).show();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, ReportActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume(){
        super.onResume();
        Intent intent = getIntent();
        double longti = intent.getDoubleExtra("Longtitude", -200.0);
        double lati = intent.getDoubleExtra("Latitude", -200.0);
        double azimuth = intent.getDoubleExtra("Azimuth", -200.0);
        int fireSize = intent.getIntExtra("FireSize", 0);
        if (longti==-200.0 && lati==-200.0 && azimuth==-200.0 && fireSize==0){

        }else if (longti <= 180.0 && longti >= -180.0 && lati <= 90.0 && lati >= -90.0 && azimuth >= 0 && azimuth <= 360 && fireSize > 0 && fireSize<3){
            Fire fire = new Fire(lati, longti, fireSize, new Date());
            fireList.add(fire);
            Log.v("test", String.valueOf(fireList.size()));
            init();
            //Marker temp = mMap.addMarker(new MarkerOptions().position(new LatLng(fire.getLongti(), fire.getLati())).title("testA").icon(BitmapDescriptorFactory.defaultMarker(fire.chooseColor())));
            //markerList.add(temp);
        }else{
            Snackbar.make((View)findViewById(R.id.map), "WrongInput", Snackbar.LENGTH_LONG).show();
        }
        Log.v("test", String.format("%f, %f, %f, %d", longti, lati, azimuth, fireSize));
    }

}

