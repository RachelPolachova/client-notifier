package com.market.client.notifier.notifier.data;

public class PushNotificationData {
    private String title;
    private String content;
    private String type;
    private Client client;

    public PushNotificationData(String title, String content, String type, Client client) {
        this.title = title;
        this.content = content;
        this.type = type;
        this.client = client;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getType() {
        return type;
    }

    public Client getClient() {
        return client;
    }
}
