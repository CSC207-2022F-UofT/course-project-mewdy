package models;


/**
 * Class that represents the RequestModel for DataLogger
 */
public class DataLoggerRequestModel {

    private final String metricName;
    private final double value;

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
