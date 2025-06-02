package com.leon.models;

public enum Side {
    BUY("Buy"),
    SELL("Sell"),
    SHORT_SELL("Short Sell");

    private final String side;

    Side(String side) {
        this.side = side;
    }

    public String getSide() {
        return side;
    }
}