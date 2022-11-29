package use_cases;

import entities.Metric;
import entities.MetricStorageInterface;
import models.MetricDelRequestModel;
import models.MetricDelResponseModel;
import presenters.MetricDelOutputBoundary;

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
            // Get metric to delete throws NullPointerException if metric does not exist
            Metric metricToDel = this.metricStorage.getMetric(metricName);

            // Remove metric from metricStorage and store the result for the presenter
            int numDataPoints = metricToDel.getDataPoints().size();
            this.metricStorage.removeMetric(metricToDel);
            MetricDelResponseModel responseModel = new MetricDelResponseModel(metricName, numDataPoints);

            return presenter.prepareSuccessView(responseModel);
        } catch (NullPointerException e) {
            //This is executed if the selected metric is not in metric storage.
            return presenter.prepareMetricDelFail("[" + metricName + "] " + "not found in metric storage!");
        }
    }
}
