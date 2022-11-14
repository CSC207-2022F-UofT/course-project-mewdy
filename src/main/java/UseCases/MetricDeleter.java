package UseCases;

import Entities.DataPoint;
import Entities.Metric;
import Entities.MetricStorageInterface;
import Models.MetricDelRequestModel;
import Models.MetricDelResponseModel;

import java.util.ArrayList;

public class MetricDeleter implements MetricDelInputBoundary {

    final MetricStorageInterface metricStorage;
    final MetricDelOutputBoundary presenter;

    public MetricDeleter(MetricStorageInterface metricStorage, MetricDelOutputBoundary presenter) {
        this.metricStorage = metricStorage;
        this.presenter = presenter;
    }

    @Override
    public MetricDelResponseModel create(MetricDelRequestModel requestModel) {
        String metricName = requestModel.getMetricName();
        try {
            Metric metricToDel = this.metricStorage.getMetric(metricName);
            int numDataPoints = metricToDel.getDataPoints().size();
            for (int i = 0; i < this.metricStorage.getMetricList().size(); i++) {
                if (this.metricStorage.getMetricList().get(i).getName().equals(metricName)) {
                    this.metricStorage.getMetricList().remove(i);
                }
            }
            MetricDelResponseModel responseModel = new MetricDelResponseModel(metricName, numDataPoints);
            return presenter.prepareSuccessView(responseModel);
        } catch (Exception e) {
            return presenter.prepareMetricDelFail(metricName + " not found in metric storage!");
        }
    }
}
