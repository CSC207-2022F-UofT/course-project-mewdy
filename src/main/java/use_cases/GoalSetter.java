package use_cases;

import entities.MetricStorageInterface;
import models.SetGoalRequestModel;
import presenters.SetGoalOutputBoundary;

/**
 * Use case interactor for goal tracking use case.
 */
public class GoalSetter implements SetGoalInputBoundary{

    private final MetricStorageInterface metricStorage;
    private final SetGoalOutputBoundary presenter;

    public GoalSetter(MetricStorageInterface metricStorage, SetGoalOutputBoundary presenter){
        this.metricStorage = metricStorage;
        this.presenter = presenter;
    }

    /**
     * Method for setting goal.
     *
     * @param requestModel represents user data that is required to fulfill use case
     * @return a String whose content will differ depending on whether the goal was successfully set
     */
    @Override
    public String setGoal(SetGoalRequestModel requestModel){

        if (metricStorage.setMetricGoal(requestModel.getGoal(), requestModel.getMetricName())){
            return presenter.prepareSuccessMessage();
        }
        return presenter.prepareFailMessage();
    }
}
