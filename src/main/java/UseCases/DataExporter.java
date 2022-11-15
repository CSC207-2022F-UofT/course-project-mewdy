package UseCases;

import Entities.DataPoint;
import Entities.Metric;
import Entities.MetricStorage;
import Models.ExportRequestModel;
import Models.ExportResponseModel;
import Presenters.DataExportPresenterOutputBoundary;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DataExporter implements DataExportInputBoundary {

    private MetricStorage storage;
    private File folder;
    private final DataExportPresenterOutputBoundary presenter;


    public DataExporter(ExportRequestModel req, DataExportPresenterOutputBoundary presenter) {
        this.folder = new File(req.getPath());
        this.storage = req.getStore();
        this.presenter = presenter;
        this.storage.setPath(this.folder);
    }

    @Override
    public ExportResponseModel writeToNewFile(ExportRequestModel req){
        this.folder = new File(req.getPath());
        this.storage = req.getStore();
        this.storage.setPath(this.folder);
        return write();
    }

    @Override
    public ExportResponseModel write() {

        BufferedWriter writer;
        for (Metric metric :
                storage.getMetricList()) {
            try {
                String currentFIle = folder.getPath() + "\\" + metric.getName();
                writer = new BufferedWriter(new FileWriter(currentFIle));
                String header = String.format("Date,Datapoint,%s,%s",metric.getUpperBound(), metric.getLowerBound());
                writer.write(header);
                writer.newLine();
                for (DataPoint dp :
                        metric.getDataPoints()) {
                    String line = String.format("%s,%s,,",dp.getDate(), dp.getValue());
                    writer.write(line);
                    writer.newLine();
                }
                writer.close();
            } catch (IOException e) {
                return presenter.prepareFailView("Error writing file");
            }
        }
        storage.save();
        return presenter.prepareSuccessView();
    }

    @Override
    public boolean filesExist() { //Check if a file will be overwritten
        File file;
        for (Metric m:
                storage.getMetricList()) {
            file = new File(folder + "\\" + m.getName() + ".csv");
            if(file.exists()) return true;
        }
        return false;
    }
}
