package controllers;

import models.MetricDelRequestModel;
import models.MetricDelResponseModel;
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
     * deleteMetric calls the use case interactor, MetricDeleter to delete a metric
     *
     * @param metricName represents the name of the metric to be deleted
     * @return the response model for deleting a metric
     */
    public MetricDelResponseModel deleteMetric(String metricName) {
        MetricDelRequestModel requestModel = new MetricDelRequestModel(metricName);
        return metricDeleter.metricDelete(requestModel);
    }
}
