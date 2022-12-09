package use_cases;

import models.MetricDelRequestModel;
import models.MetricDelResponseModel;

/**
 * Interface for the Delete Metric use case
 */
public interface MetricDelInputBoundary {

    /**
     * metricDelete represents the main method to be implemented by the use case interactor that will fulfill the
     * delete metric use case function
     *
     * @param requestModel contains user input data. Stores name of metric to be deleted
     * @return MetricDelResponseModel which will contain a message depending on success or failure.
     */
    MetricDelResponseModel metricDelete(MetricDelRequestModel requestModel);
}
