import java.util.ArrayList;
import java.util.Objects;

public class MetricStorage {

    private ArrayList<Metric> metricList;

    public void addMetric(Metric metric) {
        //this method adds a Metric to the end of the metricList
        this.metricList.add(metric);
    }

    public void addDataPoint(String metricName, DataPoint dataPoint) {
        //this method inserts a DataPoint into a Metric specified by name
        //this method will not rename the metricName contained in the dataPoint class, that responsibility
        //should lie within wherever the DataPoint is constructed
        for (Metric metric: this.metricList) {
            if (Objects.equals(metric.getName(), metricName)) {
                metric.addDataPoint(dataPoint);
                break;
            }
        }
    }

    public void removeDataPoint(String metricName) {
        //this method removes the most recent DataPoint from a chosen Metric based on its given name
        for (Metric metric: this.metricList) {
            if (Objects.equals(metric.getName(), metricName)) {
                metric.popDataPoint();
                break;
            }
        }
    }


}