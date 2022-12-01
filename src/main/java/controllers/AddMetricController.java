package controllers;

import models.AddMetricRequestModel;
import models.AddMetricResponseModel;
import use_cases.AddMetricInputBoundary;


/**
 * Controller class for MetricAdder
 */
public class AddMetricController {

    private final AddMetricInputBoundary inputBoundary;

    /**
     * Constructor method for the AddMetricController
     *
     * @param inputBoundary the input boundary for adding a metric
     */
    public AddMetricController(AddMetricInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    /**
     * Calls the use case interactor to add a metric
     *
     * @param metricName represents the name of the metric
     * @param upperBound represents the upper bound of the metric
     * @param lowerBound represents the lower bound of the metric
     * @return is a AddMetricResponseModel
     */
    public AddMetricResponseModel addMetric(String metricName, double upperBound, double lowerBound) {
        AddMetricRequestModel requestModel = new AddMetricRequestModel(metricName, upperBound, lowerBound);
        return this.inputBoundary.addMetric(requestModel);

    }
}
