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
        String metricName = requestModel.getMetricName();
        Metric metric = metricStorage.getMetric(metricName);
        double upperBound = metric.getUpperBound();
        double lowerBound = metric.getLowerBound();
        double goal = requestModel.getGoal();

        if (goal >= lowerBound && goal <= upperBound){
            metric.setGoal(requestModel.getGoal());

            return presenter.prepareSuccessMessage();
        }
        else {return presenter.prepareFailMessage();}
    }
}
