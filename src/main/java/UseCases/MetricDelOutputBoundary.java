package UseCases;

import Models.MetricDelResponseModel;

public interface MetricDelOutputBoundary {
    MetricDelResponseModel prepareSuccessView(MetricDelResponseModel responseModel);

    MetricDelResponseModel prepareMetricDelFail(String error);
}
