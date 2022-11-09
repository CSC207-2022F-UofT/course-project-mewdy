package UseCases;

import Entities.DataPoint;
import Entities.Metric;
import Entities.MetricStorage;

public class DataImportInputBoundary {

    public static interface DataLoggerInputBoundary {
        void logDataPoint(String metricName, double value, String textEntry, MetricStorage storage) throws Exception;
    }
}
