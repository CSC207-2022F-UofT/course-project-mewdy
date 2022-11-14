package Models;

public class AddMetricRequestModel {

    private final String metricName;
    private final double upperBound;
    private final double lowerBound;
    private final boolean discrete;

    public AddMetricRequestModel(String metricName, double upperBound, double lowerBound, boolean discrete) {
        this.metricName = metricName;
        this.upperBound = upperBound;
        this.lowerBound = lowerBound;
        this.discrete = discrete;
    }

    public String getMetricName() {
        return metricName;
    }

    public double getUpperBound() {
        return upperBound;
    }

    public double getLowerBound() {
        return lowerBound;
    }

    public boolean getDiscrete() {
        return discrete;
    }

}
