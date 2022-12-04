package screens;

import controllers.SetGoalController;
import entities.Metric;
import entities.MetricStorageInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SetGoalScreen extends JTabbedPane implements Refreshable, ActionListener {

    MetricStorageInterface metricStorage;
    SetGoalController setGoalController;
    CardLayout cardLayout;
    JPanel screens;
    JButton backButton;


    public SetGoalScreen(MetricStorageInterface metricStorage, SetGoalController setGoalController,
                         CardLayout cardLayout, JPanel screens) {
        this.metricStorage = metricStorage;
        this.setGoalController = setGoalController;
        this.cardLayout = cardLayout;
        this.screens = screens;

        this.setName("SetGoal");

        this.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);


        ArrayList<Metric> metricList = metricStorage.getMetricList();
        // If there are no metrics in metric storage, go back.
        if (metricList.size() == 0){
            JLabel emptyStorageMessage = new JLabel("No metrics are being tracked!");
            backButton = new JButton("Back");
            backButton.addActionListener(this);
            this.add(emptyStorageMessage);
            this.add(backButton);

        }
        // Add tabs for every Metric in MetricStorage.
        else {
            for (Metric m : metricList) {
                String metricName = m.getName();
                double upperBound = m.getUpperBound();
                double lowerBound = m.getLowerBound();
                SetGoalTab tab = new SetGoalTab(metricName, upperBound, lowerBound, setGoalController,
                        cardLayout, screens);
                this.add(metricName, tab);
            }
        }
    }

    public void actionPerformed(ActionEvent evt){
        if (evt.getSource() == backButton) {
            cardLayout.show(screens, "home");
        }
    }

    @Override
    public void refresh(){
        this.removeAll();

        this.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        ArrayList<Metric> metricList = metricStorage.getMetricList();
        // If there are no metrics in metric storage, go back.
        if (metricList.size() == 0){
            JLabel emptyStorageMessage = new JLabel("Metric Storage is empty!");
            backButton = new JButton("Back");
            backButton.addActionListener(this);
            JPanel panel = new JPanel();
            panel.add(emptyStorageMessage);
            panel.add(backButton);
            this.add(panel);

        }
        // Add tabs for every Metric in MetricStorage.
        else {
            for (Metric m : metricList) {
                String metricName = m.getName();
                double upperBound = m.getUpperBound();
                double lowerBound = m.getLowerBound();
                SetGoalTab tab = new SetGoalTab(metricName, upperBound, lowerBound, setGoalController,
                        cardLayout, screens);
                this.add(metricName, tab);
            }
        }

        this.revalidate();
        this.repaint();
    }


}
