package com.example.nilo.dublinbikeir;

/**
 * Created by Nilo on 09/03/2018.
 */

public class LocationContract {

    private String location, status, bikeStand, availableBikeStand, AvailableBikes;
    String lat, lng;

    public LocationContract(String location, String status, String bikeStand, String availableBikeStand, String availableBikes, String lat, String lng) {
        this.location = location;
        this.status = status;
        this.bikeStand = bikeStand;
        this.availableBikeStand = availableBikeStand;
        AvailableBikes = availableBikes;
        this.lat = lat;
        this.lng = lng;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setBikeStand(String bikeStand) {
        this.bikeStand = bikeStand;
    }

    public void setAvailableBikeStand(String availableBikeStand) {
        this.availableBikeStand = availableBikeStand;
    }

    public void setAvailableBikes(String availableBikes) {
        AvailableBikes = availableBikes;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLocation() {
        return location;
    }

    public String getStatus() {
        return status;
    }

    public String getBikeStand() {
        return bikeStand;
    }

    public String getAvailableBikeStand() {
        return availableBikeStand;
    }

    public String getAvailableBikes() {
        return AvailableBikes;
    }

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }

}
