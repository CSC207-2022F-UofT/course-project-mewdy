package use_cases;

import models.MetricSumResponseModel;
import presenters.MetricSumViewModel;

public interface MetricSumOutputBoundary {
    MetricSumViewModel prepareSuccessView(MetricSumResponseModel responseModel);

    MetricSumViewModel prepareDataSumFail(String error);
}