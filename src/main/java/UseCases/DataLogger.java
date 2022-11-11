package UseCases;

import Entities.DataPoint;
import Entities.Metric;
import Entities.MetricStorageInterface;
import Presenters.DataLoggerResponseModel;

import java.util.ArrayList;

public class DataLogger implements DataLoggerInputBoundary{

    final MetricStorageInterface metricStorage;

    public DataLogger(MetricStorageInterface metricStorage) {
        this.metricStorage = metricStorage;
    }

    public DataLoggerResponseModel logDataPoint(String metricName, double value, String textEntry) throws Exception {
        //this is the DataLogger.DataLogger use case interactor which takes a metricName, value for the DataPoint,
        //a text entry, and the metricStorage object that we are adding to.
        //this throws an exception whenever the metric name is invalid or whenever the
        //input value is beyond the range of the metric.
        Metric metric = metricStorage.getMetric(metricName);
        double upperBound = metric.getUpperBound();
        double lowerBound = metric.getLowerBound();

        if ((lowerBound <= value) && (upperBound >= value)) {
            DataPoint newEntry = new DataPoint(value, metricName, textEntry);
            metric.addDataPoint(newEntry);
            DataLoggerResponseModel responseModel = new DataLoggerResponseModel(true, "Successfully added datapoint");
            return responseModel;
        }
        else {
            throw new Exception("Invalid value entered: " + value + " is not a valid value for " + metricName);
        }
    }
}