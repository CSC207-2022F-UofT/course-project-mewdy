package Presenters;

import Models.MetricSumResponseModel;
import Screens.DataSummaryFailed;
import UseCases.MetricSumOutputBoundary;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.colors.XChartSeriesColors;
import org.knowm.xchart.style.lines.SeriesLines;
import org.knowm.xchart.style.markers.SeriesMarkers;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class MetricSumPresenter implements MetricSumOutputBoundary {

    public MetricSumPresenter(){}

    @Override
    public MetricSumViewModel prepareSuccessView(MetricSumResponseModel responseModel){

        String avg = String.valueOf(responseModel.getMetricAverage());
        String size = String.valueOf(responseModel.getMetricSize());

        String metricAverageAndSize = "Average: " + avg + "; Size: " + size;

        XYChart chart = createChart(responseModel);

        return new MetricSumViewModel(chart, metricAverageAndSize);
    }

    @Override
    public MetricSumViewModel prepareDataSumFail(String error){
        throw new DataSummaryFailed(error);
    }

    private XYChart createChart(MetricSumResponseModel responseModel){
        String metricName = responseModel.getMetricName();

        XYChart chart = new XYChartBuilder().width(800).height(600).theme(Styler.ChartTheme.Matlab).
                title(metricName + " vs Time").xAxisTitle("Time").yAxisTitle(metricName).build();

        chart.getStyler().setPlotGridLinesVisible(false);

        ArrayList<LocalDateTime> xData = formatDates(responseModel);
        ArrayList<Double> yData = responseModel.getDatalist();

        XYSeries series = chart.addSeries(metricName, xData, yData);
        series.setLineColor(XChartSeriesColors.LIGHT_GREY);
        series.setMarkerColor(Color.BLACK);
        series.setMarker(SeriesMarkers.CIRCLE);
        series.setLineStyle(SeriesLines.SOLID);

        return chart;
    }

    private ArrayList<LocalDateTime> formatDates(MetricSumResponseModel responseModel){
        ArrayList<LocalDateTime> dates = responseModel.getTimePoints();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        ArrayList<LocalDateTime> formattedDates = new ArrayList<>();

        for (LocalDateTime d: dates){
            String date = d.format(formatter);
            LocalDateTime formattedDate = LocalDateTime.parse(date, formatter);
            formattedDates.add(formattedDate);
        }
        return formattedDates;
    }
}
