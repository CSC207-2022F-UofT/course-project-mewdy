import java.util.ArrayList;

public class Metric {

    private String name;
    private ArrayList<DataPoint> dataPointList;
    private double upperBound;
    private double lowerBound;

    public Metric(String name, ArrayList<DataPoint> dataPointList, double upperBound, double lowerBound) {
        this.name = name;
        this.dataPointList = dataPointList;
        this.upperBound = upperBound;
        this.lowerBound = lowerBound;
    }

    public ArrayList<DataPoint> getDataPoints() {
        return this.dataPointList;
    }

    public void addDataPoint(DataPoint entry) {
        this.dataPointList.add(entry);
    }
    public void popDataPoint() {
        this.dataPointList.remove(-1);
    }

    public String getName() {
        return this.name;
    }

    public double getUpperBound() {
        return this.upperBound;
    }

    public double getLowerBound() {
        return this.lowerBound;
    }
}
