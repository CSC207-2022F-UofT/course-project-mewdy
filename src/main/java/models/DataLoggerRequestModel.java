package models;

import entities.MetricStorageInterface;

public class DataLoggerRequestModel {

    private String metricName;
    private double value;
    private MetricStorageInterface storage;

    public DataLoggerRequestModel(String metricName, double value) {
        // sends necessary values to the DataLogger use case
        this.metricName = metricName;
        this.value = value;
    }

    public double getValue() {
        return this.value;
    }

    public String getMetricName() {
        return this.metricName;
    }
}
