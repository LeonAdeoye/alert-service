package com.leon.models;

import java.util.UUID;

public class AlertConfiguration {
    private UUID id;
    private String alertName;

    private String ownerId;

    public AlertConfiguration() {
        this.id = UUID.randomUUID();
        this.alertName = "";
        this.ownerId = "";
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAlertName() {
        return null;
    }

    public void setAlertName(String alertName) {
        this.alertName = alertName;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

}
