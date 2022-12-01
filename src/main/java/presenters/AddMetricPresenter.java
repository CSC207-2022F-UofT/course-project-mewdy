package presenters;

import models.AddMetricResponseModel;

/**
 * Subclass of AddMetricPresenterOutputBoundary
 */

public class AddMetricPresenter implements AddMetricOutputBoundary {

    /**
     * metricAddedSuccessView returns the response model for a successful metric addition
     *
     * @param responseModel represents the response model
     * @return the successful response model
     */
    @Override
    public AddMetricResponseModel metricAddedSuccessView(AddMetricResponseModel responseModel) {
        String message = "Metric with the name " + responseModel.getMetricName() + responseModel.getMessage();
        return new AddMetricResponseModel(message);
    }
    /**
     * metricAddedFailureView returns the response model for a failed metric addition
     *
     * @param errorMessage represents the error message
     * @return the failed response model
     */
    @Override
    public AddMetricResponseModel metricAddedFailureView(String errorMessage) {
        return new AddMetricResponseModel(errorMessage);
    }
}

