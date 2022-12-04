import controllers.SetGoalController;
import entities.Metric;
import entities.MetricStorage;
import entities.MetricStorageInterface;
import presenters.SetGoalOutputBoundary;
import presenters.SetGoalPresenter;
import use_cases.GoalSetter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestSetGoal {

    MetricStorageInterface metricStorage;
    SetGoalController controller;
    GoalSetter goalSetter;
    SetGoalOutputBoundary presenter;

    @BeforeEach
    public void setUp(){
        metricStorage = new MetricStorage();
        presenter = new SetGoalPresenter();
        goalSetter = new GoalSetter(metricStorage, presenter);
        controller = new SetGoalController(goalSetter);

    }

    @Test
    public void testSetGoalSuccess(){
        Metric metric = new Metric("Sleep", 0, 24);
        metricStorage.addMetric(metric);

        String successMessage = controller.setGoal(2.0, "Sleep");
        double goal = metric.getGoal();
        assertEquals(2.0, goal);
        assertEquals("Metric Goal was successfully set. See how you are meeting your" +
                " goal with the summary function!.", successMessage);
    }

    @Test
    public void testSetGoalFail(){
        Metric metric = new Metric("Sleep", 0, 24);
        metricStorage.addMetric(metric);

        String failMessage = controller.setGoal(-1.0, "Sleep");
        assertEquals("Invalid input - please enter a number within the upper and lower" +
                " bounds of the metric", failMessage);
    }

}
