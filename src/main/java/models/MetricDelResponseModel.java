package models;

/**
 * Class that represents the ResponseModel for MetricDel
 */
public class MetricDelResponseModel {

    private String metricName;
    private int numDataPoints;

    /**
     * Constructor for MetricDelResponseModel
     *
     * @param metricName represents the name of the metric
     * @param numDataPoints represents the number of data points deleted
     */
    public MetricDelResponseModel(String metricName, int numDataPoints) {
        this.metricName = metricName;
        this.numDataPoints = numDataPoints;
    }

    /**
     * Getter for metricName
     *
     * @return the name of the metric
     */
    public String getMetricName() {
        return this.metricName;
    }

    /**
     * getNumDataPoints returns the number of data points associated with the selected metric
     *
     * @return the number of data points associated with the selected metric
     */
    public int getNumDataPoints() {
        return this.numDataPoints;
    }
}
