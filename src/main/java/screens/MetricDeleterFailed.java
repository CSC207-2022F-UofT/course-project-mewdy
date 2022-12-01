package screens;

/**
 * Class that is an extension of RuntimeException that is thrown when a metric can't be deleted
 */
public class MetricDeleterFailed extends RuntimeException {

    /**
     * Constructor for the MetricDeleterFailed class
     *
     * @param error represents the error message that is thrown when a metric can't be deleted
     */
    public MetricDeleterFailed(String error) {
        super(error);
    }
}
