package use_cases;

import entities.Metric;
import entities.MetricStorageInterface;
import models.SetGoalRequestModel;
import presenters.SetGoalOutputBoundary;

public class GoalSetter implements SetGoalInputBoundary{

    private final MetricStorageInterface metricStorage;
    private final SetGoalOutputBoundary presenter;

    public GoalSetter(MetricStorageInterface metricStorage, SetGoalOutputBoundary presenter){
        this.metricStorage = metricStorage;
        this.presenter = presenter;
    }

    @Override
    public String setGoal(SetGoalRequestModel requestModel){

        if (metricStorage.setMetricGoal(requestModel.getGoal(), requestModel.getMetricName())){
            return presenter.prepareSuccessMessage();
        }
        return presenter.prepareFailMessage();
    }
}
