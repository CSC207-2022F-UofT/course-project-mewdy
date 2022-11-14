package Presenters;

import java.util.ArrayList;
import java.util.Date;

public class MetricSumViewModel {

    ArrayList<Date> timePoints;
    ArrayList<Double> dataList;
    String metricAverageAndSize;


    public MetricSumViewModel(ArrayList<Date> timePoints, ArrayList<Double> dataList, String metricAverageAndSize){
        this.timePoints = timePoints;
        this.dataList = dataList;
        this.metricAverageAndSize = metricAverageAndSize;
    }

    public ArrayList<Date> getTimePoints() {
        return timePoints;
    }

    public ArrayList<Double> getDataList() {
        return dataList;
    }

    public String getMetricAverageAndSize(){ return  metricAverageAndSize;}
}
