<<<<<<< HEAD
package UseCases;

import Entities.DataPoint;
import Entities.MetricStorage;
import Entities.Metric;
=======
package use_cases;

import entities.DataPoint;
import entities.MetricStorage;
import entities.Metric;
>>>>>>> 341cb8914b93424896a86420445614c47cb806ec

import java.util.ArrayList;

public class MetricAdder {

    public Metric createMetric(String name, ArrayList<DataPoint> dataPointList, double upperBound, double lowerBound) {
        //
        return new Metric(name, dataPointList, upperBound, lowerBound);
    }
    public MetricStorage addMetric(Metric metric, MetricStorage storage) throws Exception {
        //
        String name = metric.getName().toLowerCase();
        ArrayList<Metric> metricList = storage.getMetricList();
        for (Metric present: metricList){
            if (present.getName().toLowerCase().equals(name)) {
                throw new Exception("That metric already exists.");
            }
        }
        storage.addMetric(metric);
        return storage;
    }
}

