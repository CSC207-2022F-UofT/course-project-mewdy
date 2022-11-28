package presenters;

import models.MetricDelResponseModel;

public interface MetricDelOutputBoundary {
    MetricDelResponseModel prepareSuccessView(MetricDelResponseModel responseModel);

    MetricDelResponseModel prepareMetricDelFail(String error);
}
