package Controllers;

import Entities.Metric;
import Entities.MetricStorage;
import UseCases.DataLogger;
import UseCases.DataLoggerInputBoundary;

import javax.xml.crypto.Data;

public class DataLoggerController{

    final DataLoggerInputBoundary dataLogger;

    public DataLoggerController(DataLoggerInputBoundary dataLogger){
        this.dataLogger = dataLogger;
    }
    public void logDataPoint(double value, String metricName, String textEntry, MetricStorage storage) throws Exception {
        DataLogger dataLogger = new DataLogger(storage);
        dataLogger.logDataPoint(metricName, value, textEntry);
    }

}