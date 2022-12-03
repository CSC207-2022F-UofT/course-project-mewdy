package use_cases;

import entities.Metric;
import entities.MetricStorageInterface;
import models.AddMetricRequestModel;
import models.AddMetricResponseModel;
import presenters.AddMetricOutputBoundary;

/**
 * Class that represents the MetricAdder use case that is responsible for adding a metric to the metric storage
 */
public class MetricAdder implements AddMetricInputBoundary{

    final AddMetricOutputBoundary presenter;
    final MetricStorageInterface metricStorage;

    /**
     * Constructor for MetricAdder
     *
     * @param metricStorage represents the metric storage that the use case will interact with
     * @param presenter represents the presenter that will present the output
     */
    public MetricAdder(AddMetricOutputBoundary presenter, MetricStorageInterface metricStorage) {
        this.presenter = presenter;
        this.metricStorage = metricStorage;
    }
    /**
     * Creates a new Metric with name and a upper and lower bound. All metrics intialized are assumed to have no
     * existing dataPoints.
     *
     * @param name name of the metric
     * @param upperBound upperbound of the metric
     * @param lowerBound lowerbound of the metric
     * @return returns a new Metric object
     */
    public static Metric createMetric(String name, double upperBound, double lowerBound) {
        return new Metric(name, upperBound, lowerBound);
    }
    /**
     * addMetric adds a new metric into the metricStorage and checks whether or not the metric already exists.
     * If the metric already exists, then do not add the metric and return a failview.
     *
     * @param requestModel represents the request model for the AddMetric use case
     * @return the response model for the AddMetric use case
     */
    @Override
    public AddMetricResponseModel addMetric(AddMetricRequestModel requestModel) {
        for (Metric m : metricStorage.getMetricList()) {
            if (m.getName().equalsIgnoreCase(requestModel.getMetricName())) {
                return presenter.metricAddedFailureView("Metric already exists!");
            }
        }
        AddMetricResponseModel responseModel = new AddMetricResponseModel(requestModel.getMetricName(),
                " was added successfully!");

        Metric metric = createMetric(requestModel.getMetricName(),
                requestModel.getUpperBound(), requestModel.getLowerBound());

        this.metricStorage.addMetric(metric);
        return presenter.metricAddedSuccessView(responseModel);
    }
}



