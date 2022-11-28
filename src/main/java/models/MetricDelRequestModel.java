package models;

public class MetricDelRequestModel {

    private String metricName;

    //Request model constructor
    public MetricDelRequestModel(String metricName) {
        this.metricName = metricName;
    }

    //Returns the name of the selected metric
    public String getMetricName() {
        return this.metricName;
    }

    //Setter method for the metricName of the request model
    public void setMetricName(String metricName) {
        this.metricName = metricName;
    }
}
