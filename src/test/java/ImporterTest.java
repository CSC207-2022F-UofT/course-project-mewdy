import entities.DataPoint;
import entities.Metric;
import entities.MetricStorage;
import entities.MetricStorageInterface;
import models.ImportRequestModel;
import presenters.DataImportPresenter;
import presenters.DataImportPresenterOutputBoundary;
import use_cases.DataImportInputBoundary;
import use_cases.DataImporter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ImporterTest {
    MetricStorageInterface preMadeStorage;
    MetricStorageInterface storage;

    DataImportPresenterOutputBoundary presenter;
    DataImportInputBoundary importer;
    ImportRequestModel importReq;

    @BeforeEach
    void setUp() throws IOException {
        storage = new MetricStorage();
        presenter = new DataImportPresenter();
        importReq = new ImportRequestModel("./test data/metrics");
        importer = new DataImporter(storage, presenter);

        preMadeStorage = new MetricStorage();
        ArrayList<DataPoint> dataPoints = new ArrayList<>();
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

    private boolean isEqual(MetricStorageInterface s1, MetricStorageInterface s2) {
        for (int i = 0; i < s1.getMetricList().size(); i++) {
            if (!s1.getMetricList().get(i).equals(s2.getMetricList().get(i))) return false;
        }
        return true;
    }

}
