import java.util.ArrayList;

public interface MetricStorageInterface {
    void addMetric(Metric metric);
    void addDataPoint(String metricName, DataPoint dataPoint);
    void removeDataPoint(String metricName);
    ArrayList<Metric> getMetricList();
    ArrayList<DataPoint> getDataPointList(String metricName);

}
