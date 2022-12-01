package presenters;

import models.AddMetricResponseModel;

/**
 * Interface for AddMetricPresenter
 */
public interface AddMetricOutputBoundary {

    /**
     * metricAddedSuccessView returns the response model for a successful metric addition
     *
     * @param responseModel represents the response model
     * @return the successful response model
     */
    AddMetricResponseModel metricAddedSuccessView(AddMetricResponseModel responseModel);

    /**
     * metricAddedFailureView returns the response model for a failed metric addition
     *
     * @param errorMessage represents the error message
     * @return the failed response model
     */
    AddMetricResponseModel metricAddedFailureView(String errorMessage);


}
