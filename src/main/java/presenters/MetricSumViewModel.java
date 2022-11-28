package presenters;

import org.knowm.xchart.XYChart;

public class MetricSumViewModel {

    XYChart chart;
    String metricAverageAndSize;


    public MetricSumViewModel(XYChart chart, String metricAverageAndSize) {
        this.chart = chart;
        this.metricAverageAndSize = metricAverageAndSize;
    }

    public XYChart getChart() {
        return chart;
    }

    public String getMetricAverageAndSize() {
        return metricAverageAndSize;
    }
}
