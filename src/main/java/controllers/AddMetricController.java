package controllers;

import models.AddMetricRequestModel;
import models.AddMetricResponseModel;
import use_cases.AddMetricInputBoundary;


public class AddMetricController {

    private final AddMetricInputBoundary inputBoundary;

    public AddMetricController(AddMetricInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    public AddMetricResponseModel addMetric(String metricName, double upperBound, double lowerBound) {
        AddMetricRequestModel requestModel = new AddMetricRequestModel(metricName, upperBound, lowerBound);
        return this.inputBoundary.addMetric(requestModel);

    }
}
