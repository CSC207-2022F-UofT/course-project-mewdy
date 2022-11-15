package Presenters;

import UseCases.AddMetricOutputBoundary;
import Models.AddMetricResponseModel;

public class AddMetricPresenter implements AddMetricOutputBoundary {

    @Override
    public AddMetricResponseModel metricAddedSuccessView(AddMetricResponseModel responseModel) {
        String message = "Metric " + responseModel.getMetricName() + " added successfully";
        return new AddMetricResponseModel(message);
    }
    @Override
    public AddMetricResponseModel metricAddedFailureView(String errorMessage) {
        return new AddMetricResponseModel(errorMessage);
    }
}


