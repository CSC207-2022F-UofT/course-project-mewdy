package MetricSummaryUseCase.UseCases;

import MetricSummaryUseCase.Models.MetricSumRequestModel;
import MetricSummaryUseCase.Presenters.MetricSumViewModel;

public interface MetricSumInputBoundary {
    MetricSumViewModel getMetricSummary(MetricSumRequestModel requestModel);
}
