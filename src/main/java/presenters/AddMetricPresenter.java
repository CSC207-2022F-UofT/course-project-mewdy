package presenters;

import models.AddMetricResponseModel;

// Subclass of AddMetricOutputBoundary
public class AddMetricPresenter implements AddMetricOutputBoundary {

    @Override
    public AddMetricResponseModel metricAddedSuccessView(AddMetricResponseModel responseModel) {
        String message = "Metric with the name " + responseModel.getMetricName() + responseModel.getMessage();
        return new AddMetricResponseModel(message);
    }

    @Override
    public AddMetricResponseModel metricAddedFailureView(String errorMessage) {
        return new AddMetricResponseModel(errorMessage);
    }
}

