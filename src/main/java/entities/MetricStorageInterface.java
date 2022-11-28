package entities;

import java.io.File;
import java.util.ArrayList;

/**
 * Interface for MetricStorage
 */
public interface MetricStorageInterface {
    /**
     * setPath sets the path where MetricStorage is stored
     *
     * @param path represents the path to the file
     */
    void setPath(File path);

    /**
     * addMetric adds a metric to the MetricStorage
     *
     * @param metric represents the metric to be added to the MetricStorage
     */
    void addMetric(Metric metric);

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
    Metric getMetric(String metricName) throws Exception;

    /**
     * getPath returns the path of the MetricStorage
     *
     * @return the path of the MetricStorage
     */
    File getPath();

    /**
     * getSaveStatus returns the save status of the MetricStorage
     *
     * @return a boolean representing the save status of the MetricStorage
     */
    Boolean getSaveStatus();

}