package controllers;

import models.AddMetricRequestModel;
import models.AddMetricResponseModel;
import presenters.AddMetricFail;
import use_cases.AddMetricInputBoundary;


public class AddMetricController {

    private final AddMetricInputBoundary inputBoundary;

    public AddMetricController(AddMetricInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    public AddMetricResponseModel addMetric(String metricName, double upperBound, double lowerBound) {
        try {
            AddMetricRequestModel requestModel = new AddMetricRequestModel(metricName, upperBound, lowerBound);
            return this.inputBoundary.addMetric(requestModel);
        } catch (Exception e) {
            throw new AddMetricFail("Metric failed to add.");
        }
    }
}
