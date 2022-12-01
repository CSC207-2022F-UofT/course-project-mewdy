package use_cases;

import entities.DataPoint;
import entities.MetricStorageInterface;
import models.EntryUndoRequestModel;
import models.EntryUndoResponseModel;
import presenters.EntryUndoOutputBoundary;

import java.util.ArrayList;


public class EntryUndo implements EntryUndoInputBoundary{
    private final MetricStorageInterface metricStorage;
    private final EntryUndoOutputBoundary presenter;

    public EntryUndo(MetricStorageInterface metricStorage, EntryUndoOutputBoundary presenter){
        this.metricStorage = metricStorage;
        this.presenter = presenter;
    }

    public EntryUndoResponseModel deleteDatapoint(EntryUndoRequestModel requestModel){
        String metricName = requestModel.getMetricName();
        ArrayList<DataPoint> deletedMetric = this.metricStorage.getDataPointList(metricName);
        try {
            DataPoint deletedData = deletedMetric.get(deletedMetric.size() - 1);
            // Throws an IndexOutOfBoundsException if the metric is empty

            this.metricStorage.removeDataPoint(metricName); // Remove data point from metric

            // Prepare response model with values from deleted data point
            double value = deletedData.getValue();
            String date = deletedData.getDate();
            EntryUndoResponseModel responseModel = new EntryUndoResponseModel(value, date, metricName);

            return presenter.prepareSuccessView(responseModel);

        } catch (IndexOutOfBoundsException e) {
            return presenter.prepareFailView("Metric can't undo action becuase - " + metricName
                    + " contains fewer than 1 data point");
        }
    }
}
