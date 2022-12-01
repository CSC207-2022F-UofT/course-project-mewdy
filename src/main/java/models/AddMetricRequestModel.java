package models;

import entities.DataPoint;

import java.util.ArrayList;

/**
 * Class that represents the RequestModel for AddMetric
 */
public class AddMetricRequestModel {

    private final String metricName;
    private final double upperBound;
    private final double lowerBound;
    private ArrayList<DataPoint> dataPointList;

    /**
     * Constructor for AddMetricRequestModel
     *
     * @param metricName represents the name of the metric
     * @param upperBound represents the upper bound of the metric
     * @param lowerBound represents the lower bound of the metric
     */
    public AddMetricRequestModel(String metricName, double upperBound,
                                 double lowerBound) {
        this.metricName = metricName;
        this.upperBound = upperBound;
        this.lowerBound = lowerBound;
        this.dataPointList = new ArrayList<>();
    }

    /**
     * Getter for metricName
     *
     * @return metricName
     */
    public String getMetricName() {
        return metricName;
    }

    /**
     * Getter for upperBound
     *
     * @return upperBound
     */
    public double getUpperBound() {
        return upperBound;
    }

    /**
     * Getter for lowerBound
     *
     * @return lowerBound
     */
    public double getLowerBound() {
        return lowerBound;
    }
}