package Controllers;

import Entities.DataPoint;
import Entities.MetricStorageInterface;
import Models.AddMetricRequestModel;
import Models.AddMetricResponseModel;
import Presenters.AddMetricPresenter;
import UseCases.AddMetricInputBoundary;
import UseCases.MetricAdder;

import java.util.ArrayList;

public class AddMetricController {

    private final MetricStorageInterface metricStorage;

    public AddMetricController(MetricStorageInterface metricStorage) {
        this.metricStorage = metricStorage;
    }

    public AddMetricResponseModel addMetric(String metricName, double upperBound, double lowerBound, boolean discrete, ArrayList<DataPoint> dataPointList) {
        AddMetricPresenter presenter = new AddMetricPresenter();
        AddMetricInputBoundary inputBoundary = new MetricAdder(presenter, metricStorage);
        try {
            AddMetricRequestModel requestModel = new AddMetricRequestModel(metricName, dataPointList, upperBound, lowerBound, discrete);
            return inputBoundary.addMetric(requestModel);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
