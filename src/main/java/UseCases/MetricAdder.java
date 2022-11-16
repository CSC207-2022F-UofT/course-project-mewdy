package UseCases;

import Entities.DataPoint;
import Entities.Metric;
import Entities.MetricStorageInterface;
import Models.AddMetricRequestModel;
import Models.AddMetricResponseModel;

import java.util.ArrayList;

public class MetricAdder implements AddMetricInputBoundary{

    final AddMetricOutputBoundary presenter;
    final MetricStorageInterface metricStorage;

    public MetricAdder(AddMetricOutputBoundary presenter, MetricStorageInterface metricStorage) {
        this.presenter = presenter;
        this.metricStorage = metricStorage;
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

    public static Metric createMetric(String name, double upperBound, double lowerBound) {
        return new Metric(name, upperBound, lowerBound);
    }
    /**
    *
     */
    @Override
    public AddMetricResponseModel addMetric(AddMetricRequestModel requestModel) throws Exception {
        MetricStorageInterface metricStorage = requestModel.getStorage();
        try {
            for (Metric m : metricStorage.getMetricList()) {
                if (m.getName().equals(requestModel.getMetricName())) {
                    return presenter.metricAddedFailureView("Metric already exists.");
                }
            }
            if (requestModel.getDataPointList().isEmpty()) {
                Metric metric = createMetric(requestModel.getMetricName(), requestModel.getUpperBound(), requestModel.getLowerBound());
            } else if (requestModel.getDataPointList().size() >= 1) {
                Metric metric = createMetric(requestModel.getMetricName(), requestModel.getDataPointList(), requestModel.getUpperBound(), requestModel.getLowerBound());
            }

            return presenter.metricAddedSuccessView(new AddMetricResponseModel(requestModel.getMetricName()));
    }
        catch (Exception e) {
            return presenter.metricAddedFailureView("Metric failed to add.");
        }
    }
}

