package Presenters;

import java.util.ArrayList;
import java.util.Date;

public class MetricSumViewModel {

    ArrayList<Date> timePoints;
    ArrayList<Double> dataList;
    String metricAverage;
    String metricTrend;


    public MetricSumViewModel(ArrayList<Date> timePoints, ArrayList<Double> dataList, String metricAverage,
                              String metricTrend){
        this.timePoints = timePoints;
        this.dataList = dataList;
        this.metricAverage = metricAverage;
        this.metricTrend = metricTrend;
    }

    public ArrayList<Date> getTimePoints() {
        return timePoints;
    }

    public ArrayList<Double> getDataList() {
        return dataList;
    }

    public String getMetricAverage() {
        return metricAverage;
    }

    public String getMetricTrend() {
        return metricTrend;
    }

}
