package Models;

public class MetricDelResponseModel {

    private String metricName;
    private int numDataPoints;

    public MetricDelResponseModel(String metricName, int numDataPoints) {
        this.metricName = metricName;
        this.numDataPoints = numDataPoints;
    }

    public String getMetricName() {
        return this.metricName;
    }

    public int getNumDataPoints() {
        return this.numDataPoints;
    }
}
