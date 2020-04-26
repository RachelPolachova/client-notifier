package com.market.client.notifier.notifier.data;

public class Post {
    private String multicastId;
    private int success;
    private int failure;

    public Post(String multicastId, int success, int failure) {
        this.multicastId = multicastId;
        this.success = success;
        this.failure = failure;
    }

    public Post() {
    }

    public String getMulticastId() {
        return multicastId;
    }

    public int getSuccess() {
        return success;
    }

    public int getFailure() {
        return failure;
    }

    public void setMulticastId(String multicastId) {
        this.multicastId = multicastId;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public void setFailure(int failure) {
        this.failure = failure;
    }
}
