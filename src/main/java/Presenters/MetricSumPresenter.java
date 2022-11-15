package Presenters;

import Models.MetricSumResponseModel;
import Screens.DataSummaryFailed;
import UseCases.MetricSumOutputBoundary;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MetricSumPresenter implements MetricSumOutputBoundary {

    public MetricSumPresenter(){}

    @Override
    public MetricSumViewModel prepareSuccessView(MetricSumResponseModel responseModel){

        String metricAverageAndSize = "Average: " + responseModel.getMetricAverage() + "; Size: " +
                responseModel.getMetricSize();

        JFreeChart chart = createChart(responseModel);

        return new MetricSumViewModel(chart, metricAverageAndSize);
    }

    @Override
    public MetricSumViewModel prepareDataSumFail(String error){
        throw new DataSummaryFailed(error);
    }

    //Create list of Day objects from list of LocalDateTime objects given in MetricSumResponseModel. This list is
    // for constructing a TimeSeriesCollection dataset.
    private static ArrayList<Day> timePointsToListOfDays(MetricSumResponseModel responseModel){
        ArrayList<LocalDateTime> timePoints = responseModel.getTimePoints();
        ArrayList<Day> listOfDays = new ArrayList<Day>();

        for (LocalDateTime date: timePoints){
            listOfDays.add(new Day(date.getDayOfMonth(), date.getMonthValue(), date.getYear()));
        }

        return listOfDays;
    }
    private static TimeSeriesCollection constructDataset(MetricSumResponseModel responseModel){
        ArrayList<Double> datalist = responseModel.getDatalist();
        ArrayList<Day> listOfDays = timePointsToListOfDays(responseModel);

        TimeSeries data = new TimeSeries("Metric values vs time");
        for (int i = 0; i < listOfDays.size(); i++){
            data.add(listOfDays.get(i), datalist.get(i));
        }

        return new TimeSeriesCollection(data);

    }

    private static JFreeChart createChart(MetricSumResponseModel responseModel){
        TimeSeriesCollection dataset = constructDataset(responseModel);
        JFreeChart chart = ChartFactory.createTimeSeriesChart(responseModel.getMetricName() + " vs Time",
                "Time", responseModel.getMetricName(), dataset);
       return chart;
    }

}
