package models;

/**
 * Class that represents the RequestModel for SetGoal use case
 */
public class SetGoalRequestModel {

    private double goal;
    private String metricName;

    public SetGoalRequestModel(double goal, String metricName){
        this.goal = goal;
        this.metricName = metricName;
    }

    public double getGoal(){return this.goal;}

    public String getMetricName(){return this.metricName;}
}
