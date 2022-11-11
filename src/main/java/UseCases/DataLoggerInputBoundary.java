package UseCases;

import Entities.MetricStorage;
import Entities.MetricStorageInterface;
import Presenters.DataLoggerResponseModel;

public interface DataLoggerInputBoundary {

    DataLoggerResponseModel logDataPoint(String metricName, double value, String textEntry) throws Exception;

}