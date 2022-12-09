package models;

/**
 * Class that represents the ResponseModel for DataLogger
 */
public class DataLoggerResponseModel{

    private final String metricName;
    private final double value;
    private final String message;

    /**
     * Constructor for DataLoggerResponseModel
     *
     * @param message represents the message to be sent to the user
     * @param metricName represents the name of the metric
     * @param value represents the value of the metric
     */
    public DataLoggerResponseModel(String message, String metricName, double value) {
        // depending on whether the DataLog fails or succeeds, these values will be relevant or not
        this.message = message;
        this.metricName = metricName;
        this.value = value;
    }

    /**
     * Overloaded constructor for DataLoggerResponseModel
     *
     * @param message represents the message to be sent to the user
     */
    public DataLoggerResponseModel(String message){
        this.message = message;
        this.metricName = "";
        this.value = 0;
    }

    /**
     * Getter for message
     *
     * @return message
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Getter for metricName
     *
     * @return metricName
     */
    public String getMetricName(){
        return this.metricName;
    }

    /**
     * Getter for value
     *
     * @return value
     */
    public double getValue(){
        return this.value;
    }
}
