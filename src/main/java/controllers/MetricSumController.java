package controllers;

import models.MetricSumRequestModel;
import presenters.MetricSumViewModel;
import use_cases.MetricSumInputBoundary;

public class MetricSumController {

    final MetricSumInputBoundary metricSummarizer;

    public MetricSumController(MetricSumInputBoundary metricSummarizer){
        this.metricSummarizer = metricSummarizer;
    }

    public MetricSumViewModel getMetricSummary(String metricName){
        MetricSumRequestModel requestModel = new MetricSumRequestModel(metricName);

        return this.metricSummarizer.getMetricSummary(requestModel);
    }
}
