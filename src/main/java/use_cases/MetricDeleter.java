package use_cases;

import entities.Metric;
import entities.MetricStorageInterface;
import models.MetricDelRequestModel;
import models.MetricDelResponseModel;
import presenters.MetricDelOutputBoundary;

/**
 * Class that represents the MetricDeleter use case that is responsible for deleting a metric from the metric storage
 */
public class MetricDeleter implements MetricDelInputBoundary {

    final MetricStorageInterface metricStorage;
    final MetricDelOutputBoundary presenter;

    /**
     * Constructor for the MetricDeleter use case
     *
     * @param metricStorage represents the metric storage that the use case will interact with
     * @param presenter represents the presenter that will present the output
     */
    public MetricDeleter(MetricStorageInterface metricStorage, MetricDelOutputBoundary presenter) {
        this.metricStorage = metricStorage;
        this.presenter = presenter;
    }

    /**
     * deleteMetric deletes a given metric from the metric storage
     *
     * @param requestModel represents the request model for the MetricDeleter use case
     * @return the response model for the MetricDeleter use case
     * @throws NullPointerException if the selected metric is not in the metric storage
     */
    @Override
    public MetricDelResponseModel metricDelete(MetricDelRequestModel requestModel) {
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
