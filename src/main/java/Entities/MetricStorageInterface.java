package Entities;

import java.util.ArrayList;

public interface MetricStorageInterface {
    void addMetric(Metric metric);
    void addDataPoint(String metricName, DataPoint dataPoint);
    void removeDataPoint(String metricName);
    ArrayList<Metric> getMetricList();
    Metric getMetric(String metricName) throws Exception;
    ArrayList<DataPoint> getDataPointList(String metricName);
    Metric getMetric(String metricName) throws Exception;

}