package presenters;

import models.MetricDelResponseModel;

/**
 * Interface for MetricDelPresenter
 */

public interface MetricDelOutputBoundary {

    /**
     * prepareSuccessView returns the success view for the metric deletion.
     *
     * @param responseModel represents the response model for the metric deletion.
     * @return the success view for the metric deletion.
     */
    MetricDelResponseModel prepareSuccessView(MetricDelResponseModel responseModel);

    /**
     * prepareMetricDelFail returns the failure view for the metric deletion.
     *
     * @param error represents the error message for the metric deletion.
     * @return the failure view for the metric deletion.
     */
    MetricDelResponseModel prepareMetricDelFail(String error);
}
