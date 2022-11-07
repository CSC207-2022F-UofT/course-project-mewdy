package UseCases;

import Entities.DataPoint;
import Entities.Metric;
import Entities.MetricStorage;

public class DataImportInputBoundary {

    public static class DataLogger implements DataLoggerInputBoundary {
        public void logDataPoint(String metricName, double value, String textEntry, MetricStorage storage) throws Exception {
            //this is the UseCases.DataImportInputBoundary.DataLogger use case interactor which takes a metricName, value for the DataPoint,
            //a text entry, and the metricStorage object that we are adding to.
            //this throws an exception whenever the metric name is invalid or whenever the
            //input value is beyond the range of the metric.

            Metric metric = storage.getMetric(metricName);
            double upperBound = metric.getUpperBound();
            double lowerBound = metric.getLowerBound();

            if ((lowerBound <= value) && (upperBound >= value)) {
                DataPoint newEntry = new DataPoint(value, metricName, textEntry);
                metric.addDataPoint(newEntry);
            }
            else {
                throw new Exception("Invalid value entered: " + value + " is not a valid value for " + metricName);
            }
        }
    }

    public static interface DataLoggerInputBoundary {
        void logDataPoint(String metricName, double value, String textEntry, MetricStorage storage) throws Exception;
    }
}
