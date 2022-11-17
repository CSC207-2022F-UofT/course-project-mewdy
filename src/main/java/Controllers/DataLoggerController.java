package Controllers;


import Entities.MetricStorageInterface;
import Presenters.DataLogFailed;
import Presenters.DataLoggerPresenter;
import Presenters.DataLoggerResponseModel;
import UseCases.DataLogger;
import UseCases.DataLoggerInputBoundary;
import UseCases.DataLoggerOutputBoundary;
import UseCases.DataLoggerRequestModel;

import javax.xml.crypto.Data;

public class DataLoggerController{

    private final MetricStorageInterface storage;
    private final DataLoggerInputBoundary dataLogger;
    private final DataLoggerOutputBoundary presenter;

    public DataLoggerController(MetricStorageInterface storage, DataLoggerInputBoundary dataLogger,
                                DataLoggerOutputBoundary presenter){
        this.storage = storage;
        this.dataLogger = dataLogger;
        this.presenter = presenter;
    }
    public DataLoggerResponseModel logDataPoint(double value, String metricName) {
        try {
            DataLoggerRequestModel requestModel = new DataLoggerRequestModel(metricName, value, this.storage);
            return this.dataLogger.logDataPoint(requestModel);

        } catch (Exception e) {
            throw new DataLogFailed("Failed to add datapoint");
        }

    }

}