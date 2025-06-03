package com.leon.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.ArrayList;


@Document(collection = "AlertConfiguration")
public class AlertConfiguration
{
    @Id
    private UUID id;
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
    private String customizations;

    public AlertConfiguration() {
        this.id = UUID.randomUUID();
        this.isActive = false;
        this.alertName = "";
        this.side = Side.BUY;
        this.alertType = AlertType.ORDER_REJECTIONS;
        this.priority = Priority.LOW;
        this.startTime = LocalDateTime.now();
        this.endTime = LocalDateTime.now().plusHours(6);
        this.frequency = "* * * * 1-5";
        this.exchanges = new ArrayList<>();
        this.customizations = "";
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAlertName() {
        return this.alertName;
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

    public boolean getIsActive() {
        return this.isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
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

    public String getCustomizations() {
        return customizations;
    }

    public void setCustomizations(String customizations) {
        this.customizations = customizations;
    }

    @Override
    public String toString() {
        return "AlertConfiguration{" +
                "alertConfigurationId=" + id +
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
                ", customizations='" + customizations + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, alertName, ownerId, side, deskId, alertType, startTime, endTime, frequency, isActive, exchanges, priority, clientId, customizations);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AlertConfiguration)) return false;
        AlertConfiguration that = (AlertConfiguration) o;
        return isActive == that.isActive && Objects.equals(getId(), that.getId()) && Objects.equals(getAlertName(), that.getAlertName())
                && Objects.equals(getOwnerId(), that.getOwnerId()) && getSide() == that.getSide() && Objects.equals(getDeskId(), that.getDeskId())
                && getAlertType() == that.getAlertType() && Objects.equals(getStartTime(), that.getStartTime()) && Objects.equals(getEndTime(), that.getEndTime())
                && Objects.equals(getFrequency(), that.getFrequency()) && Objects.equals(getExchanges(), that.getExchanges()) && getPriority() == that.getPriority()
                && Objects.equals(getClientId(), that.getClientId()) && Objects.equals(getCustomizations(), that.getCustomizations());
    }
}



