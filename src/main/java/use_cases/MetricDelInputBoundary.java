package use_cases;

import models.MetricDelRequestModel;
import models.MetricDelResponseModel;

public interface MetricDelInputBoundary {
    MetricDelResponseModel metricDelete(MetricDelRequestModel requestModel);
}
