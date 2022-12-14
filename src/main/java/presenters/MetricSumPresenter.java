package presenters;

import models.MetricSumResponseModel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.colors.XChartSeriesColors;
import org.knowm.xchart.style.lines.SeriesLines;
import org.knowm.xchart.style.markers.SeriesMarkers;
import screens.DataSummaryFailed;

import java.awt.*;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Presenter for the MetricSumView use case
 */
public class MetricSumPresenter implements MetricSumOutputBoundary {

    /**
     * Constructor for the MetricSumPresenter
     */
    public MetricSumPresenter() {
    }

    /**
     * prepareSuccessView prepares the success view for the MetricSum use case
     *
     * @param responseModel represents the response model for the MetricSum use case
     * @return the success view for the MetricSum use case
     */
    @Override
    public MetricSumViewModel prepareSuccessView(MetricSumResponseModel responseModel) {

        //Create decimal formatter to round numbers to 2 decimal points.
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);

        //Get string expressions for average and size
        String avg = df.format(responseModel.getMetricAverage());
        String size = String.valueOf(responseModel.getMetricSize());

        //Create string to be stored in view model
        String metricAverageAndSize = "Average: " + avg + "; Size: " + size;

        //Create chart using data stored in response model
        XYChart chart = createChart(responseModel);

        return new MetricSumViewModel(chart, metricAverageAndSize);
    }

    /**
     * prepareDataSumFail prepares the fail view for the MetricSum use case
     *
     * @param error represents the error message
     * @return the fail view for the MetricSum use case
     */
    @Override
    public MetricSumViewModel prepareDataSumFail(String error) {
        throw new DataSummaryFailed(error);
    }

    /**
     * createChart creates a chart using the data stored in the response model
     *
     * @param responseModel represents the response model for the MetricSum use case
     * @return a chart based off of the data from the response model
     */
    private XYChart createChart(MetricSumResponseModel responseModel) {

        XYChart chart = null;

        try {
            //Get metric name
            String metricName = responseModel.getMetricName();

            //Initialize chart
            chart = new XYChartBuilder().width(800).height(600).theme(Styler.ChartTheme.Matlab).
                    title(metricName + " vs Time").xAxisTitle("Time").yAxisTitle(metricName).build();

            chart.getStyler().setPlotGridLinesVisible(false);

            //Get x and y data
            ArrayList<Date> xData = formatDates(responseModel);
            ArrayList<Double> yData = responseModel.getDatalist();

            //Format chart and add data
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

    /**
     * formatDates formats the dates stored in the response model
     *
     * @param responseModel represents the response model for the MetricSum use case
     * @return an ArrayList of formatted dates
     * @throws ParseException if the date cannot be parsed
     */
    private ArrayList<Date> formatDates(MetricSumResponseModel responseModel) throws ParseException {
        ArrayList<String> dates = responseModel.getTimePoints();

        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<Date> formattedDates = new ArrayList<>();

        for (String d : dates) {
            formattedDates.add(sdf.parse(d));
        }
        return formattedDates;
    }
}
