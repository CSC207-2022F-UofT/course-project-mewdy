package presenters;

import models.AddMetricResponseModel;

public interface AddMetricOutputBoundary {

    AddMetricResponseModel metricAddedSuccessView(AddMetricResponseModel responseModel);

    AddMetricResponseModel metricAddedFailureView(String errorMessage);


}
