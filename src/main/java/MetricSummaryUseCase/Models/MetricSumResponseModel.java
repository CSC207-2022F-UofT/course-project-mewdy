package MetricSummaryUseCase.Models;

import java.util.ArrayList;
import java.util.Date;

public class MetricSumResponseModel {

    ArrayList<Date> timePoints;
    ArrayList<Double> datalist;
    double metricAverage;
    int metricSize;

    public MetricSumResponseModel(ArrayList<Date> timePoints, ArrayList<Double> datalist, double metricAverage,
                                  int metricSize){
        this.timePoints = timePoints;
        this.datalist = datalist;
        this.metricAverage = metricAverage;
        this.metricSize = metricSize;
    }

    public ArrayList<Date> getTimePoints(){return this.timePoints;}

    public ArrayList<Double> getDatalist(){return this.datalist;}

    public double getMetricAverage(){return this.metricAverage;}

    public double getMetricSize(){return this.metricSize;}
}
