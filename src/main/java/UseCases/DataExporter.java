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

    private final MetricStorage storage;
    private final File folder;
    private final DataExportPresenterOutputBoundary presenter;


    public DataExporter(ExportRequestModel req, DataExportPresenterOutputBoundary presenter) {
        this.folder = new File(req.getPath());
        this.storage = req.getStore();
        this.presenter = presenter;
    }

    @Override
    public ExportResponseModel write() {

        BufferedWriter writer;
        for (Metric metric :
                storage.getMetricList()) {
            try {

                String currentFIle = folder.getPath() + metric.getName();
                writer = new BufferedWriter(new FileWriter(currentFIle));
                String header = "Date,Datapoint,%s,%s".formatted(metric.getUpperBound(), metric.getLowerBound());
                writer.write(header);
                writer.newLine();
                for (DataPoint dp :
                        metric.getDataPoints()) {
                    String line = "%s,%s,,".formatted(dp.getDate(), dp.getValue());
                    writer.write(line);
                    writer.newLine();
                }
                writer.close();
            } catch (IOException e) {
                return presenter.prepareFailView("Error writing file");
            }
        }
        return presenter.prepareSuccessView();
    }
}
