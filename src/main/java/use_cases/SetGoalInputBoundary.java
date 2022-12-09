package use_cases;


import models.SetGoalRequestModel;

/**
 * Interface for the Set Goal use case
 */
public interface SetGoalInputBoundary {

    /**
     * setGoal represents the method to be implemented by the set goal use case interactor that will execute the
     * use case function.
     *
     * @param requestModel stores user input data which contains name of metric for goal setting and value of goal
     * @return a String message that differs depending on success or failure
     */
    String setGoal(SetGoalRequestModel requestModel);

}
