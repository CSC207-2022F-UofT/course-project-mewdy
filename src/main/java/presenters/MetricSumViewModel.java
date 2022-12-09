package presenters;

import org.knowm.xchart.XYChart;

/**
 * CLass that represents the view model for the metric sum view use case
 */

public class MetricSumViewModel {

    XYChart chart;
    String metricStatistics;
    String goalStat;
    boolean containsGoalStat;

    /**
     * Constructor for the metric sum view model
     *
     * @param chart represents the chart to be displayed
     * @param statistics represents the metric average and size
     */
    public MetricSumViewModel(XYChart chart, String statistics) {
        this.chart = chart;
        this.metricStatistics = statistics;
    }

    /**
     * Getter for the chart
     *
     * @return the chart
     */
    public XYChart getChart() {
        return chart;
    }

    /**
     * Getter for the metric average and size
     *
     * @return the metric average and size
     */
    public String getMetricStatistics() {
        return metricStatistics;
    }

    /**
     * Setter for metricStatistics. Allows GoalTrackingDecorator to modify view model output.
     *
     * @param metricStatistics represents relevant statistics that will be used to construct metric summary
     */
    public void setMetricStatistics(String metricStatistics) { this.metricStatistics = metricStatistics; }

    /**
     * Setter for goalStat. Allows GoalTrackingDecorator to set a goal tracking stat in the view model for additional
     * information to be given by metric summary when the chosen metric contains a goal.
     *
     * @param stat a string that expresses the user's performance with respect to the metric's goal
     */
    public void setGoalStat(String stat) {
        goalStat = stat;
        containsGoalStat = true;
    }

    /**
     * @return the goal stat stored in this view model which will be used by GoalTrackingDecorator
     */
    public String getGoalStat() { return goalStat; }

    /**
     * @return true iff this view model contains a goal stat.
     */
    public boolean getContainsGoalStat() { return containsGoalStat; }
}
