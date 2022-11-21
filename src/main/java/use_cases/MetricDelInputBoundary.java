package use_cases;

import models.MetricDelRequestModel;
import models.MetricDelResponseModel;

public interface MetricDelInputBoundary {
    MetricDelResponseModel create(MetricDelRequestModel requestModel);
}
