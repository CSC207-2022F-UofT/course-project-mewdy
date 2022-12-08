package controllers;

import models.SetGoalRequestModel;
import use_cases.SetGoalInputBoundary;

/**
 * Class that represents the controller for the set goal use case
 */
public class SetGoalController {

    final SetGoalInputBoundary goalSetter;

    /**
     * Constructor for the set goal controller
     *
     * @param goalSetter represents the goal setter
     */
    public SetGoalController(SetGoalInputBoundary goalSetter){this.goalSetter = goalSetter;}

    /**
     * setGoal method sets the goal for the user
     *
     * @param goal represents the goal to be set
     * @param metricName represents the metric name
     * @return true if the goal was set, false otherwise
     */
    public String setGoal(double goal, String metricName){
        SetGoalRequestModel requestModel = new SetGoalRequestModel(goal, metricName);

        return this.goalSetter.setGoal(requestModel);
    }
}
