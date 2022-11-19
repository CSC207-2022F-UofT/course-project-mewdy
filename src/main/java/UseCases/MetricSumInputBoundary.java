package UseCases;

import Models.MetricSumRequestModel;
import Presenters.MetricSumViewModel;

public interface MetricSumInputBoundary {
    MetricSumViewModel getMetricSummary(MetricSumRequestModel requestModel);
}
