import Controllers.DataLoggerController;
import Entities.Metric;
import Entities.MetricStorage;
import UseCases.MetricAdder;

import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println(new Date());
        MetricStorage storage = new MetricStorage();
        Metric newMetric = new Metric("mood", 10, 0);
        storage.addMetric(newMetric);
        System.out.println(storage.getMetricList().get(0).getName());
        DataLoggerController logController = new DataLoggerController();
        logController.logDataPoint(3, "mood", "today I am sad", storage);
        logController.logDataPoint(8, "mood", "today I am sad", storage);
        System.out.println(newMetric.getDataPoints().get(0).getDate());
        System.out.println(newMetric.getDataPoints().get(0).getValue());
        System.out.println(newMetric.getDataPoints().get(0).getMetricName());
        System.out.println(newMetric.getDataPoints().get(0).getTextEntry());


    }


}
