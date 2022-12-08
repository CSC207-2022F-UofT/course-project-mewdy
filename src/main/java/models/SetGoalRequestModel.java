package models;

public class SetGoalRequestModel {

    private final double goal;
    private final String metricName;

    public SetGoalRequestModel(double goal, String metricName){
        this.goal = goal;
        this.metricName = metricName;
    }

    public double getGoal(){return this.goal;}

    public String getMetricName(){return this.metricName;}
}
