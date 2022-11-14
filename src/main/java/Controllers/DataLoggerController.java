package Controllers;

import Entities.Metric;
import Entities.MetricStorage;
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
    private final DataLoggerPresenter presenter;

    public DataLoggerController(MetricStorageInterface storage, DataLoggerPresenter presenter){
        this.storage = storage;
        this.presenter = presenter;
    }
    public DataLoggerResponseModel logDataPoint(double value, String metricName) {
        DataLoggerInputBoundary dataLogger = new DataLogger(this.storage, this.presenter);
        try {
            DataLoggerRequestModel requestModel = new DataLoggerRequestModel(metricName, value);
            return dataLogger.logDataPoint(requestModel);

        } catch (Exception e) {
            throw new DataLogFailed("Failed to add datapoint");
        }

    }

}