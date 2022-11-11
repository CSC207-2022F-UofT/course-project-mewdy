import Controllers.DataLoggerController;
import Entities.Metric;
import Entities.MetricStorage;
import Entities.MetricStorageInterface;
import Presenters.DataLoggerPresenter;
import UseCases.DataLoggerInputBoundary;
import UseCases.DataLoggerOutputBoundary;
import UseCases.MetricAdder;

import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        MetricStorageInterface storage = new MetricStorage();
        Metric newMetric = new Metric("mood", 10, 0);
        storage.addMetric(newMetric);
        //
        DataLoggerController loggerController = new DataLoggerController(storage);
        //
        System.out.println(loggerController.logDataPoint(7, "mood", "bingus").getMessage());
        System.out.println(storage.getMetric("mood").getDataPoints().get(0).getValue());
        System.out.println(loggerController.logDataPoint(11, "mood", "this value won't work").getMessage());
    }


}
