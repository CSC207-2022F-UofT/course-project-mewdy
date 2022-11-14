package MetricSummaryUseCase.Screens;

import Entities.Metric;
import MetricSummaryUseCase.Models.MetricSumRequestModel;
import Entities.MetricStorageInterface;
import MetricSummaryUseCase.Controllers.MetricSumController;
import MetricSummaryUseCase.Models.MetricSumResponseModel;
import MetricSummaryUseCase.Presenters.MetricSumViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ChooseMetricSumScreen extends JPanel implements ActionListener{

    MetricStorageInterface metricStorage;
    MetricSumController metricSumController;

    public ChooseMetricSumScreen(MetricStorageInterface metricStorageInterface,
                                 MetricSumController metricSumController){
        this.metricStorage = metricStorageInterface;
        this.metricSumController = metricSumController;

        JLabel title = new JLabel("Metric Summary");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add buttons for every Metric in MetricStorage.
        JPanel buttons = new JPanel();
        ArrayList<Metric> metricList = metricStorage.getMetricList();
        for (Metric m: metricList){
            JButton metricButton = new JButton(m.getName());
            buttons.add(metricButton);
            metricButton.addActionListener( this);
            metricButton.setActionCommand(m.getName());
        }

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(buttons);

    }

    //React to button press to summarize chosen Metric.
    public void actionPerformed(ActionEvent evt){
        try {
            MetricSumViewModel viewModel = metricSumController.getMetricSummary(evt.getActionCommand());
            String averageAndTrend = viewModel.getMetricAverageAndSize();
            JOptionPane.showMessageDialog(this, averageAndTrend);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
}
