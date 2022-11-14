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
    }
}