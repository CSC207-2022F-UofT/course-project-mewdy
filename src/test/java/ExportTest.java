import entities.DataPoint;
import entities.Metric;
import entities.MetricStorage;
import models.ExportRequestModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import presenters.DataExportPresenter;
import presenters.DataExportPresenterOutputBoundary;
import use_cases.DataExportInputBoundary;
import use_cases.DataExporter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExportTest {
    MetricStorage ms;
    DataExportPresenterOutputBoundary presenter;
    DataExportInputBoundary exporter;
    ArrayList<DataPoint> dataPoints;

    @BeforeEach
    public void setUp() {
        this.ms = new MetricStorage();
        this.presenter = new DataExportPresenter();
        this.exporter = new DataExporter(ms, presenter);
        String[] metrics = {"play", "sleep", "work"};
        for (String name : metrics) {
            dataPoints = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                dataPoints.add(new DataPoint(i));
            }
            this.ms.addMetric(new Metric(name, dataPoints, 10, 0));
        }
    }

    @Test //test export to folder that does not exist
    public void testExportToNewFile() throws IOException {
        File f = new File("./new/metrics");
        exporter.writeToNewFile(new ExportRequestModel("./new"));
        assertTrue(isEqual(this.ms, read(f)));
    }

    @Test //test export to folder that does not exist
    public void testDeleteMetrics() throws IOException {
        File f = new File("./new"); // Initialize folders so that they will delete after test
        f.deleteOnExit();
        File f2 = new File("./new/metrics");
        f2.deleteOnExit();
        //this.ms.deleteMetric()
        exporter.writeToNewFile(new ExportRequestModel("./new"));
        assertTrue(isEqual(this.ms, read(f2)));
    }


    private MetricStorage read(File files) throws IOException {
        MetricStorage s = new MetricStorage();

        for (File file : Objects.requireNonNull(files.listFiles())) {
            if (!file.getName().contains(".csv")) continue;
            ArrayList<String> dates = new ArrayList<>();
            ArrayList<Double> data = new ArrayList<>();
            double upperBound = 0, lowerBound = 0;
            String fullFileName = file.getName();
            String extension = fullFileName.substring(fullFileName.lastIndexOf(".") + 1);
            String filename = fullFileName.substring(0, fullFileName.lastIndexOf("."));

            if (extension.equals("csv")) {
                BufferedReader r = new BufferedReader(new FileReader(file));
                String line = r.readLine();
                String[] header = line.split(",");
                upperBound = Double.parseDouble(header[2]);
                lowerBound = Double.parseDouble(header[3]);
                String row;
                while ((row = r.readLine()) != null) {
                    String[] col = row.split(",");
                    dates.add(String.valueOf(col[0]));
                    data.add(Double.valueOf(col[1]));
                }
                r.close();
            }
            createMetric(dates, data, upperBound, lowerBound, filename, s);
        }
        return s;
    }

    private void createMetric(
            ArrayList<String> dates, ArrayList<Double> data, double ub, double lb, String name, MetricStorage s) {

        ArrayList<DataPoint> dataPoints = new ArrayList<>();
        for (int i = 0; i < dates.size(); i++) {
            dataPoints.add(new DataPoint(dates.get(i), data.get(i)));
        }
        s.addMetric(new Metric(name, dataPoints, ub, lb));
    }

    private boolean isEqual(MetricStorage s1, MetricStorage s2) {
        for (int i = 0; i < s1.getMetricList().size(); i++) {
            if (!s1.getMetricList().get(i).equals(s2.getMetricList().get(i))) return false;
        }
        return true;
    }
}
