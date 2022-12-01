package controllers;


import models.DataLoggerResponseModel;
import models.DataLoggerRequestModel;
import use_cases.DataLoggerInputBoundary;

/**
 * Controller class for DataLogger use case
 */
public class DataLoggerController{

    private final DataLoggerInputBoundary dataLogger;

    /**
     * Constructor for DataLoggerController
     *
     * @param dataLogger represents the input boundary for DataLogger
     */
    public DataLoggerController(DataLoggerInputBoundary dataLogger){
        this.dataLogger = dataLogger;
    }

    /**
     * logDataPoint calls the use case interactor, DataLogger to log a data point
     *
     * @param value represents the value to be logged
     * @param metricName represents the name of the metric to be logged
     * @return DataLoggerResponseModel
     */
    public DataLoggerResponseModel logDataPoint(double value, String metricName) {
        DataLoggerRequestModel requestModel = new DataLoggerRequestModel(metricName, value);
        return this.dataLogger.logDataPoint(requestModel);
    }

}