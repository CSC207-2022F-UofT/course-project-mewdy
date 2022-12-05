package entities;

import java.io.File;
import java.util.ArrayList;

public interface MetricStorageInterface {
    void setPath(File path);

    void addMetric(Metric metric);
    void removeMetric(Metric metric);

    /**
     * addDataPoint adds a data point to the MetricStorage
     *
     * @param metricName represents name of the metric
     * @param dataPoint represents the data point to be added to the MetricStorage
     */
    void addDataPoint(String metricName, DataPoint dataPoint);

    /**
     * removeDataPoint removes a data point from the MetricStorage
     *
     * @param metricName represents name of the metric
     */
    void removeDataPoint(String metricName);

    /**
     * save saves the MetricStorage to the path
     */
    void save();

    /**
     * getMetricList returns the list of metrics in the MetricStorage
     *
     * @return the list of metrics in the MetricStorage
     */
    ArrayList<Metric> getMetricList();

    /**
     * getDataPointList returns the list of data points in the MetricStorage
     *
     * @param metricName represents name of the metric
     * @return the list of data points in the MetricStorage
     */
    ArrayList<DataPoint> getDataPointList(String metricName);

    /**
     * getMetric returns the metric in the MetricStorage
     *
     * @param metricName represents name of the metric
     * @return the metric in the MetricStorage
     */
    Metric getMetric(String metricName) throws NullPointerException;

    /**
     * getPath returns the path of the MetricStorage
     *
     * @return the path of the MetricStorage
     */
    File getPath();

    /**
     * setMetricGoal returns true if goal is successfully set for metric. Goal needs to be within defined bounds
     * of metric with metricName
     *
     * @param goal is the goal to be set
     * @param metricName is the name of metric for which we are setting the goal
     * @return true if goal is successfully set, false if not
     */
    boolean setMetricGoal(double goal, String metricName);

    Boolean getSaveStatus();

}