package use_cases;

import entities.DataPoint;
import entities.Metric;
import entities.MetricStorageInterface;
import models.DataLoggerRequestModel;
import presenters.DataLoggerOutputBoundary;
import models.DataLoggerResponseModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class that represents the DataLogger use case that is responsible for logging data points
 */

public class DataLogger implements DataLoggerInputBoundary{

    final MetricStorageInterface metricStorage;
    final DataLoggerOutputBoundary presenter;

    /**
     * Constructor for the DataLogger use case
     *
     * @param metricStorage represents the metric storage that the use case will interact with
     * @param presenter represents the presenter that will present the output
     */
    public DataLogger(MetricStorageInterface metricStorage, DataLoggerOutputBoundary presenter) {
        this.metricStorage = metricStorage;
        this.presenter = presenter;
    }

    /**
     * logDataPoint adds a datapoint to a given metric in the metric storage
     * An error message is returned if the metric name or input values are invalid
     *
     * @param requestModel contains the information of the datapoint to be logged
     * @return the response model that contains the information of the datapoint that was logged
     */
    public DataLoggerResponseModel logDataPoint(DataLoggerRequestModel requestModel) {
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
            } else if ((lowerBound <= value) && (upperBound >= value)) {
                return presenter.prepareFailView("Failed to add datapoint, there is already logged data for" +
                        " today!");
            } else {
                return presenter.prepareFailView("Failed to add datapoint, invalid value - Please enter a " +
                        "number within the bounds of the metric");
            }

        } catch (NullPointerException e) {

            return presenter.prepareFailView("Metric does not exist");
        }
    }
}