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

- tests can be found in the test file. More extensive testing will be implemented later.

Users can create a "metric" or category that takes numerical data they input each day and can later summarize that data graphically.

Users can create any metrics they wish, such as metrics for mood (on a scale of 1-10), sleep (on a scale of 0-24), and their very own metrics that take values in a range specified by them.

Users can view a summary of each metric, with the value of the data they logged each day on a line chart.

Users can log their data for each metric once per day. The data they log must be within the acceptable range for each metric. For example, if a user enters -5 for hours of sleep, it won't be inputted. If a user wants to change the data logged on that day, they can delete the most recently added data point.

Users can delete metrics, but all data from that metric will be deleted.

Data is imported from a directory containing csv files at the start if users do not specify that they want to create a new metric.
