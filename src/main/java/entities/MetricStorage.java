package entities;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

public class MetricStorage implements MetricStorageInterface {

    private final ArrayList<Metric> METRICLIST;
    private File location;
    private boolean saved;

    public MetricStorage() {
        this.METRICLIST = new ArrayList<>();
        this.location = new File("./metrics");
        this.saved = true; // if storage is empty, nothing to save
    }

    @Override
    public void setPath(File path) {
        // Sets the path to the file where the metrics will be saved
        String folder = path.getPath();
        if (!(folder.endsWith(File.separator + "metrics") || folder.endsWith(File.separator + "metrics/")))
            folder += File.separator + "metrics";
        System.out.println(folder);
        this.location = new File(folder);
    }

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

    @Override
    public void removeDataPoint(String metricName) {
        //this method removes the most recent Entities.DataPoint from a chosen Entities.Metric based on its given name
        for (Metric metric : this.METRICLIST) {
            if (Objects.equals(metric.getName(), metricName)) {
                metric.popDataPoint();
                break;
            }
        }
        this.saved = false;
    }

    @Override
    public void save() {
        //method to indicate that there are no unsaved changes
        this.saved = true;
    }

    @Override
    public ArrayList<Metric> getMetricList() {
        //getter method for returning a list of Metrics
        return this.METRICLIST;
    }

    @Override
    public Metric getMetric(String metricName) throws NullPointerException {
        for (Metric metric : this.METRICLIST) {
            if (metric.getName().equalsIgnoreCase(metricName)) {
                return metric;
            }

        }
        throw new NullPointerException("No Metric found with name: " + metricName);
    }

    public File getPath() {
        return this.location;
    }

    @Override
    public Boolean getSaveStatus() {
        return this.saved;
    }

    @Override
    public ArrayList<DataPoint> getDataPointList(String metricName) {
        //getter method that returns a list of DataPoints from an Entities.Metric in metricList
        ArrayList<DataPoint> returnValue = null;
        for (Metric metric : this.METRICLIST) {
            if (Objects.equals(metric.getName(), metricName)) {
                returnValue = metric.getDataPoints();
                break;
            }
        }
        return returnValue;
    }

    public static class MetricComparator implements Comparator<Metric> {

        public int compare(Metric m1, Metric m2) {
            return m1.getName().compareTo(m2.getName());
        }
    }
}