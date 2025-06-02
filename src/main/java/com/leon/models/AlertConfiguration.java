package com.leon.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Document(collection = "alertConfigurations")
public class AlertConfiguration
{
    @Id
    private UUID alertConfigurationId;
    private String alertName;
    private String ownerId;
    private Side side;
    private UUID deskId;
    private AlertType alertType;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String frequency;
    private boolean isActive;
    private List<String> exchanges;
    private Priority priority;
    private UUID clientId;

    public AlertConfiguration() {
        this.alertConfigurationId = UUID.randomUUID();
        this.isActive = false;
        this.side = Side.BUY;
        this.alertType = AlertType.ORDER_REJECTIONS;
        this.priority = Priority.LOW;
        this.startTime = LocalDateTime.now();
        this.endTime = LocalDateTime.now().plusHours(6);
        this.frequency = "* * * * * 1-5";
    }

    public UUID getAlertConfigurationId() {
        return alertConfigurationId;
    }

    public void setAlertConfigurationId(UUID alertConfigurationId) {
        this.alertConfigurationId = alertConfigurationId;
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

    public Side getSide() {
        return side;
    }

    public void setSide(Side side) {
        this.side = side;
    }

    public UUID getDeskId() {
        return deskId;
    }

    public void setDeskId(UUID deskId) {
        this.deskId = deskId;
    }

    public AlertType getAlertType() {
        return alertType;
    }

    public void setAlertType(AlertType alertType) {
        this.alertType = alertType;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<String> getExchanges() {
        return exchanges;
    }

    public void setExchanges(List<String> exchanges) {
        this.exchanges = exchanges;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return "AlertConfiguration{" +
                "alertConfigurationId=" + alertConfigurationId +
                ", alertName='" + alertName + '\'' +
                ", ownerId='" + ownerId + '\'' +
                ", side=" + side +
                ", deskId=" + deskId +
                ", alertType=" + alertType +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", frequency='" + frequency + '\'' +
                ", isActive=" + isActive +
                ", exchanges=" + exchanges +
                ", priority=" + priority +
                ", clientId=" + clientId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AlertConfiguration)) return false;

        AlertConfiguration that = (AlertConfiguration) o;

        return alertConfigurationId != null ? alertConfigurationId.equals(that.alertConfigurationId) : that.alertConfigurationId == null;
    }

    @Override
    public int hashCode() {
        return alertConfigurationId != null ? alertConfigurationId.hashCode() : 0;
    }



}



