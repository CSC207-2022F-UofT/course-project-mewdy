package Models;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MetricSumResponseModel {

    ArrayList<LocalDateTime> timePoints;
    ArrayList<Double> datalist;
    double metricAverage;
    int metricSize;
    String metricName;

    public MetricSumResponseModel(ArrayList<LocalDateTime> timePoints, ArrayList<Double> datalist, double metricAverage,
                                  int metricSize, String metricName){
        this.timePoints = timePoints;
        this.datalist = datalist;
        this.metricAverage = metricAverage;
        this.metricSize = metricSize;
        this.metricName = metricName;
    }

    public ArrayList<LocalDateTime> getTimePoints(){return this.timePoints;}

    public ArrayList<Double> getDatalist(){return this.datalist;}

    public double getMetricAverage(){return this.metricAverage;}

    public int getMetricSize(){return this.metricSize;}

    public String getMetricName(){return this.metricName;}
}
