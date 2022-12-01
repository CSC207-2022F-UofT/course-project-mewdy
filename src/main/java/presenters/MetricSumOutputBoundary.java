package presenters;

import models.MetricSumResponseModel;

/**
 * Interface for the MetricSum presenter class
 */

public interface MetricSumOutputBoundary {

    /**
     * prepareSuccesView prepares the success view for the MetricSum use case
     *
     * @param responseModel represents the response model for the MetricSum use case
     * @return the success view for the MetricSum use case
     */
    MetricSumViewModel prepareSuccessView(MetricSumResponseModel responseModel);

    /**
     * prepareDataSumFail prepares the fail view for the MetricSum use case
     *
     * @param error represents the error message
     * @return the fail view for the MetricSum use case
     */
    MetricSumViewModel prepareDataSumFail(String error);
}
