import Entities.DataPoint;
import Entities.Metric;
import Entities.MetricStorage;
import Models.ExportRequestModel;
import UseCases.DataExportInputBoundary;
import UseCases.DataExporter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;

import static org.junit.Assert.*;

public class TestExport {
    MetricStorage ms;
    ArrayList<DataPoint> dataPoints;
    DataExportInputBoundary exporter;

    @Before
    public void setUp() {
        ms = new MetricStorage();
        ExportRequestModel req = new ExportRequestModel("./", this.ms);
        exporter = new DataExporter(req, null);
        String[] metrics = {"sleep", "work", "play"};
        for (String name : metrics) {
            dataPoints = new ArrayList<DataPoint>();
            for (int i = 0; i < 10; i++) {
                dataPoints.add(new DataPoint(i));
            }
            this.ms.addMetric(new Metric(name, dataPoints, 10, 0));
        }
    }

    @Test(timeout = 50)
    public void testExport() throws IOException {
        exporter.write();
        assertTrue(isEqual(this.ms, read(new File("./"))));
    }

    @Test(timeout = 50)
    public void testFilesExist() {
        assertTrue(exporter.filesExist());
    }

    @Test(timeout = 50)
    public void testExportToNewFile() throws IOException {
        exporter.writeToNewFile(new ExportRequestModel("./new", this.ms));
        assertTrue(isEqual(this.ms, read(new File("./new"))));
    }

    @AfterAll
    public static void cleanUp() {
        File f;
        String[] toClean = {"./metric", "./new"};
        for (String s :
                toClean) {
            f = new File(s);
            if (//f.delete()
                    true) {
                System.out.println("Deleted the file: " + Arrays.toString(f.listFiles()));
            } else {
                System.out.println("Cleanup failed");
            }
        }
    }

    private MetricStorage read(File files) throws IOException {
        MetricStorage s = new MetricStorage();

        for (File file : Objects.requireNonNull(files.listFiles())) {
            ArrayList<String> dates = new ArrayList<String>();
            ArrayList<Double> data = new ArrayList<Double>();
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

        ArrayList<DataPoint> dataPoints = new ArrayList<DataPoint>();
        for (int i = 0; i < dates.size(); i++) {
            dataPoints.add(new DataPoint(dates.get(i), data.get(i)));
        }
        s.addMetric(new Metric(name, dataPoints, ub, lb));
    }

    private boolean isEqual(MetricStorage s1, MetricStorage s2) {
        if (s1.getMetricList().containsAll(s2.getMetricList())) {
            for(int i = 0; i < s1.getMetricList().size(); i++) {
              if (s1.getMetricList().get(i) != s2.getMetricList().get(i)) return false;
            }
            return true;
        }
        return false;
    }
}
