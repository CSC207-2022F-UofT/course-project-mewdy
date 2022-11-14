package UseCases;

import Entities.DataPoint;
import Entities.Metric;
import Entities.MetricStorageInterface;
import Presenters.DataLoggerResponseModel;

public class DataLogger implements DataLoggerInputBoundary{

    final MetricStorageInterface metricStorage;
    final DataLoggerOutputBoundary presenter;

    public DataLogger(MetricStorageInterface metricStorage, DataLoggerOutputBoundary presenter) {
        this.metricStorage = metricStorage;
        this.presenter = presenter;
    }

    public DataLoggerResponseModel logDataPoint(DataLoggerRequestModel requestModel) throws Exception {
        //this is the DataLogger.DataLogger use case interactor which takes a metricName, value for the DataPoint,
        //a text entry, and the metricStorage object that we are adding to.
        //this throws an exception whenever the metric name is invalid or whenever the
        //input value is beyond the range of the metric.
        String metricName = requestModel.getMetricName();
        double value = requestModel.getValue();
        DataLoggerResponseModel responseModel = new DataLoggerResponseModel("Successfully added datapoint",
                metricName, value);
        MetricStorageInterface metricStorage = requestModel.getStorage();
        try {
            Metric metric = metricStorage.getMetric(metricName);
            double upperBound = metric.getUpperBound();
            double lowerBound = metric.getLowerBound();

            if ((lowerBound <= value) && (upperBound >= value)) {
                DataPoint newEntry = new DataPoint(value);
                metric.addDataPoint(newEntry);

                return presenter.prepareSuccessView(responseModel);
            }
            else {
                return presenter.prepareFailView("Failed to add datapoint, invalid value");
            }
        }
        catch (Exception e){
            return presenter.prepareFailView("Metric does not exist");
        }
    }
}