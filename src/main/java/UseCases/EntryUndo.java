package UseCases;

import Entities.MetricStorage;
import Entities.DataPoint;
import Models.EntryUndoRequestModel;
import Models.EntryUndoResponseModel;
import Presenters.EntryUndoOutputBoundary;

import java.time.LocalDateTime;
import java.util.ArrayList;


public class EntryUndo implements EntryUndoInputBoundary{
    private final MetricStorage metricStorage;
    private final EntryUndoOutputBoundary presenter;

    public EntryUndo(MetricStorage metricStorage, EntryUndoOutputBoundary presenter){
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
            LocalDateTime date = deletedData.getDate();
            EntryUndoResponseModel responseModel = new EntryUndoResponseModel(value, date, metricName);
            return presenter.prepareSuccessView(responseModel);
            } catch (Exception e) {
            return presenter.prepareFailView("Unknown Error");
            }

    }
}
