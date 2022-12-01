package use_cases;

import entities.DataPoint;
import entities.Metric;
import entities.MetricStorageInterface;
import models.DataLoggerRequestModel;
import presenters.DataLoggerOutputBoundary;
import models.DataLoggerResponseModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataLogger implements DataLoggerInputBoundary{

    final MetricStorageInterface metricStorage;
    final DataLoggerOutputBoundary presenter;

    public DataLogger(MetricStorageInterface metricStorage, DataLoggerOutputBoundary presenter) {
        this.metricStorage = metricStorage;
        this.presenter = presenter;
    }

    public DataLoggerResponseModel logDataPoint(DataLoggerRequestModel requestModel) {
        //this is the DataLogger.DataLogger use case interactor which takes a metricName, value for the DataPoint,
        //and the metricStorage object that we are adding to.
        //this throws an exception whenever the metric name is invalid or whenever the
        //input value is beyond the range of the metric.
        String metricName = requestModel.getMetricName();
        double value = requestModel.getValue();
        DataLoggerResponseModel responseModel = new DataLoggerResponseModel("Successfully added datapoint",
                metricName, value);
        try {
            // Get metric info
            Metric metric = this.metricStorage.getMetric(metricName);
            double upperBound = metric.getUpperBound();
            double lowerBound = metric.getLowerBound();
            int size = metric.getDataPoints().size();
            // Initialize dates to be not equal in case there are no data points
            String lastDate = "neq";
            String todayDate = "neq2";

            if (size >= 1) { // Check if list is not empty, compare last entry date with today's date if not empty
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                lastDate = metric.getDataPoints().get(size - 1).getDate().substring(0, 10);
                todayDate = LocalDate.now().format(myFormatObj);

            }
            if ((lowerBound <= value) && (upperBound >= value) && (!todayDate.equals(lastDate))) {
                // Check if value is within bounds and if today's date is not equal to last entry date
                // Add data point
                DataPoint newEntry = new DataPoint(value);
                this.metricStorage.addDataPoint(metricName, newEntry);

                return presenter.prepareSuccessView(responseModel);
            } else {
                return presenter.prepareFailView("Failed to add datapoint, invalid value");
            }

        } catch (NullPointerException e) {

            return presenter.prepareFailView("Metric does not exist");
        }
    }
}