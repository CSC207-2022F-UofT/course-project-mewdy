package use_cases;

import models.MetricSumRequestModel;
import presenters.MetricSumViewModel;

public interface MetricSumInputBoundary {
    MetricSumViewModel getMetricSummary(MetricSumRequestModel requestModel);
}
