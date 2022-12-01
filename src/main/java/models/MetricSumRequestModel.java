package models;

/**
 * Class that represents the RequestModel for MetricSum
 */
public class MetricSumRequestModel {

    private String metricName;

    /**
     * Constructor for MetricSumRequestModel
     *
     * @param metricName represents the name of the metric
     */
    public MetricSumRequestModel(String metricName){
        this.metricName = metricName;
    }

    /**
     * Getter for metricName
     *
     * @return the name of the metric
     */
    public String getMetricName(){
        return this.metricName;
    }
}
