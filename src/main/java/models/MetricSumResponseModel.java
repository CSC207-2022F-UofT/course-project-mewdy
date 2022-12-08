package models;

import java.util.ArrayList;

/**
 * Class that represents the ResponseModel for MetricSum
 */
public class MetricSumResponseModel {

    ArrayList<String> timePoints;
    ArrayList<Double> datalist;
    double metricAverage;
    int metricSize;
    String metricName;

    /**
     * Constructor for MetricSumResponseModel
     *
     * @param timePoints represents the time points
     * @param datalist represents the data list
     * @param metricAverage represents the metric average
     * @param metricSize represents the metric size
     * @param metricName represents the metric name
     */
    public MetricSumResponseModel(ArrayList<String> timePoints, ArrayList<Double> datalist, double metricAverage,
                                  int metricSize, String metricName){
        this.timePoints = timePoints;
        this.datalist = datalist;
        this.metricAverage = metricAverage;
        this.metricSize = metricSize;
        this.metricName = metricName;
    }

    /**
     * Getter for timePoints
     *
     * @return the time points
     */
    public ArrayList<String> getTimePoints(){return this.timePoints;}

    /**
     * Getter for datalist
     *
     * @return the data list
     */
    public ArrayList<Double> getDatalist(){return this.datalist;}

    /**
     * Getter for metricAverage
     *
     * @return the metric average
     */
    public double getMetricAverage(){return this.metricAverage;}

    /**
     * Getter for metricSize
     *
     * @return the metric size
     */
    public int getMetricSize(){return this.metricSize;}

    /**
     * Getter for metricName
     *
     * @return the metric name
     */
    public String getMetricName(){return this.metricName;}
}
