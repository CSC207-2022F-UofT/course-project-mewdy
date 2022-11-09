package Controllers;

import Entities.Metric;
import Entities.MetricStorage;
import UseCases.DataLogger;
public class DataLoggerController{
    public void logDataPoint(double value, String metricName, String textEntry, MetricStorage storage) throws Exception {
        DataLogger dataLogger = new DataLogger(storage);
        dataLogger.logDataPoint(metricName, value, textEntry);
    }

}
