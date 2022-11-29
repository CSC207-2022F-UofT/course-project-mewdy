package presenters;

/**
 * Subclass of RuntimeException that is thrown when a DataLogger fails to log data.
 */
public class DataLogFailed extends RuntimeException{

    /**
     * Constructor for DataLogFailed
     *
     * @param error
     */
    public DataLogFailed(String error){
        super(error);
    }
}
