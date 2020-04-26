package com.market.client.notifier.notifier.data;

public class Client {
    private Location2DCoordinates location;
    private String name;
    private String phone;

    public Client(Location2DCoordinates location, String name, String phone) {
        this.location = location;
        this.name = name;
        this.phone = phone;
    }

    public Location2DCoordinates getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
}
