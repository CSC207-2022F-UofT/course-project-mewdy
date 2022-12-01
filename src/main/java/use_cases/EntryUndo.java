package use_cases;

import entities.MetricStorage;
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
        try{
            ArrayList<DataPoint> deletedMetric = this.metricStorage.getDataPointList(metricName);
            try{
                DataPoint deletedData = deletedMetric.get(deletedMetric.size() - 1);
            }catch (Exception e) {
                return presenter.prepareFailView("Metric can't undo - " + metricName
                        + " contains fewer than 1 data point");}
            DataPoint deletedData = deletedMetric.get(deletedMetric.size() - 1);
            this.metricStorage.removeDataPoint(metricName);
            double value = deletedData.getValue();
            String date = deletedData.getDate();
            EntryUndoResponseModel responseModel = new EntryUndoResponseModel(value, date, metricName);
            return presenter.prepareSuccessView(responseModel);
            } catch (Exception e) {
            return presenter.prepareFailView("Unknown Error");
            }

    }
}
