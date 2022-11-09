package Models;

import java.util.ArrayList;
import java.util.Date;

public class MetricSumResponseModel {

    ArrayList<Date> timePoints;
    ArrayList<Double> datalist;
    double metricAverage;
    double metricTrend;

    public MetricSumResponseModel(ArrayList<Date> timePoints, ArrayList<Double> datalist, double metricAverage,
                                  double metricTrend){
        this.timePoints = timePoints;
        this.datalist = datalist;
        this.metricAverage = metricAverage;
        this.metricTrend = metricTrend;
    }

    public ArrayList<Date> getTimePoints(){return this.timePoints;}

    public ArrayList<Double> getDatalist(){return this.datalist;}

    public double getMetricAverage(){return this.metricAverage;}

    public double getMetricTrend(){return this.metricTrend;}
}
