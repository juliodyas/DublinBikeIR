package com.example.nilo.dublinbikeir;

/**
 * Created by Nilo on 10/03/2018.
 */

public class Markers {

    double lat,lng;

    public Markers(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }
}
