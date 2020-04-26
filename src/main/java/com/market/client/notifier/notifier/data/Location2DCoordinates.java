package com.market.client.notifier.notifier.data;

public class Location2DCoordinates {
    private double latitude;
    private double longitude;

    public Location2DCoordinates(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double distance(Location2DCoordinates that) {
        final int R = 6371; // polomer Zeme v kilometroch
        double deltaLat = Math.toRadians(latitude - that.latitude);
        double deltaLong = Math.toRadians(longitude - that.longitude);

        // Haversine metóda
        double a = (Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2)) +
                (Math.cos(Math.toRadians(latitude))) *
                        (Math.cos(Math.toRadians(that.latitude))) *
                        (Math.sin(deltaLong / 2)) *
                        (Math.sin(deltaLong / 2));

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }
}
