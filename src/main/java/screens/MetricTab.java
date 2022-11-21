package screens;

import entities.Metric;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MetricTab extends JPanel {
    private final JTabbedPane pane;

    public MetricTab(final JTabbedPane pane, String title){
        this.pane = pane;

        JLabel label = new JLabel(title);
        this.add(label);

        //create tab button
        JButton deleteButton = new DeleteButton();
        this.add(deleteButton);
    }

    private class DeleteButton extends JButton implements ActionListener{

        public DeleteButton(){
            this.setText("x");
            setToolTipText("Delete this metric");
            addActionListener(this);
        }

        public void actionPerformed(ActionEvent evt){
            int i = pane.indexOfTabComponent(MetricTab.this);
            pane.remove(i);
        }


    }


}
