package presenters;

import models.MetricSumResponseModel;

public interface MetricSumOutputBoundary {
    MetricSumViewModel prepareSuccessView(MetricSumResponseModel responseModel);

    MetricSumViewModel prepareDataSumFail(String error);
}
