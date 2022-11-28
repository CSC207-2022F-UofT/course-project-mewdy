package models;

public class AddMetricResponseModel {

    private final String metricName;
    private final String message;

    public AddMetricResponseModel(String metricName, String message) {
        this.metricName = metricName;
        this.message = message;
    }

    public AddMetricResponseModel(String message) {
        this.metricName = "";
        this.message = message;
    }

    public String getMetricName() {
        return metricName;
    }

    public String getMessage() {
        return message;
    }
}