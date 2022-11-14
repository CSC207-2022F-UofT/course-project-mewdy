package UseCases;

import Models.MetricSumResponseModel;
import Presenters.MetricSumViewModel;

public interface MetricSumOutputBoundary {
    MetricSumViewModel prepareSuccessView(MetricSumResponseModel responseModel);

    MetricSumViewModel prepareDataSumFail(String error);
}
