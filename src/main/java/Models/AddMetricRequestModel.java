package Models;

import Entities.DataPoint;
import Entities.MetricStorageInterface;

import java.util.ArrayList;

public class AddMetricRequestModel {

    private final String metricName;
    private final double upperBound;
    private final double lowerBound;
    private final ArrayList<DataPoint> dataPointList;
    private final MetricStorageInterface metricStorage;

    public AddMetricRequestModel(String metricName, ArrayList<DataPoint> dataPointList, double upperBound,
                                 double lowerBound, MetricStorageInterface metricStorage) {
        this.metricName = metricName;
        this.upperBound = upperBound;
        this.lowerBound = lowerBound;
        this.dataPointList = dataPointList;
        this.metricStorage = metricStorage;
    }

    public String getMetricName() {
        return metricName;
    }

    public ArrayList<DataPoint> getDataPointList() {
        return dataPointList;
    }

    public double getUpperBound() {
        return upperBound;
    }

    public double getLowerBound() {
        return lowerBound;
    }

    public MetricStorageInterface getStorage() {
        return metricStorage;
    }

}
