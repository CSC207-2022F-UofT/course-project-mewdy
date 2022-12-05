package models;

/**
 * Class that represents the RequestModel for SetGoal use case
 */
public class SetGoalRequestModel {

    private final double goal;
    private final String metricName;

    /**
     * Constructor method for SetGoalRequestModel
     * @param goal represents goal defined by user input
     * @param metricName represents metric that user wants to set goal for
     */
    public SetGoalRequestModel(double goal, String metricName){
        this.goal = goal;
        this.metricName = metricName;
    }

    /**
     * Getter for goal
     * @return  goal stored by the request model
     */
    public double getGoal(){return this.goal;}

    /**
     * Getter for metric name
     * @return  metric name string
     */
    public String getMetricName(){return this.metricName;}
}
