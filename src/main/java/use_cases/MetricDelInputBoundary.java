package use_cases;

import models.MetricDelRequestModel;
import models.MetricDelResponseModel;

/**
 * Interface for the MetricDeleter use case
 */
public interface MetricDelInputBoundary {

    /**
     * deleteMetric deletes a metric from the metric storage
     *
     * @param requestModel represents the request model for the MetricDeleter use case
     * @return MetricDelResponseModel representing the response model for the MetricDeleter use case
     */
    MetricDelResponseModel create(MetricDelRequestModel requestModel);
}
