package use_cases;

import models.AddMetricResponseModel;

/**
 * AddMetricOutputBoundary is the interface for the presenter class
 */
public interface AddMetricOutputBoundary {

    /**
     * @param responseModel contains the name of the metric that was added and a success message
     * @return returns a successview
     */
    AddMetricResponseModel metricAddedSuccessView(AddMetricResponseModel responseModel);

    /**
     * @param errorMessage contains the error message
     * @return returns a failview
     */
    AddMetricResponseModel metricAddedFailureView(String errorMessage);


}
