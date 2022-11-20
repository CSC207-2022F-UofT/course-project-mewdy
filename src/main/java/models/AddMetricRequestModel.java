package models;

import entities.DataPoint;

import java.util.ArrayList;

public class AddMetricRequestModel {

    private final String metricName;
    private final double upperBound;
    private final double lowerBound;
    private ArrayList<DataPoint> dataPointList;

    public AddMetricRequestModel(String metricName, double upperBound,
                                 double lowerBound) {
        this.metricName = metricName;
        this.upperBound = upperBound;
        this.lowerBound = lowerBound;
        this.dataPointList = new ArrayList<>();
    }

    public String getMetricName() {
        return metricName;
    }

    public double getUpperBound() {
        return upperBound;
    }

    public double getLowerBound() {
        return lowerBound;
    }
}