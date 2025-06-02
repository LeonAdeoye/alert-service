package com.leon.models;

public enum AlertType {
    ORDER_REJECTIONS("Order Rejections"),
    AMENDMENT_REJECTIONS("Amendment Rejections"),
    ALGO_EXPIRED_WITH_EXECUTIONS_OUTSTANDING("Algo Expired With Executions Outstanding"),
    ORDER_NOT_COMPLETE("Order Not Complete");

    private final String alertType;

    AlertType(String alertType) {
        this.alertType = alertType;
    }

    public String getAlertType() {
        return alertType;
    }
}
