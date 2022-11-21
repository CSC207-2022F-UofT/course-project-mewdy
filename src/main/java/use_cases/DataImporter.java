package use_cases;

<<<<<<< HEAD
public class DataImporter {

=======
import entities.DataPoint;
import entities.Metric;
import entities.MetricStorageInterface;
import models.ImportRequestModel;
import models.ImportResponseModel;
import presenters.DataImportPresenterOutputBoundary;

import java.io.*;
import java.text.ParseException;
import java.util.*;

public class DataImporter implements DataImportInputBoundary {

    private File folder;
    private MetricStorageInterface storage;
    private final DataImportPresenterOutputBoundary presenter;

    public DataImporter(MetricStorageInterface storage, DataImportPresenterOutputBoundary presenter){
        this.folder = storage.getPath();
        this.storage = storage;
        this.presenter = presenter;

    }

    @Override
    public ImportResponseModel readFromNewFile(ImportRequestModel req){
        this.folder = new File(req.getPath());
        this.storage.setPath(this.folder);
        return read();
    }
    
    @Override
    public ImportResponseModel read() {
        try {
            for (File file : Objects.requireNonNull(this.folder.listFiles())) {
                ArrayList<String> dates = new ArrayList<>();
                ArrayList<Double> data = new ArrayList<>();
                double upperBound = 0, lowerBound = 0;
                String fullFileName = file.getName();
                String filename = fullFileName.substring(0, fullFileName.lastIndexOf("."));

                if (fullFileName.endsWith(".csv")) {
                    BufferedReader r = new BufferedReader(new FileReader(file));
                    String line = r.readLine();
                    if (line == null) {
                        continue; // skip file if empty
                    } else {
                        String[] header = line.split(",");
                        upperBound = Double.parseDouble(header[2]);
                        lowerBound = Double.parseDouble(header[3]);
                    }
                    String row;
                    while ((row = r.readLine()) != null) {
                        String[] col = row.split(",");
                        dates.add(String.valueOf(col[0]));
                        data.add(Double.valueOf(col[1]));
                    }
                    r.close();
                }
                createMetric(dates, data, upperBound, lowerBound, filename);
                storage.save(); //State is replicated from storage
            }
        } catch (RuntimeException | IOException e) {
            System.out.println(e.getMessage());//DEBUG
            return presenter.prepareFailView("File either does not exist or does not have access.");
        } catch (ParseException e) {
            return presenter.prepareFailView("Bad data.");
        }
        return presenter.prepareSuccessView();
    }

    private void createMetric(
            ArrayList<String> dates, ArrayList<Double> data, double ub, double lb, String name) throws ParseException {

            ArrayList<DataPoint> dataPoints = new ArrayList<>();
            for (int i = 0; i < dates.size(); i++) {
                 dataPoints.add(new DataPoint(dates.get(i), data.get(i)));
            }
            storage.addMetric(new Metric(name, dataPoints, ub, lb));
    }
>>>>>>> 1a7e3b6895d936eee5e68cf04e9c29d52db1081f
}
