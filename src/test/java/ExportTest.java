import Entities.DataPoint;
import Entities.Metric;
import Entities.MetricStorage;
import Models.ExportRequestModel;
import Presenters.DataExportPresenter;
import Presenters.DataExportPresenterOutputBoundary;
import UseCases.DataExportInputBoundary;
import UseCases.DataExporter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ExportTest {
    MetricStorage ms;
    ArrayList<DataPoint> dataPoints;
    DataExportInputBoundary exporter;
    DataExportPresenterOutputBoundary presenter;

    @BeforeEach
    public void setUp() {
        ms = new MetricStorage();
        presenter = new DataExportPresenter();
        ExportRequestModel req = new ExportRequestModel("./", this.ms);
        exporter = new DataExporter(req, presenter);
        String[] metrics = {"play", "sleep", "work"};
        for (String name : metrics) {
            dataPoints = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                dataPoints.add(new DataPoint(i));
            }
            this.ms.addMetric(new Metric(name, dataPoints, 10, 0));
        }
    }

    @Test
    public void testExport() throws IOException {
        exporter.write();
        Assertions.assertTrue(isEqual(this.ms, read(new File("./metrics"))));
    }

    @Test
    public void testFilesExist() {
        Assertions.assertTrue(exporter.filesExist());
    }

    @Test
    public void testExportToNewFile() throws IOException {
        File f = new File("./new/metrics");
        f.deleteOnExit();
        exporter.writeToNewFile(new ExportRequestModel("./new", this.ms));
        Assertions.assertTrue(isEqual(this.ms, read(f)));
    }

    private MetricStorage read(File files) throws IOException {
        MetricStorage s = new MetricStorage();

        for (File file : Objects.requireNonNull(files.listFiles())) {
            if (!file.getName().contains(".csv")) continue;
            file.deleteOnExit();
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
