package controllers;

import models.AddMetricRequestModel;
import models.AddMetricResponseModel;
import presenters.AddMetricFail;
import use_cases.AddMetricInputBoundary;

/**
 * Controller for MetricAdder
 */

public class AddMetricController {

    private final AddMetricInputBoundary inputBoundary;

    /**
     *
     * @param inputBoundary represents the input for the controller
     * Initializes the controller for MetricAdder.
     */

    public AddMetricController(AddMetricInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    /**
     *
     * @param metricName represents the metricname.
     * @param upperBound represents the upperbound.
     * @param lowerBound represents the lowerbound
     * addMetric passes the parameters for a Metric into the use case interactor
     */
    public AddMetricResponseModel addMetric(String metricName, double upperBound, double lowerBound) {
        try {
            AddMetricRequestModel requestModel = new AddMetricRequestModel(metricName, upperBound, lowerBound);
            return this.inputBoundary.addMetric(requestModel);
        } catch (Exception e) {
            throw new AddMetricFail("Metric failed to add.");
        }
    }
}
