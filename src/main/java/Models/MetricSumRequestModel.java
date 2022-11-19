package Models;

public class MetricSumRequestModel {

    private String metricName;

    public MetricSumRequestModel(String metricName){
        this.metricName = metricName;
    }

    public String getMetricName(){
        return this.metricName;
    }
}
