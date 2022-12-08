package presenters;

/**
 * Interface for SetGoalPresenter
 */
public interface SetGoalOutputBoundary {

    /**
    * prepareSuccessMessage method that returns a success message
     */
    String prepareSuccessMessage();

    /**
     * prepareFailMessage method that returns a fail message
     */
    String prepareFailMessage();
}
