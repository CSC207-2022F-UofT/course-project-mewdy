package UseCases;

import Models.AddMetricResponseModel;

public interface AddMetricOutputBoundary {

    AddMetricResponseModel metricAddedSuccessView(AddMetricResponseModel responseModel);
    AddMetricResponseModel metricAddedFailureView(String errorMessage);


}
