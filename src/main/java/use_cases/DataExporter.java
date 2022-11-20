package use_cases;

import entities.DataPoint;
import entities.Metric;
import entities.MetricStorageInterface;
import models.ExportRequestModel;
import models.ExportResponseModel;
import presenters.DataExportPresenterOutputBoundary;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DataExporter implements DataExportInputBoundary {

    private MetricStorageInterface storage;
    private File folder;
    private final DataExportPresenterOutputBoundary presenter;


    public DataExporter(ExportRequestModel req, DataExportPresenterOutputBoundary presenter) {
        this.storage = req.getStore();
        this.presenter = presenter;
        this.storage.setPath(new File(req.getPath()));
        this.folder = this.storage.getPath();
    }

    @Override
    public ExportResponseModel writeToNewFile(ExportRequestModel req) {
        this.storage = req.getStore();
        this.storage.setPath(new File(req.getPath()));
        this.folder = this.storage.getPath();
        return write();
    }

    @Override
    public ExportResponseModel write() {

        BufferedWriter writer;
        for (Metric metric : storage.getMetricList()) {
            try {
                if (!this.folder.exists()) this.folder.mkdirs();
                String currentFile = this.folder.getPath() + "\\" + metric.getName() + ".csv";
                writer = new BufferedWriter(new FileWriter(currentFile));
                String header = String.format("Date,Datapoint,%s,%s", metric.getUpperBound(), metric.getLowerBound());
                writer.write(header);
                writer.newLine();
                for (DataPoint dp : metric.getDataPoints()) {
                    String line = String.format("%s,%s,,", dp.getDate(), dp.getValue());
                    writer.write(line);
                    writer.newLine();
                }
                writer.close();
            } catch (IOException e) {
                return presenter.prepareFailView(e.getMessage());
            }
        }
        storage.save();
        return presenter.prepareSuccessView();
    }

    @Override
    public boolean filesExist() { //Check if a file will be overwritten
        File file;
        for (Metric m : storage.getMetricList()) {
            file = new File(this.folder + "\\" + m.getName() + ".csv");
            if (file.exists()) return true;
        }
        return false;
    }
}
