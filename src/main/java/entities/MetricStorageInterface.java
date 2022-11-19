package entities;

import java.io.File;
import java.util.ArrayList;

public interface MetricStorageInterface {
    void setPath(File path);
    void addMetric(Metric metric);
    void addDataPoint(String metricName, DataPoint dataPoint);
    void removeDataPoint(String metricName);
    void save();
    ArrayList<Metric> getMetricList();
    ArrayList<DataPoint> getDataPointList(String metricName);
    Metric getMetric(String metricName) throws Exception;
    File getPath();
    Boolean getSaveStatus();

}