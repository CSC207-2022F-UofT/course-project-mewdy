package use_cases;

import entities.DataPoint;
import entities.Metric;
import entities.MetricStorageInterface;
import models.DataLoggerRequestModel;
import presenters.DataLoggerPresenter;
import models.DataLoggerResponseModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataLogger implements DataLoggerInputBoundary{

    final MetricStorageInterface metricStorage;

    public DataLogger(MetricStorageInterface metricStorage) {
        this.metricStorage = metricStorage;
    }

    public DataLoggerResponseModel logDataPoint(DataLoggerRequestModel requestModel) throws Exception {
        //this is the DataLogger.DataLogger use case interactor which takes a metricName, value for the DataPoint,
        //and the metricStorage object that we are adding to.
        //this throws an exception whenever the metric name is invalid or whenever the
        //input value is beyond the range of the metric.
        String metricName = requestModel.getMetricName();
        double value = requestModel.getValue();
        DataLoggerResponseModel responseModel = new DataLoggerResponseModel("Successfully added datapoint",
                metricName, value);
        DataLoggerOutputBoundary presenter = (DataLoggerOutputBoundary) new DataLoggerPresenter();
        try {
            Metric metric = this.metricStorage.getMetric(metricName);
            double upperBound = metric.getUpperBound();
            double lowerBound = metric.getLowerBound();
            int size = metric.getDataPoints().size();
            String lastDate = null;
            String todayDate = null;
            if (size >= 1) {
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("YYYY-MM-dd");
                lastDate = metric.getDataPoints().get(size - 1).getDate().substring(0, 10);
                todayDate = LocalDate.now().format(myFormatObj);
            }
            else {
                todayDate = "neq";
                lastDate = "neq2";
            }
            if ((lowerBound <= value) && (upperBound >= value) && (!todayDate.equals(lastDate))) {
                DataPoint newEntry = new DataPoint(value);
                metric.addDataPoint(newEntry);

                return presenter.prepareSuccessView(responseModel);
            } else {
                return presenter.prepareFailView("Failed to add datapoint, invalid value");
            }
        }
        catch (Exception e){
            return presenter.prepareFailView("Metric does not exist");
        }
    }
}