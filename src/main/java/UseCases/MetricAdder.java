package UseCases;

import Entities.DataPoint;
import Entities.Metric;
import Entities.MetricStorageInterface;
import Models.AddMetricRequestModel;
import Models.AddMetricResponseModel;

import java.util.ArrayList;

public class MetricAdder implements AddMetricInputBoundary{

    final AddMetricOutputBoundary presenter;

    public MetricAdder(AddMetricOutputBoundary presenter) {
        this.presenter = presenter;
    }
    /**
     * @param name name of the metric
     * @param dataPointList list of existing datapoints to import
     * @param upperBound upperbound of the metric
     * @param lowerBound lowerbound of the metric
     * Creates a new Metric with existing datapoints
     */
    public static Metric createMetric(String name, ArrayList<DataPoint> dataPointList, double upperBound, double lowerBound) {
        return new Metric(name, dataPointList, upperBound, lowerBound);
    }
    /**
    *
     */
    @Override
    public AddMetricResponseModel addMetric(AddMetricRequestModel requestModel, MetricStorageInterface storage) {

    }
}

