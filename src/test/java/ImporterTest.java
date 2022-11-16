import Entities.DataPoint;
import Entities.Metric;
import Entities.MetricStorage;
import Models.ImportRequestModel;
import Presenters.DataImportPresenter;
import Presenters.DataImportPresenterOutputBoundary;
import UseCases.DataImportInputBoundary;
import UseCases.DataImporter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ImporterTest {
    MetricStorage preMadeStorage;
    MetricStorage storage;

    DataImportPresenterOutputBoundary presenter;
    DataImportInputBoundary importer;
    ImportRequestModel importReq;

    @BeforeEach
    void setUp() throws IOException {
        storage = new MetricStorage();
        presenter = new DataImportPresenter();
        importReq = new ImportRequestModel("./test data/metrics", storage);
        importer = new DataImporter(importReq, presenter);

        preMadeStorage = new MetricStorage();
        ArrayList<DataPoint> dataPoints = new ArrayList<DataPoint>();
        dataPoints.add(new DataPoint("2022-11-11 12:47:18", 1));
        dataPoints.add(new DataPoint("2022-11-12 12:47:18", 3));
        dataPoints.add(new DataPoint("2022-11-14 12:47:18", 5));
        dataPoints.add(new DataPoint("2022-11-15 12:47:18", 8));
        dataPoints.add(new DataPoint("2022-11-16 12:47:18", 1));

        preMadeStorage.addMetric(new Metric("focus", dataPoints, 10,1));
        preMadeStorage.addMetric(new Metric("sleep", dataPoints, 24,0));
        preMadeStorage.addMetric(new Metric("testmetric", dataPoints, 10,1));
        preMadeStorage.addMetric(new Metric("work", dataPoints, 10,1));
    }

    @Test
    void read() {
        importer.read();
        assertTrue(isEqual(storage, preMadeStorage));
    }

    @Test
    void readFromNewFile() {
        importer.readFromNewFile(importReq);
        assertTrue(isEqual(storage, preMadeStorage));
    }

    private boolean isEqual(MetricStorage s1, MetricStorage s2) {
        for (int i = 0; i < s1.getMetricList().size(); i++) {
            if (!s1.getMetricList().get(i).equals(s2.getMetricList().get(i))) return false;
        }
        return true;
    }

}