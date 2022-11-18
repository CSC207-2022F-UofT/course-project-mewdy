import entities.DataPoint;
import entities.Metric;

import entities.MetricStorage;
import models.ImportRequestModel;
import presenters.DataImportPresenter;
import presenters.DataImportPresenterOutputBoundary;
import use_cases.DataImportInputBoundary;
import use_cases.DataImporter;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        MetricStorage storage = new MetricStorage();
        DataImportPresenterOutputBoundary presenter = new DataImportPresenter();
        //ImportRequestModel importReq = new ImportRequestModel("./metrics", storage);
        ImportRequestModel importReq = new ImportRequestModel("./test data/metrics", storage); //DEBUG
        DataImportInputBoundary user;
        try {
            user = new DataImporter(importReq, presenter);
        } catch (IOException e) {
            throw new RuntimeException("File either does not exist or does not have access.");
        }
       user.read();

        // DEBUG
        //Print all metrics
        for (Metric metric:
                storage.getMetricList()) {
            System.out.println(metric.getName() + " " + metric.getUpperBound() + " " + metric.getLowerBound());
            for (DataPoint dp:
                    metric.getDataPoints()) {
                System.out.println(dp.getDate() + ", " + dp.getValue());
            }
            System.out.println();
        }
    }
}
