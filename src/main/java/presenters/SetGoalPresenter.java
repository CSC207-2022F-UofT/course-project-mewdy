package presenters;

/**
 * Class that represents the presenter for SetGoal use case
 */
public class SetGoalPresenter implements SetGoalOutputBoundary{

    public SetGoalPresenter(){}

    /**
     * prepareSuccessMessage method prepares a success message for the user
     *
     * @return the success message
     */
    @Override
    public String prepareSuccessMessage(){return "Metric Goal was successfully set. See how you are meeting your" +
            " goal with the summary function!";}

    /**
     * prepareFailureMessage method prepares a failure message for the user
     *
     * @return the failure message
     */
    @Override
    public String prepareFailMessage(){return "Invalid input - please enter a number within the upper and lower" +
            " bounds of the metric";}
}
