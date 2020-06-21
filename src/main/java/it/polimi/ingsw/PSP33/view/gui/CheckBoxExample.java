package it.polimi.ingsw.PSP33.view.gui;

import it.polimi.ingsw.PSP33.model.God;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;


public class CheckBoxExample extends JFrame implements ActionListener {

    private List<God> gods;
    private boolean trigger;

    private JLabel l;
    private List<JCheckBox> checkBoxes;
    private JButton b;

    CheckBoxExample(List<God> godList, boolean trigger) {

        gods = godList;
        this.trigger = trigger;
        checkBoxes = new ArrayList<>();

        l = new JLabel("God choosing list");
        l.setBounds(30, 30, 300, 20);

        int i = 0;
        for (God god : godList) {
            JCheckBox cb1 = new JCheckBox(god.getName().name());
            cb1.setBounds(40, 60 + i, 150, 20);
            checkBoxes.add(cb1);
            add(cb1);
            i = i + 25;
        }

        b = new JButton("Order");
        b.setBounds(50, 80 + i, 80, 30);
        b.addActionListener(this);

        add(l);
        add(b);

        setSize(200, 475);
        setResizable(false);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {

        List<God> godList = new ArrayList<>();

        for (JCheckBox checkBox : checkBoxes) {
            if (checkBox.isSelected()) {
                godList.add(gods.get(checkBoxes.indexOf(checkBox)));
            }
        }

        for (God god : godList) {
            System.out.println(god.getName().name());
        }
    }
}
