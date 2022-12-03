package controllers;

import models.MetricSumRequestModel;
import presenters.MetricSumViewModel;
import use_cases.MetricSumInputBoundary;

/**
 * Controller for the MetricSum use case
 */
public class MetricSumController {

    final MetricSumInputBoundary metricSummarizer;

    /**
     * Constructor for MetricSumController
     *
     * @param metricSummarizer represents the input boundary for the MetricSum use case
     */
    public MetricSumController(MetricSumInputBoundary metricSummarizer){
        this.metricSummarizer = metricSummarizer;
    }

    /**
     * getMetricSummary calls the use case interactor, MetricSum to get the summary of a metric
     *
     * @param metricName represents the name of the metric to be summarized
     * @return the response model for getting the summary of a metric
     */
    public MetricSumViewModel getMetricSummary(String metricName){
        MetricSumRequestModel requestModel = new MetricSumRequestModel(metricName);

        return this.metricSummarizer.getMetricSummary(requestModel);
    }
}
