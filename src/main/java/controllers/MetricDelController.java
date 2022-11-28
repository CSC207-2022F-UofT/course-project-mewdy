package controllers;

import models.MetricDelResponseModel;
import models.MetricDelRequestModel;
import use_cases.MetricDelInputBoundary;

public class MetricDelController {
    final MetricDelInputBoundary metricDeleter;

    //Controller constructor
    public MetricDelController(MetricDelInputBoundary metricDeleter) {
        this.metricDeleter = metricDeleter;
    }

    //Calls the use case interactor, MetricDeleter
    public MetricDelResponseModel create(String metricName) {
        MetricDelRequestModel requestModel = new MetricDelRequestModel(metricName);
        return metricDeleter.create(requestModel);
    }
}
