# Project Mewdy

Mewdy is a configurable mood tracking application with customizable metric tracker.

## How to run:
- run Main.java
- when the java swing window opens, click import
- find the folder "test data" on your computer (it is located inside the test data folder within the directory for this project)
- go inside it and select the "metrics" folder and press the 'open' button.
- if it says "success", then the program will take you to the home page.
- currently, we are having issues with button formatting MacOS for the tabs at the top of the screen when you press "record" it is recommended to use windows for testing currently
- you can [record] datapoints to metrics you choose. Pressing the X button next to each metric will delete them permanently. The program will prompt for user confirmation before deleting any metric.
- you can add datapoints to each metric once per day
- all imported metrics will have the same 9 datapoints. Feel free to modify the csv files to see how things change in the application.
- when you hit the [Summary] button, and then press the button for any metric, you will be able to see the graph of the metric along with its average and number of data points stored.
- the save and undo entry buttons are NOT implemented yet.

Users can create a "metric" or category that takes numerical data they input each day and can later summarize that data graphically.

Users can create any metrics they wish, such as metrics for mood (on a scale of 1-10), sleep (on a scale of 0-24), and their very own metrics that take values in a range specified by them.

Users can view a summary of each metric, with the value of the data they logged each day on a line chart.

Users can log their data for each metric once per day. The data they log must be within the acceptable range for each metric. For example, if a user enters -5 for hours of sleep, it won't be inputted. If a user wants to change the data logged on that day, they can delete the most recently added data point.

Users can delete metrics, but all data from that metric will be deleted.

Data is imported from a directory containing csv files at the start if users do not specify that they want to create a new metric.

## Code Structure

### Entities
- Datapoint, contains a date and value, has getter and setter methods as well as an equals method and a date formatting method
- Metric, contains an arraylist of datapoints, has a name, upper bound and lowerbound. Methods include getter, setter, equals, and overridden constructor for creating a new metric
- MetricStorage, container for metrics, has getter and setter methods, accessed through an interface
### Use Cases
- Add Metric, adds a metric to metric storage
- Delete Metric, removes a metric from metric storage
- Data Logger, adds a datapoint to a metric
- Undo Entry, undoes the most recent datapoint logged to a metric
- Import/export, accesses csv files to store and retrieve data to be used in the metric storage.
- Metric Summary, creates a visualization of datapoints and statistically manipulates data, such as getting mean value for datapoints
### Controllers, Presenters, Output/Input layers
- Each use case has a controller, presenter, and associated input/output boundaries.
### Screens, models
- Screens and view models are used for the UI component of the program. 
- They have associated use cases as well as functionalities with the outer most layer of the program.

## Test cases

### EntryUndoTest
- undo tests whether removing an entry causes the metric storage containing said entry to be different from its previous state

### ExportTest
- testExport checks that the memory is organized the correct way
- testFilesExist checks that the correct files exist
- testExportToNewFile checks to make sure that exporting to a file that does not exist functions as expected

### ImporterTest
- read tests that read functionality imports files correctly
- readFromNewFile tests reading from a new file

### MetricDeleterTest
- testMetricDeleterSuccess tests a case of deleting a metric that is expected to work
- testMetricDeleterFail tests a case of deleting a metric that is not expected to work, missing metric
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
- testMetricSummarizerFail test that Metric Summarizer throws DataSummaryFailed exception when Metric contains fewer than 7 dataPoints. Metric needs to contain >= 7 datapoints for summary function to work.
