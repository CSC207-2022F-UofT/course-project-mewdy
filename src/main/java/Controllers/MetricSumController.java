package Controllers;

import Models.MetricSumRequestModel;
import Presenters.MetricSumViewModel;
import UseCases.MetricSumInputBoundary;

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
