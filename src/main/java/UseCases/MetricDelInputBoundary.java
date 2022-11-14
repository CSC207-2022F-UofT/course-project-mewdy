package UseCases;

import Models.MetricDelRequestModel;
import Models.MetricDelResponseModel;

public interface MetricDelInputBoundary {
    MetricDelResponseModel create(MetricDelRequestModel requestModel);
}
