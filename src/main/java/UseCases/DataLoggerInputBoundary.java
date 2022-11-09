package UseCases;

import Entities.MetricStorage;
import Entities.MetricStorageInterface;

public interface DataLoggerInputBoundary {

    void logDataPoint(String metricName, double value, String textEntry) throws Exception;

}