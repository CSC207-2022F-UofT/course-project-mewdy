package use_cases;

import models.AddMetricRequestModel;
import models.AddMetricResponseModel;


public interface AddMetricInputBoundary {
    AddMetricResponseModel addMetric(AddMetricRequestModel requestModel);
}
