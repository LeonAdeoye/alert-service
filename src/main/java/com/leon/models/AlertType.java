package com.leon.models;

public enum AlertType {
    ORDER_REJECTIONS("Order Rejections"),
    AMENDMENT_REJECTIONS("Amendment Rejections");

    private final String alertType;

    AlertType(String alertType) {
        this.alertType = alertType;
    }

    public String getAlertType() {
        return alertType;
    }
}
