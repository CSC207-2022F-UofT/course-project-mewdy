package models;

/**
 * Class that represents the ResponseModel for AddMetric
 */
public class AddMetricResponseModel {

    private final String metricName;
    private final String message;

    /**
     * Constructor for AddMetricResponseModel
     *
     * @param metricName represents the name of the metric
     * @param message represents the message to be sent to the user
     */
    public AddMetricResponseModel(String metricName, String message) {
        this.metricName = metricName;
        this.message = message;
    }

    /**
     * Overloaded constructor for AddMetricResponseModel
     *
     * @param message represents the message to be sent to the user
     */
    public AddMetricResponseModel(String message) {
        this.metricName = "";
        this.message = message;
    }

    /**
     * Getter for metricName
     *
     * @return metricName
     */
    public String getMetricName() {
        return metricName;
    }

    /**
     * Getter for message
     *
     * @return message
     */
    public String getMessage() {
        return message;
    }
}