# Project Mewdy

Mewdy is a configurable metric tracking application.

## How to run:
- run Main.java
- when the java swing window opens, click import
- find the folder "test data" on your computer (it is located inside the test data folder within the directory for this project)
- go inside it and select the "metrics" folder and press the 'open' button.
- if it says "success", then the program will take you to the home page.
- you can [record] datapoints to metrics you choose. Pressing the X button next to each metric will delete them permanently (NOTE: the tabs do not render properly on MacOS). The program will prompt for user confirmation before deleting any metric.
- you can add datapoints to each metric once per day
- data entries for any metric containing datapoints can be deleted with the undo button. This will remove the most recent entry.
- all imported metrics will have pre-existing datapoints. Some will have goals already being tracked and some will not. Feel free to modify the csv files to see how things change in the application.
- you can [add] metrics in the add metric tab in the same screen where you record datapoints. There are two preset metrics you can add, sleep and mood. You can also create a custom metric by defining a name, upper bound, and lower bound. Note that duplicates are not allowed.
- when you hit the [Summary] button, and then press the button for any metric, you will be able to see the graph of the metric along with its average, number of data points stored, and goal performance if the metric has a goal.
- click the save button to save changes 
- export your data through the export button on the home screen. The program will prompt the user for a location to which a folder will be created containing csvs for each metric in metric storage.

## Code Structure
Classes are organized into packages based on which component of Clean Architecture they are associated with. For example, all controller classes will be found in the controller package.

### Entities
- Datapoint, contains a date and value, has getter and setter methods as well as an equals method and a date formatting method
- Metric, contains an arraylist of datapoints, has a name, upper bound and lowerbound. Methods include getter, setter, equals, and overridden constructor for creating a new metric
- MetricStorage, container for metrics, has getter and setter methods, accessed through an interface
### Use Cases
- Add metric, adds a metric to metric storage
- Delete metric, removes a metric from metric storage
- Data logging, adds a datapoint to a metric
- Undo entry, undoes the most recent datapoint logged to a metric
- Import/export, accesses csv files to store and retrieve data to be used in the metric storage.
- Goal tracking, allows the user to set a goal for currently tracked metrics.
- Metric Summary, creates a visualization of metric data over time and displays the average value of a metric as well as the number of data points it stores.
  - If the metric to be summarized contains a goal, additional information will be displayed about the user's performance with respect to the set goal.
### Controllers, Presenters, Output/Input layers
- Each use case has a controller, presenter, and associated input/output boundaries.
### Screens, models
- Screens and view models are used for the UI component of the program. 
- They have associated use cases as well as functionalities with the outer most layer of the program.

## Test cases

### EntryUndoTest
- undo tests whether removing an entry causes the metric storage containing said entry to be different from its previous state

### ExportTest
- testExportToNewFile checks to make sure that exporting to a file that does not exist functions as expected
- testDeleteMetrics checks that exporting works with delete metric so that the exported file matches the state of metric storage

### ImporterTest
- readFromNewFile tests reading from a new file

### MetricDeleterTest
- testMetricDeleterSuccess tests a case of deleting a metric that is expected to work
- testMetricDeleterFailure tests a case of deleting a metric that is not expected to work, missing metric
### TestAddMetric
- testAddMetricData tests that adding a metric changes the values of Metric Storage correctly
- testAddMetricWithPreexistingMetric tests that adding a metric to a storage with preexisting metrics functions properly (no overwrite)
- testAddMetricPresenterSuccess tests that the presenter sends a success message that is correct
- testAddMetricPresenterFailure tests that the presenter sends a failure message when unable to add a metric (a metric with that name already exists)
### TestDataLogger
- testLogDataPointMissingMetric tests the case where there is no metric to add datapoints to
- testLogDataPointMissingMetricName tests the case where the incorrect metric name is used as input
- testLogDataPointSuccess tests a successful additon of a datapoint
- testLogDataPointMultipleMetrics tests adding datapoint to one of multiple metrics
- testInvalidValue tests that an invalid value (a value beyond the upperlower bounds) cannot be added
- testLogDataPointOverwrite makes sure that adding multiple datapoints on the same day doesn't add more
- testLogDataPointNoOverwrite2 makes sure that datapoints added on the same day don't overwrite their values
- testLogDataPointOnLongList makes sure that datapoints can be added while other datapoints are in the arraylist at other dates
- testLogDataPointOnLongListValuesCorrect makes sure that datapoints values are consistent after adding datapoints on different days
- testLogDataPointReallyLongList tests that adding many datapoints doesn't interfere with the usecase
### TestMetricSumUseCase
- testMetricSummarizerSuccess test that Metric Summarizer returns view model with properly formatted String displaying avg and size of metric
- testMetricSummarizerFail test that Metric Summarizer throws DataSummaryFailed exception when Metric contains 0 dataPoints. Metric needs to contain >= 1 datapoints for summary function to work.
- testGoalTracking checks that the output of metric summary when called on a metric with a goal will have additional information relevant to goal performance
### TestSetGoal
- testSetGoalSuccess tests the success case of goal setting in which the user input is permissive i.e. within the upper and lower bounds of the chosen metric
- testSetGoalFail tests the fail case of goal setting in which the user input falls outside the permissive range allowed by the metric's upper and lower bounds.


## Design Patterns

### Decorator
- Design pattern implemented to modify functionality of metric summarizer use case interactor. 
- An abstract decorator implements the MetricSumInputBoundary 
- A concrete decorator for goal tracking extends the abstract decorator class, giving the method getMetricSummary additional functionality when the chosen input metric is associated with a goal.
- When metric summary is called on metrics associated with a goal, the GoalTrackingDecorator is called as the MetricSumInputBoundary.