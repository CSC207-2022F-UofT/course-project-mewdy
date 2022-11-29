package presenters;

/**
 * Subclass of RuntimeException
 */
public class AddMetricFail extends RuntimeException {
    /**
     * Constructor for AddMetricFail
     *
     * @param message represents the message to be displayed
     */
    public AddMetricFail(String message) {
        super(message);
    }
}

