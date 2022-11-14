package UseCases;

import Entities.MetricStorage;
import Entities.Metric;
import Entities.DataPoint;
import Models.EntryUndoRequestModel;
import Models.EntryUndoResponseModel;
import Presenters.EntryUndoPresenter;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;


public class EntryUndo {
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
            Date date = deletedData.getDate();
            String metric = deletedData.getMetricName();
            String responseModel = value + ", " + new SimpleDateFormat("dd/MM/yyyy").format(date) + ", " + metric;
            return presenter.prepareSuccessView(responseModel);
            } catch (Exception e) {
            return presenter.prepareFailView("Unknown Error");
            }

    }
}
