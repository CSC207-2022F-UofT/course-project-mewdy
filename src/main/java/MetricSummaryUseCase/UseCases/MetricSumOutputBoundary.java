package MetricSummaryUseCase.UseCases;

import MetricSummaryUseCase.Models.MetricSumResponseModel;
import MetricSummaryUseCase.Presenters.MetricSumViewModel;

public interface MetricSumOutputBoundary {
    MetricSumViewModel prepareSuccessView(MetricSumResponseModel responseModel);

    MetricSumViewModel prepareDataSumFail(String error);
}
