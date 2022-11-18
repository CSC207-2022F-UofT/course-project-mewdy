package Presenters;

import Models.MetricSumResponseModel;
import Screens.DataSummaryFailed;
import UseCases.MetricSumOutputBoundary;

import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.colors.XChartSeriesColors;
import org.knowm.xchart.style.lines.SeriesLines;
import org.knowm.xchart.style.markers.SeriesMarkers;

import java.awt.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    private XYChart createChart(MetricSumResponseModel responseModel) {

        XYChart chart = null;
        try {
            String metricName = responseModel.getMetricName();

            chart = new XYChartBuilder().width(800).height(600).theme(Styler.ChartTheme.Matlab).
                    title(metricName + " vs Time").xAxisTitle("Time").yAxisTitle(metricName).build();

            chart.getStyler().setPlotGridLinesVisible(false);

            ArrayList<Date> xData = formatDates(responseModel);
            ArrayList<Double> yData = responseModel.getDatalist();

            XYSeries series = chart.addSeries(metricName, xData, yData);
            series.setLineColor(XChartSeriesColors.LIGHT_GREY);
            series.setMarkerColor(Color.BLACK);
            series.setMarker(SeriesMarkers.CIRCLE);
            series.setLineStyle(SeriesLines.SOLID);

            return chart;
        } catch (ParseException e) {
            System.err.println("ParseException caught!");
            e.printStackTrace();
        }
        return chart;
    }

    private ArrayList<Date> formatDates(MetricSumResponseModel responseModel) throws ParseException {
        ArrayList<LocalDateTime> dates = responseModel.getTimePoints();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        ArrayList<Date> formattedDates = new ArrayList<>();

        for (LocalDateTime d: dates){
            String date = d.format(formatter);
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            formattedDates.add(sdf.parse(date));
        }
        return formattedDates;
    }
}
