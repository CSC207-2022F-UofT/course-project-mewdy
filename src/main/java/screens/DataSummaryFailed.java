package screens;

/**
 * Subclass of DataSummary that represents the failed data summary screen.
 */
public class DataSummaryFailed extends RuntimeException {

    /**
     * Constructor for the DataSummaryFailed class.
     *
     * @param error represents the error message.
     */
    public DataSummaryFailed(String error) {
        super(error);
    }
}
