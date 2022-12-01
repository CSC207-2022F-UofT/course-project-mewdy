package models;

/**
 * Class that represents the RequestModel for MetricDel
 */
public class MetricDelRequestModel {

    private String metricName;

    /**
     * Constructor for MetricDelRequestModel
     *
     * @param metricName represents the name of the metric to be deleted
     */
    public MetricDelRequestModel(String metricName) {
        this.metricName = metricName;
    }

    /**
     * Getter for metricName
     *
     * @return the name of the metric to be deleted
     */
    public String getMetricName() {
        return this.metricName;
    }

    /**
     * Setter for metricName
     *
     * @param metricName represents the name of the metric to be changed
     */
    public void setMetricName(String metricName) {
        this.metricName = metricName;
    }
}
