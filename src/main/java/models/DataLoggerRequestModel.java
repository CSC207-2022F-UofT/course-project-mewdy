package models;

import entities.MetricStorageInterface;

/**
 * Class that represents the RequestModel for DataLogger
 */
public class DataLoggerRequestModel {

    private String metricName;
    private double value;
    private MetricStorageInterface storage;

    /**
     * Constructor for DataLoggerRequestModel
     *
     * @param metricName represents the name of the metric
     * @param value represents the value of the metric
     */
    public DataLoggerRequestModel(String metricName, double value){
        this.metricName = metricName;
        this.value = value;
    }

    /**
     * Getter for value
     *
     * @return value
     */
    public double getValue() {
        return this.value;
    }

    /**
     * Getter for metricName
     *
     * @return metricName
     */
    public String getMetricName() {
        return this.metricName;
    }
}
