package use_cases;

import entities.DataPoint;
import entities.Metric;
import entities.MetricStorageInterface;
import models.MetricSumRequestModel;
import presenters.MetricSumViewModel;

import java.util.ArrayList;

public class GoalTrackingDecorator extends MetricSummarizerDecorator{

    MetricStorageInterface metricStorage;

    public GoalTrackingDecorator(MetricSumInputBoundary metricSummarizer, MetricStorageInterface metricStorage){
        super(metricSummarizer);
        this.metricStorage = metricStorage;
    }

    public MetricSumViewModel getMetricSummary(MetricSumRequestModel requestModel){

        MetricSumViewModel viewModel = metricSummarizer.getMetricSummary(requestModel);
        if (checkHasGoal(requestModel.getMetricName())) {
            addGoalTrackingStats(requestModel, viewModel);
        }
        return viewModel;
    }

    private void addGoalTrackingStats(MetricSumRequestModel requestModel, MetricSumViewModel viewModel){
        String metricName = requestModel.getMetricName();
        Metric metric = metricStorage.getMetric(metricName);
        double goal = metric.getGoal();

        // Tally all days when datapoint met goal
        ArrayList<DataPoint> dataPoints = metric.getDataPoints();
        double goalMetCount = 0.0;
        for (DataPoint d: dataPoints){
            if (d.getValue() >= goal){goalMetCount = goalMetCount + 1.0;}
        }

        // Give message depending on how user is performing with respect to goal
        double performance = goalMetCount / dataPoints.size();
        int value = (int) goalMetCount;
        String stat;
        if (performance > 0.5){
            stat = "You have met your goal " + value + " times out of the " +
                    dataPoints.size() + " days tracked. Keep it up!";
        }
        else {
            stat = "You have met your goal " + value + " times out of the " +
                    dataPoints.size() + " days tracked. Keep improving!";
        }
        viewModel.setGoalStat(stat);

        // Modify view model's metric stats to include goal
        viewModel.setMetricStatistics(viewModel.getMetricStatistics() + "; Goal: " + goal);

    }

    private boolean checkHasGoal(String metricName){
        Metric metric = metricStorage.getMetric(metricName);
        return metric.getGoalStatus();
    }
}
