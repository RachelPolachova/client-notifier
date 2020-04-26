package com.market.client.notifier.notifier.data;

public class NearClientRequest {
    private Location2DCoordinates location;
    private String token;

    public NearClientRequest(Location2DCoordinates location, String token) {
        this.location = location;
        this.token = token;
    }

    public Location2DCoordinates getLocation() {
        return location;
    }

    public String getToken() {
        return token;
    }
}
