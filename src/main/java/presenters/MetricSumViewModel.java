package presenters;

import org.knowm.xchart.XYChart;

/**
 * CLass that represents the view model for the metric sum view use case
 */

public class MetricSumViewModel {

    XYChart chart;
    String metricAverageAndSize;

    /**
     * Constructor for the metric sum view model
     *
     * @param chart represents the chart to be displayed
     * @param metricAverageAndSize represents the metric average and size
     */
    public MetricSumViewModel(XYChart chart, String metricAverageAndSize) {
        this.chart = chart;
        this.metricAverageAndSize = metricAverageAndSize;
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
    public String getMetricAverageAndSize() {
        return metricAverageAndSize;
    }
}
