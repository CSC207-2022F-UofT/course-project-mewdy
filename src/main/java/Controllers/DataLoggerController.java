package Controllers;

import Entities.Metric;
import Entities.MetricStorage;
import Entities.MetricStorageInterface;
import Presenters.DataLoggerPresenter;
import Presenters.DataLoggerResponseModel;
import UseCases.DataLogger;
import UseCases.DataLoggerInputBoundary;
import UseCases.DataLoggerOutputBoundary;

import javax.xml.crypto.Data;

public class DataLoggerController{

    final MetricStorageInterface storage;

    public DataLoggerController(MetricStorageInterface storage){
        this.storage = storage;
    }
    public DataLoggerResponseModel logDataPoint(double value, String metricName, String textEntry) {
        DataLoggerInputBoundary dataLogger = new DataLogger(this.storage);
        try {
            return dataLogger.logDataPoint(metricName, value, textEntry);

        }
        catch (Exception e) {
            DataLoggerResponseModel responseModel = new DataLoggerResponseModel(false, "Invalid Metric or Datapoint value");
            return responseModel;
        }
    }

}