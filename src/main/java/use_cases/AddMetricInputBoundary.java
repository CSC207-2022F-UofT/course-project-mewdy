package use_cases;

import models.AddMetricRequestModel;
import models.AddMetricResponseModel;


/**
 * AddMetricInputBoundary is the interface for the use case class
 */
public interface AddMetricInputBoundary {
    /**
     * @param requestModel contains the name, upper and lower bounds of the metric that wants to be added.
     * @return returns a response model
     */
    AddMetricResponseModel addMetric(AddMetricRequestModel requestModel);
}
