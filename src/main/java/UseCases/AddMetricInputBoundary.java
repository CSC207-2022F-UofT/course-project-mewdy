package UseCases;

import Models.AddMetricRequestModel;
import Models.AddMetricResponseModel;


public interface AddMetricInputBoundary {
    AddMetricResponseModel addMetric(AddMetricRequestModel requestModel) throws Exception;
}
