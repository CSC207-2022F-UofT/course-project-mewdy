package UseCases;

import Entities.Metric;
import Entities.MetricStorageInterface;
import Models.AddMetricRequestModel;
import Models.AddMetricResponseModel;
import Presenters.AddMetricPresenter;

public interface AddMetricInputBoundary {
    AddMetricPresenter addMetric(AddMetricRequestModel requestModel, MetricStorageInterface storage);
}
