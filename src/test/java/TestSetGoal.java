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

    /**
     * Test success case of set goal for when user gives number input that is between the lower and upper bounds defined
     * by the metric.
     */
    @Test
    public void testSetGoalSuccess(){
        Metric metric = new Metric("Sleep", 0, 24);
        metricStorage.addMetric(metric);

        String successMessage = controller.setGoal(2.0, "Sleep");
        double goal = metric.getGoal();
        assertEquals(2.0, goal);
        assertEquals("Metric Goal was successfully set. See how you are meeting your" +
                " goal with the summary function!", successMessage);
    }

    /**
     * Test fail case of set goal for when user gives number input that is outside the permissive range defined by the
     * upper and lower bounds of chosen metric. Scenario for non-numerical input is not considered since the user is not
     * allowed to input anything that isn't a number.
     */
    @Test
    public void testSetGoalFail(){
        Metric metric = new Metric("Sleep", 0, 24);
        metricStorage.addMetric(metric);

        String failMessage = controller.setGoal(-1.0, "Sleep");
        assertEquals("Invalid input - please enter a number within the upper and lower" +
                " bounds of the metric", failMessage);
    }

}
