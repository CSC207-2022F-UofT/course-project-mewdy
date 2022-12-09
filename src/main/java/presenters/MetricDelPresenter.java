package presenters;

import models.MetricDelResponseModel;
import screens.MetricDeleterFailed;

/**
 * Presenter for the metric deleter use case that implements the output boundary interface.
 */

public class MetricDelPresenter implements MetricDelOutputBoundary {

    /**
     * prepareSuccessView prepares a success view model for the metric deleter use case.
     *
     * @param responseModel represents the response model for the metric deletion.
     * @return the success view model for the metric deleter use case.
     */
    @Override
    public MetricDelResponseModel prepareSuccessView(MetricDelResponseModel responseModel) {
        return responseModel;
    }

    /**
     * prepareMetricDeleterFail throws an MetricDeleterFailed exception when the metric cannot be deleted
     *
     * @param error represents the error message for the metric deletion.
     * @return the failure view model for the metric deleter use case.
     */
    @Override
    public MetricDelResponseModel prepareMetricDelFail(String error) {
        throw new MetricDeleterFailed(error);
    }
}
