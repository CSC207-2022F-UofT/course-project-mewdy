package Controllers;

import Entities.DataPoint;
import Entities.Metric;
import Entities.MetricStorage;

import java.util.ArrayList;

public class AddMetricController {

    public void buttonPress() {
    }

    public void passData(String name, ArrayList<DataPoint> dataPointList, double upperBound, double lowerBound, MetricStorage storage) {
        Metric newMetric = new Metric(name, dataPointList, upperBound, lowerBound);
        storage.addMetric(newMetric);
    }
}
