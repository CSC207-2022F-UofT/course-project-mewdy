package MetricSummaryUseCase.Controllers;

import MetricSummaryUseCase.Models.MetricSumRequestModel;
import MetricSummaryUseCase.Presenters.MetricSumViewModel;
import MetricSummaryUseCase.UseCases.MetricSumInputBoundary;

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
