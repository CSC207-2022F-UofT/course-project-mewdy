package presenters;

public class SetGoalPresenter implements SetGoalOutputBoundary{

    public SetGoalPresenter(){}

    @Override
    public String prepareSuccessMessage(){return "Metric Goal was successfully set. See how you are meeting your" +
            " goal with the summary function!.";}

    @Override
    public String prepareFailMessage(){return "Invalid input - please enter a number within the upper and lower" +
            " bounds of the metric";}
}
