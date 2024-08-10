package com.example.weather;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

import com.google.android.gms.maps.CameraUpdateFactory;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class WeatherMap {
    private GoogleMap googleMap;
    private Context context;

    public WeatherMap(SupportMapFragment mapFragment, Context context) {
        this.context = context;
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                WeatherMap.this.googleMap = googleMap;
                initializeMap();
            }
        });
    }

    private void initializeMap() {
        // set default location and zoom level
        LatLng defaultLocation = new LatLng(-34, 151); // Sydney, Australia
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, 10));

        // enable user location if permission is granted or not
        if (context.checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) == android.content.pm.PackageManager.PERMISSION_GRANTED) {
            googleMap.setMyLocationEnabled(true);
        }}

    public void addMarker(LatLng latLng, String title) {
        googleMap.addMarker(new MarkerOptions().position(latLng).title(title));
    }

    public void moveCameraToLocation(LatLng latLng, float zoomLevel) {
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel));
    }

    public LatLng getCurrentLocation() {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (context.checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) == android.content.pm.PackageManager.PERMISSION_GRANTED) {
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location != null) {
                return new LatLng(location.getLatitude(), location.getLongitude());
            }}
        return null;
  }}
