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
    /**
     * Export UseCase
     * Saves data to a metrics folder with corresponding metric csv files
     */

    private MetricStorageInterface storage;
    private final DataExportPresenterOutputBoundary presenter;


    public DataExporter(MetricStorageInterface metricStorage, DataExportPresenterOutputBoundary presenter) {
        this.storage = metricStorage;
        this.presenter = presenter;
    }

    @Override
    public ExportResponseModel writeToNewFile(ExportRequestModel req) {
        this.storage.setPath(new File(req.getPath())); // Set MetricStorage path to the path in the request
        return write();
    }

    @Override
    public boolean getSaveStatus() {
        return this.storage.getSaveStatus();
    }

    private ExportResponseModel write() {

        BufferedWriter writer;
        for (Metric metric : storage.getMetricList()) {
            try {
                // Create folders if they don't exist
                if (!this.storage.getPath().exists()) this.storage.getPath().mkdirs();

                // Get file name from metric name
                String currentFile = this.storage.getPath().getPath() + File.separator + metric.getName() + ".csv";

                // Write header to file
                writer = new BufferedWriter(new FileWriter(currentFile));
                String header = String.format("Date,Datapoint,%s,%s", metric.getUpperBound(), metric.getLowerBound());
                writer.write(header);
                writer.newLine();

                // Write data to file
                for (DataPoint dp : metric.getDataPoints()) {
                    String line = String.format("%s,%s,,", dp.getDate(), dp.getValue());
                    writer.write(line);
                    writer.newLine();
                }
                writer.close();

                // Remove deleted Metrics from folder
                cleanUp(this.storage);

            } catch (IOException e) {
                return presenter.prepareFailView(e.getMessage());
            }
        }
        storage.save(); // Indicate that changes have been saved
        return presenter.prepareSuccessView();
    }

    private void cleanUp(MetricStorageInterface storage) {
        // Delete removed metrics from the metrics folder
        File[] files = this.storage.getPath().listFiles();
        files:
        for (File f : files) {
            if (f.getPath().endsWith(".csv")) {
                for (Metric m : storage.getMetricList()) {
                    if (f.getName().equals(m.getName() + ".csv")) continue files;
                }
                f.delete();
            }
        }
    }
}
