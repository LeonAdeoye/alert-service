package com.leon.models;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "AlertTypeDetail")
public class AlertTypeDetail {
    private UUID id;
    private Classification classification;
    private String explanation;
    private String expression;
    private String messageTemplate;
    private AlertType alertType;

    public AlertTypeDetail()
    {
        this.id = UUID.randomUUID();
        this.classification = Classification.ORDER;
        this.explanation = "";
        this.expression = "";
        this.messageTemplate = "";
        this.alertType = AlertType.ORDER_REJECTIONS;
    }

    public UUID getId()
    {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Classification getClassification() {
        return classification;
    }

    public void setClassification(Classification classification) {
        this.classification = classification;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getMessageTemplate() {
        return messageTemplate;
    }

    public void setMessageTemplate(String messageTemplate) {
        this.messageTemplate = messageTemplate;
    }

    public AlertType getAlertType() {
        return alertType;
    }

    public void setAlertType(AlertType alertType) {
        this.alertType = alertType;
    }

    @Override
    public String toString() {
        return "AlertTypeDetail{" +
                "id=" + id +
                ", classification=" + classification +
                ", explanation='" + explanation + '\'' +
                ", expression='" + expression + '\'' +
                ", messageTemplate='" + messageTemplate + '\'' +
                ", alertType=" + alertType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AlertTypeDetail)) return false;
        AlertTypeDetail that = (AlertTypeDetail) o;
        return id.equals(that.id) &&
                classification == that.classification &&
                explanation.equals(that.explanation) &&
                expression.equals(that.expression) &&
                messageTemplate.equals(that.messageTemplate) &&
                alertType == that.alertType;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + classification.hashCode();
        result = 31 * result + explanation.hashCode();
        result = 31 * result + expression.hashCode();
        result = 31 * result + messageTemplate.hashCode();
        result = 31 * result + alertType.hashCode();
        return result;
    }
}
