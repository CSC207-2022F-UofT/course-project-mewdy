package use_cases;


import models.SetGoalRequestModel;

/**
 * Interface for the Set Goal use case
 */
public interface SetGoalInputBoundary {

    /**
     *
     * @param requestModel stores user input data which contains name of metric for goal setting and value of goal
     * @return a String message that differs depending on success or failure
     */
    String setGoal(SetGoalRequestModel requestModel);

}
