package UseCases;

public class DataLoggerRequestModel {

    private String metricName;
    private double value;

    public DataLoggerRequestModel(String metricName, double value){
        this.metricName = metricName;
        this.value = value;
    }

    public double getValue() {
        return this.value;
    }

    public String getMetricName() {
        return this.metricName;
    }
}
