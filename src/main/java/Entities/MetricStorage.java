package Entities;

import java.util.ArrayList;
import java.util.Objects;

public class MetricStorage implements MetricStorageInterface{

    private ArrayList<Metric> metricList;

    public MetricStorage(){
        this.metricList = new ArrayList<Metric>();
    }

    @Override
    public void addMetric(Metric metric) {
        //this method adds an Entities.Metric to the end of the metricList
        this.metricList.add(metric);
    }

    @Override
    public void addDataPoint(String metricName, DataPoint dataPoint) {
        //this method inserts an Entities.DataPoint into an Entities.Metric specified by name
        //this method will not rename the metricName contained in the dataPoint class, that responsibility
        //should lie within wherever the Entities.DataPoint is constructed
        for (Metric metric: this.metricList) {
            if (Objects.equals(metric.getName(), metricName)) {
                metric.addDataPoint(dataPoint);
                break;
            }
        }
    }

    @Override
    public void removeDataPoint(String metricName) {
        //this method removes the most recent Entities.DataPoint from a chosen Entities.Metric based on its given name
        for (Metric metric: this.metricList) {
            if (Objects.equals(metric.getName(), metricName)) {
                break;
            }
        }
    }

    @Override
    public ArrayList<Metric> getMetricList() {
        //getter method for returning a list of Metrics
        return this.metricList;
    }

    @Override
    public Metric getMetric(String metricName) throws Exception {
        for (Metric metric:this.metricList) {
            if (metric.getName().equalsIgnoreCase(metricName)) {
                return metric;
            }

        }
        throw new Exception("No Metric found with name: " + metricName);
    }

    @Override
    public ArrayList<DataPoint> getDataPointList(String metricName) {
        //getter method that returns a list of DataPoints from an Entities.Metric in metricList
        ArrayList<DataPoint> returnValue = null;
        for (Metric metric : this.metricList) {
            if (Objects.equals(metric.getName(), metricName)) {
                returnValue = metric.getDataPoints();
                break;
            }
        }
        return returnValue;
    }
}