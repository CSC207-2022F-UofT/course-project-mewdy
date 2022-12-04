package presenters;

import org.knowm.xchart.XYChart;

/**
 * CLass that represents the view model for the metric sum view use case
 */

public class MetricSumViewModel {

    XYChart chart;
    String metricStatistics;
    String goalStat;

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
    public void setMetricStatistics(String metricStatistics) { this.metricStatistics = metricStatistics; }

    public void setGoalStat(String stat) { goalStat = stat; }

    public String getGoalStat() { return goalStat; }
}
