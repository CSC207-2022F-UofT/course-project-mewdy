package Controllers;

import Models.MetricDelResponseModel;
import Models.MetricDelRequestModel;
import UseCases.MetricDelInputBoundary;

public class MetricDelController {
    final MetricDelInputBoundary metricDeleter;

    public MetricDelController(MetricDelInputBoundary metricDeleter) {
        this.metricDeleter = metricDeleter;
    }

    MetricDelResponseModel create(String metricName, int numDataPoints) {
        MetricDelRequestModel requestModel = new MetricDelRequestModel(metricName);

        return this.metricDeleter.create(requestModel);
    }
}
