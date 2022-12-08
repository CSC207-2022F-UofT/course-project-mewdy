package use_cases;

import models.AddMetricRequestModel;
import models.AddMetricResponseModel;


/**
 * Interface for the MetricAdder
 */
public interface AddMetricInputBoundary {
    /**
     * addMetric returns a response model with the metric name and additional information
     *
     * @param requestModel contains the name, upper and lower bounds of the metric that wants to be added.
     * @return returns a response model
     */
    AddMetricResponseModel addMetric(AddMetricRequestModel requestModel);
}
