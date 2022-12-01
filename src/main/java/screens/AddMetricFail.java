package screens;

/**
 * Subclass of RuntimeException that is thrown when a metric fails to be added.
 */
public class AddMetricFail extends RuntimeException {

    /**
     * Constructor for AddMetricFail
     *
     * @param message represents the error message that is thrown when a metric fails to be added
     */
    public AddMetricFail(String message) {
        super(message);
    }
}

