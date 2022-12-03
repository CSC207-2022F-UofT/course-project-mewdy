package use_cases;

import models.MetricSumRequestModel;
import presenters.MetricSumViewModel;

/**
 * Interface for the MetricSum use case
 */
public interface MetricSumInputBoundary {

    /**
     * getMetricSummary returns a summary of a given metric
     *
     * @param requestModel represents the request model for the MetricSum use case
     * @return MetricSumViewModel representing the response model for the MetricSum use case
     */
    MetricSumViewModel getMetricSummary(MetricSumRequestModel requestModel);
}
