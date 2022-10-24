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
}
