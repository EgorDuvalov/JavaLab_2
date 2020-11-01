package com.company;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {

    private JTextField xField = new JTextField("____");
    private JTextField yField = new JTextField("____");
    private JTextField zField = new JTextField("____");
    private JTextField resField = new JTextField("HELLO");
    private JLabel label = new JLabel("Input");
    private JButton button = new JButton("Press");
    private JRadioButton RadioB1 = new JRadioButton("VARIANT_1", true);
    private JRadioButton RadioB2 = new JRadioButton("VARIANT_2", false);
    private static final int WIDTH = 400;
    private static final int HEIGHT = 320;

    public MainFrame() {
        super("MainFrame");
        this.setBounds(100, 100, WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(2,5,10,10));
        container.add(label);
        container.add(resField);
        container.add(xField);
        container.add(yField);
        container.add(zField);

        ButtonGroup radioButtons = new ButtonGroup();
        radioButtons.add(RadioB1);
        radioButtons.add(RadioB2);
        container.add(RadioB1);
        container.add(RadioB2);
        button.addActionListener(new ButtonEventListener());
        container.add(button);
    }

    public class ButtonEventListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String message = "Button was pressed\n";
            message += (RadioB1.isSelected() ? "Radio#1" : "Radio#2") + " is selected";
            xField.setText("HELLO");
            yField.setText("HELLO");
            zField.setText("HELLO");
            JOptionPane.showMessageDialog(null,message,"Otput",JOptionPane.PLAIN_MESSAGE);
        }
    }

    public double Fun_1(double x, double y, double z) {
        return x + y + z;
    }

    public double Fun_2(double x, double y, double z) {
        return x * y * z;
    }
}
