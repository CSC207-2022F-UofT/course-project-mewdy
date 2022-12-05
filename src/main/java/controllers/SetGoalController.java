package controllers;

import models.SetGoalRequestModel;
import use_cases.SetGoalInputBoundary;

public class SetGoalController {

    final SetGoalInputBoundary goalSetter;

    public SetGoalController(SetGoalInputBoundary goalSetter){this.goalSetter = goalSetter;}

    public String setGoal(double goal, String metricName){
        SetGoalRequestModel requestModel = new SetGoalRequestModel(goal, metricName);

        return this.goalSetter.setGoal(requestModel);
    }
}
