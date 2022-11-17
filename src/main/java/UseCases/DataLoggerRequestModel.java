package UseCases;

import Entities.MetricStorageInterface;

public class DataLoggerRequestModel {

    private String metricName;
    private double value;
    private MetricStorageInterface storage;

    public DataLoggerRequestModel(String metricName, double value, MetricStorageInterface storage){
        // sends necessary values to the DataLogger use case
        this.metricName = metricName;
        this.value = value;
        this.storage = storage;
    }

    public double getValue() {
        return this.value;
    }

    public String getMetricName() {
        return this.metricName;
    }

    public MetricStorageInterface getStorage() {
        return this.storage;
    }
}
