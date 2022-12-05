package entities;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

/**
 * Class that represents MetricStorage and implements MetricStorageInterface
 */
public class MetricStorage implements MetricStorageInterface{

    private final ArrayList<Metric> METRICLIST;
    private File location;
    private boolean saved;

    /**
     * Constructor for MetricStorage
     */
    public MetricStorage(){
        this.METRICLIST = new ArrayList<>();
        this.location = new File("./metrics");
        this.saved = true; // if storage is empty, nothing to save
    }
    /**
     * setPath sets the location of the MetricStorage
     *
     * @param path represents the path to the file
     */
    @Override
    public void setPath(File path) {
        // Sets the path to the file where the metrics will be saved
        String folder = path.getPath();
        if (!(folder.endsWith(File.separator + "metrics")||folder.endsWith(File.separator + "metrics/")))
            folder += File.separator + "metrics";
        System.out.println(folder);
        this.location = new File(folder);
    }

    /**
     * addMetric adds a metric to the MetricStorage
     *
     * @param metric represents the metric to be added
     */
    @Override
    public void addMetric(Metric metric) {
        // this method adds Metric to the MetricStorage list and sorts the list
        this.METRICLIST.add(metric);
        this.METRICLIST.sort(new MetricComparator());
        this.saved = false;
    }

    @Override
    public void removeMetric(Metric metric) {
        // this method removes Metric from the MetricStorage list
        this.METRICLIST.remove(metric);
        this.saved = false;
    }

    /**
     * addDataPoint adds a dataPoint to the metric
     *
     * @param metricName represents the name of the metric
     * @param dataPoint represents the dataPoint to be added
     */
    @Override
    public void addDataPoint(String metricName, DataPoint dataPoint) {
        // this method inserts an Entities.DataPoint into an Entities.Metric specified by name
        // this method will not rename the metricName contained in the dataPoint class, that responsibility
        // should lie within wherever the Entities.DataPoint is constructed
        for (Metric metric : this.METRICLIST) {
            if (Objects.equals(metric.getName(), metricName)) {
                metric.addDataPoint(dataPoint);
                break;
            }
        }
        this.saved = false;
    }

    /**
     * removeDataPoint removes the most recent dataPoint from the metric
     *
     * @param metricName represents the name of the metric
     */
    @Override
    public void removeDataPoint(String metricName) {
        for (Metric metric: this.METRICLIST) {
            if (Objects.equals(metric.getName(), metricName)) {
                metric.popDataPoint();
                break;
            }
        }
        this.saved = false;
    }

    /**
     * save the MetricStorage to the location
     */
    @Override
    public void save() {
        this.saved = true;
    }

    /**
     * getMetricList returns the list of metrics
     *
     * @return the list of metrics in MetricStorage
     */
    @Override
    public ArrayList<Metric> getMetricList() {
        return this.METRICLIST;
    }

    /**
     * getMetric returns the metric with the given name
     *
     * @param metricName represents the name of the metric
     * @return the metric with the name metricName
     */
    @Override
    public Metric getMetric(String metricName) throws NullPointerException {
        for (Metric metric : this.METRICLIST) {
            if (metric.getName().equalsIgnoreCase(metricName)) {
                return metric;
            }

        }
        throw new NullPointerException("No Metric found with name: " + metricName);
    }

    /**
     * setMetricGoal returns true if goal is successfully set for metric. Goal needs to be within defined bounds
     * of metric with metricName.
     *
     * @param goal is the goal to be set
     * @param metricName is the name of metric for which we are setting the goal
     * @return true if goal is successfully set, false if not
     */
    @Override
    public boolean setMetricGoal(double goal, String metricName){
        Metric metric = getMetric(metricName);
        double upperBound = metric.getUpperBound();
        double lowerBound = metric.getLowerBound();
        if (goal >= lowerBound && goal <= upperBound) {
            metric.setGoal(goal);
            this.saved = false;
            return true;
        }
        return false;
    }

    /**
     * getPath returns the list of dataPoints in the metric
     *
     * @return the location of the MetricStorage
     */
    public File getPath() {
        return this.location;
    }

    /**
     * getSaveStatus returns the save status of the MetricStorage
     *
     * @return a boolean representing the save status of the MetricStorage
     */
    @Override
    public Boolean getSaveStatus() {
        return this.saved;
    }

    /**
     * getDataPointList returns the list of dataPoints in the metric
     *
     * @param metricName represents the name of the metric
     * @return the list of dataPoints in the metric
     */
    @Override
    public ArrayList<DataPoint> getDataPointList(String metricName) {
        ArrayList<DataPoint> returnValue = null;
        for (Metric metric : this.METRICLIST) {
            if (Objects.equals(metric.getName(), metricName)) {
                returnValue = metric.getDataPoints();
                break;
            }
        }
        return returnValue;
    }

    /**
     * Class that represents a comparator for metrics and implements Comparator
     */
    public static class MetricComparator implements Comparator<Metric> {

        /**
         * compare compares two metrics
         *
         * @param m1 the first Metric to be compared.
         * @param m2 the second Metric to be compared.
         * @return an integer representing the comparison of the two metrics
         */
        public int compare(Metric m1, Metric m2) {
            return m1.getName().compareTo(m2.getName());
        }
    }
}