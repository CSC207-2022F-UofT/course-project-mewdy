package use_cases;

import entities.DataPoint;
import entities.Metric;
import entities.MetricStorageInterface;
import models.ImportRequestModel;
import models.ImportResponseModel;
import presenters.DataImportOutputBoundary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Class that represents the DataImporter use case that is responsible for importing data from a csv file
 */

public class DataImporter implements DataImportInputBoundary {
    private final MetricStorageInterface storage;
    private final DataImportOutputBoundary presenter;

    /**
     * Constructor for DataImporter
     *
     * @param storage represents the metric storage that the use case will interact with
     * @param presenter represents the presenter that will present the output
     */
    public DataImporter(MetricStorageInterface storage, DataImportOutputBoundary presenter) {
        this.storage = storage;
        this.presenter = presenter;
    }

    /**
     * readFromNewFile reads data from a csv file and adds it to the metric storage
     *
     * @param req represents the request model for the DataImport use case
     * @return the response model for the DataImport use case
     */
    @Override
    public ImportResponseModel readFromNewFile(ImportRequestModel req) {
        this.storage.setPath(new File(req.getPath()));
        return read();
    }

    /**
     * read reads data from a csv file and adds it to the metric storage
     *
     * @return the response model for the DataImport use case
     */
    private ImportResponseModel read() {
        try {
            for (File file : Objects.requireNonNull(this.storage.getPath().listFiles())) {
                ArrayList<String> dates = new ArrayList<>();
                ArrayList<Double> data = new ArrayList<>();
                double upperBound = 0, lowerBound = 0;

                // Read file
                String fullFileName = file.getName();
                String filename = fullFileName.substring(0, fullFileName.lastIndexOf("."));

                // Filter csv files
                if (fullFileName.endsWith(".csv")) {
                    BufferedReader r = new BufferedReader(new FileReader(file));
                    String line = r.readLine();
                    if (line == null) {
                        continue; // skip file if empty
                    } else {
                        // Read header if file is not empty
                        String[] header = line.split(",");
                        upperBound = Double.parseDouble(header[2]);
                        lowerBound = Double.parseDouble(header[3]);
                    }
                    // Read data
                    String row;
                    while ((row = r.readLine()) != null) {
                        String[] col = row.split(",");
                        dates.add(String.valueOf(col[0]));
                        data.add(Double.valueOf(col[1]));
                    }
                    r.close();
                }
                createMetric(dates, data, upperBound, lowerBound, filename);
                storage.save(); // State should be identical from last save
            }
        } catch (RuntimeException | IOException e) {
            return presenter.prepareFailView("Folder either does not contain csv files or does not have access.");
        } catch (ParseException e) {
            return presenter.prepareFailView("Bad data.");
        }
        return presenter.prepareSuccessView();
    }

    /**
     * createMetric creates a metric from the data read from the csv file
     *
     * @param dates represents the dates of the data points
     * @param data represents the data read from the csv file
     * @param ub represents the upper bound of the metric
     * @param lb represents the lower bound of the metric
     * @param name represents the name of the metric
     * @throws ParseException if the date is not in the correct format?????????????idk if this is right
     */
    private void createMetric(
            ArrayList<String> dates, ArrayList<Double> data, double ub, double lb, String name) throws ParseException {
        // Create a temporary ArrayList to store the DataPoints to be added to the Metric
        ArrayList<DataPoint> dataPoints = new ArrayList<>();
        for (int i = 0; i < dates.size(); i++) {
            dataPoints.add(new DataPoint(dates.get(i), data.get(i)));
        }
        // Create a new Metric with the given name, upper and lower bounds, and the DataPoints
        // adds Metric to Storage
        storage.addMetric(new Metric(name, dataPoints, ub, lb));
    }
}
