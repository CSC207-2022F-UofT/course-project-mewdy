package presenters;

import use_cases.AddMetricOutputBoundary;
import models.AddMetricResponseModel;

// Subclass of AddMetricOutputBoundary
public class AddMetricPresenter implements AddMetricOutputBoundary {

    /**
     *
     * @param responseModel represents the response view
     * metricAddedSuccessView returns
     */
    @Override
    public AddMetricResponseModel metricAddedSuccessView(AddMetricResponseModel responseModel) {
        String message = "Metric " + responseModel.getMetricName() + " added successfully";
        return new AddMetricResponseModel(message);
    }

    /**
     *
     * @param errorMessage represents the error message being displayed
     * metricAddedFailureView returns the error message
     */
    @Override
    public AddMetricResponseModel metricAddedFailureView(String errorMessage) {
        return new AddMetricResponseModel(errorMessage);
    }
}

