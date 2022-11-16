package UseCases;

import Entities.MetricStorage;
import Entities.DataPoint;
import Models.EntryUndoRequestModel;
import Models.EntryUndoResponseModel;
import Presenters.EntryUndoPresenter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.text.SimpleDateFormat;


public class EntryUndo implements EntryUndoInputBoundary{
    private MetricStorage metricStorage;
    private EntryUndoPresenter presenter;

    public EntryUndo(MetricStorage metricStorage, EntryUndoPresenter presenter){
        this.metricStorage = metricStorage;
        this.presenter = presenter;
    }

    public EntryUndoResponseModel deleteDatapoint(EntryUndoRequestModel requestModel){
        String metricName = requestModel.getMetricName();
        try{
            ArrayList deletedMetric = this.metricStorage.getDataPointList(metricName);
            DataPoint deletedData = (DataPoint) deletedMetric.get(-1);
            this.metricStorage.removeDataPoint(metricName);
            if (deletedData.getDate() == null){
                return presenter.prepareFailView("Metric can't undo - " + metricName
                        + " contains fewer than 1 data point");
            }
            double value = deletedData.getValue();
            LocalDateTime date = deletedData.getDate();
            String responseModel = value + ", " + new SimpleDateFormat("dd/MM/yyyy").format(date) + ", "
                    + metricName;
            return presenter.prepareSuccessView(responseModel);
            } catch (Exception e) {
            return presenter.prepareFailView("Unknown Error");
            }

    }
}
