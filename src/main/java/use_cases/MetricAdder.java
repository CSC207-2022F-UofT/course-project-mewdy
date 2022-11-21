package use_cases;

import entities.Metric;
import entities.MetricStorageInterface;
import models.AddMetricRequestModel;
import models.AddMetricResponseModel;

public class MetricAdder implements AddMetricInputBoundary{

    final AddMetricOutputBoundary presenter;
    final MetricStorageInterface metricStorage;

    /**
     * Constructor for use_case.MetricAdder
     * @param presenter is the presenter class
     * @param metricStorage is where the metrics are stored/accessed
     */
    public MetricAdder(AddMetricOutputBoundary presenter, MetricStorageInterface metricStorage) {
        this.presenter = presenter;
        this.metricStorage = metricStorage;
    }
    /**
     * @param name name of the metric
     * @param upperBound upperbound of the metric
     * @param lowerBound lowerbound of the metric
     * Creates a new Metric with name and a upper and lower bound. All metrics intialized are assumed to have no
     * existing dataPoints.
    */
    public static Metric createMetric(String name, double upperBound, double lowerBound) {
        return new Metric(name, upperBound, lowerBound);
    }
    /**
    * @param requestModel contains the name, upper and lower bounds of the metric that wants to be added.
     * addMetric adds a new metric into the metricStorage and checks whether or not the metric already exists.
     * If the metric already exists, then do not add the metric and return a failview.
     */
    @Override
    public AddMetricResponseModel addMetric(AddMetricRequestModel requestModel) {
            for (Metric m : metricStorage.getMetricList()) {
                if (m.getName().equalsIgnoreCase(requestModel.getMetricName())) {
                    return presenter.metricAddedFailureView("Metric already exists!");
                }
            }
            AddMetricResponseModel responseModel = new AddMetricResponseModel(requestModel.getMetricName(), " was added successfully!");
            Metric metric = createMetric(requestModel.getMetricName(), requestModel.getUpperBound(), requestModel.getLowerBound());
            this.metricStorage.addMetric(metric);
            return presenter.metricAddedSuccessView(responseModel);
            }
        }



