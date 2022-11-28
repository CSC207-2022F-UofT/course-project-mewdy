package controllers;

import models.MetricDelResponseModel;
import models.MetricDelRequestModel;
import use_cases.MetricDelInputBoundary;

/**
 * Controller for the MetricDeleter use case
 */
public class MetricDelController {
    final MetricDelInputBoundary metricDeleter;

    /**
     * Constructor for MetricDelController
     *
     * @param metricDeleter represents the input boundary for the MetricDeleter use case
     */
    public MetricDelController(MetricDelInputBoundary metricDeleter) {
        this.metricDeleter = metricDeleter;
    }

    /**
     * ????????? Why is the method called create when this is the metric deleter use case?
     *
     * @param metricName represents the name of the metric to be deleted
     * @return MetricDelResponseModel
     */
    public MetricDelResponseModel create(String metricName) {
        MetricDelRequestModel requestModel = new MetricDelRequestModel(metricName);
        return metricDeleter.create(requestModel);
    }
}
