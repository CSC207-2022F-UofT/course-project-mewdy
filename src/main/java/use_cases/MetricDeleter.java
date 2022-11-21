package use_cases;

import entities.Metric;
import entities.MetricStorageInterface;
import models.MetricDelRequestModel;
import models.MetricDelResponseModel;

public class MetricDeleter implements MetricDelInputBoundary {

    final MetricStorageInterface metricStorage;
    final MetricDelOutputBoundary presenter;

    //MetricDeleter constructor
    public MetricDeleter(MetricStorageInterface metricStorage, MetricDelOutputBoundary presenter) {
        this.metricStorage = metricStorage;
        this.presenter = presenter;
    }

    //Following block of code is what is responsible for deleting a metric
    @Override
    public MetricDelResponseModel create(MetricDelRequestModel requestModel) {
        String metricName = requestModel.getMetricName();

        //Trys to delete the selected metric
        try {
            //This is executed if the selected metric is in the metric storage
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
            //This is executed if the selected metric is not in metric storage.
            return presenter.prepareMetricDelFail("[" + metricName + "] " + "not found in metric storage!");
        }
    }
}
