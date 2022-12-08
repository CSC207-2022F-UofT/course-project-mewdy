package use_cases;

import entities.DataPoint;
import entities.Metric;
import entities.MetricStorageInterface;
import models.ExportRequestModel;
import models.ExportResponseModel;
import presenters.DataExportOutputBoundary;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class that represents the DataExporter use case that is responsible for exporting data to a csv file
 */

public class DataExporter implements DataExportInputBoundary {

    private MetricStorageInterface storage;
    private final DataExportOutputBoundary presenter;

    /**
     * Constructor for DataExporter
     *
     * @param metricStorage represents the metric storage that the use case will interact with
     * @param presenter represents the presenter that will present the output
     */
    public DataExporter(MetricStorageInterface metricStorage, DataExportOutputBoundary presenter) {
        this.storage = metricStorage;
        this.presenter = presenter;
    }

    /**
     * writeToNewFile writes data from the metric storage to a csv file
     *
     * @param req represents the request model for the DataExport use case
     * @return the response model for the DataExport use case
     */
    @Override
    public ExportResponseModel writeToNewFile(ExportRequestModel req) {
        this.storage.setPath(new File(req.getPath())); // Set MetricStorage path to the path in the request
        return write();
    }

    /**
     * Getter for the save status of the metric storage
     *
     * @return a boolean representing the save status of the metric storage
     */
    @Override
    public boolean getSaveStatus() {
        return this.storage.getSaveStatus();
    }

    /**
     * write writes data from the metric storage to a csv file
     *
     * @return the response model for the DataExport use case
     */
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
                int hasGoalStatus = (metric.getGoalStatus()) ? 1:0;
                String header = String.format("Date,Datapoint,%s,%s,%s,%s", metric.getUpperBound(),
                        metric.getLowerBound(), hasGoalStatus, metric.getGoal());
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

    /**
     * cleanUp removes deleted Metrics from the folder
     *
     * @param storage represents the metric storage
     */
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
