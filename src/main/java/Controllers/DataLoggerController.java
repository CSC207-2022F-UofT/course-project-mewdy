package Controllers;


import Entities.MetricStorageInterface;
import Presenters.DataLogFailed;
import Presenters.DataLoggerPresenter;
import Presenters.DataLoggerResponseModel;
import UseCases.DataLogger;
import UseCases.DataLoggerInputBoundary;
import UseCases.DataLoggerRequestModel;

import javax.xml.crypto.Data;

public class DataLoggerController{

    private final MetricStorageInterface storage;

    public DataLoggerController(MetricStorageInterface storage){
        this.storage = storage;
    }
    public DataLoggerResponseModel logDataPoint(double value, String metricName) {
        DataLoggerPresenter presenter = new DataLoggerPresenter();
        DataLoggerInputBoundary dataLogger = new DataLogger(this.storage, presenter);
        try {
            DataLoggerRequestModel requestModel = new DataLoggerRequestModel(metricName, value, this.storage);
            return dataLogger.logDataPoint(requestModel);

        } catch (Exception e) {
            throw new DataLogFailed("Failed to add datapoint");
        }

    }

}