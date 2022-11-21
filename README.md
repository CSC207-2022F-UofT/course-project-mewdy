# Project Mewdy

How to run:
- pull from FEATURE-metric-summary
- run Main.java
- when the java swing window opens, click import
- find the folder "test data" on your computer (it is located inside of the src folder)
- go inside of it and select the "metrics" folder and choose that
- if it says "success", then continue onto the next page.
- currently, we are having issues with button formatting MacOS for the tabs at the top of the screen when you press "record" it is recommended to use windows for testing currently
- you can [record] datapoints to metrics you choose. Pressing the X button next to each metric will delete them permanently.
- you can add datapoints to each metric once per day
- the only metric with more than 7 datapoints currently saved is "work"
- when you hit the [Summary] button, and then press the button corresponding to the "work" metric, you will be able to see the graph of a user's work every day
- hitting save will allow you to save a folder of csv files corresponding to every metric currently active.

Mewdy is a configurable mood tracking application with customizable metric builder.

Users can create a "metric" or category that takes numerical data they input each day and can later summarize that data graphically.

Users can create any metrics they wish, such as metrics for mood (on a scale of 1-10), sleep (on a scale of 0-24), and their very own metrics that take values in a range specified by them.

Users can view a summary of each metric, with the value of the data they logged each day on a line chart.

Users can log their data for each metric once per day. The data they log must be within the acceptable range for each metric. For example, if a user enters -5 for hours of sleep, it won't be inputted. If a user wants to change the data logged on that day, they can delete the most recently added data point.

Users can delete metrics, but all data from that metric will be deleted.

Data is imported from a csv file at the start if users do not specify that they want to create a new metric.
