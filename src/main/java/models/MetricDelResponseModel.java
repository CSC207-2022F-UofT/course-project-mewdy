package models;

public class MetricDelResponseModel {

    private String metricName;
    private int numDataPoints;

    //Response model constructor
    public MetricDelResponseModel(String metricName, int numDataPoints) {
        this.metricName = metricName;
        this.numDataPoints = numDataPoints;
    }

    //Returns the name of the selected metric
    public String getMetricName() {
        return this.metricName;
    }

    //Returns the number of data points that were associated with the selected metric
    public int getNumDataPoints() {
        return this.numDataPoints;
    }
}
