package use_cases;

import models.MetricSumRequestModel;
import presenters.MetricSumViewModel;

/**
 * Abstract decorator class (from decorator design pattern) for MetricSummarizer. Classes that extend this class will
 * modify functionality of getMetricSummary.
 */
public abstract class MetricSummarizerDecorator implements MetricSumInputBoundary{

    protected MetricSumInputBoundary metricSummarizer;

    public MetricSummarizerDecorator(MetricSumInputBoundary metricSummarizer){
        this.metricSummarizer = metricSummarizer;
    }

    @Override
    public MetricSumViewModel getMetricSummary(MetricSumRequestModel requestModel){
        return metricSummarizer.getMetricSummary(requestModel);}
}
