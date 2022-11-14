package Models;

public class MetricDelRequestModel {

    private String metricName;

    public MetricDelRequestModel(String metricName) {
        this.metricName = metricName;
    }

    public String getMetricName() {
        return this.metricName;
    }

    public void setMetricName(String metricName) {
        this.metricName = metricName;
    }
}
