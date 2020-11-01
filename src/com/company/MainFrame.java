package com.company;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {

    private JTextField xField = new JTextField("    ");
    private JTextField yField = new JTextField("    ");
    private JTextField zField = new JTextField("    ");
    private JLabel label = new JLabel("Input");
    private JButton buttonM = new JButton("M+");
    private JButton buttonMC = new JButton("MC");
    private JRadioButton RadioB1 = new JRadioButton("Formula #1", true);
    private JRadioButton RadioB2 = new JRadioButton("Formula #2", false);
    private JRadioButton Mem1=new JRadioButton("Memory #1", true);
    private JRadioButton Mem2=new JRadioButton("Memory #2", false);
    private JRadioButton Mem3=new JRadioButton("Memory #3", false);
    private double mem1=0;
    private double mem2=0;
    private double mem3=0;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 320;

    public MainFrame() {
        super("Calculator");
        this.setBounds(100, 100, WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new BoxLayout(container,BoxLayout.Y_AXIS));
        container.add(label);
        container.add(xField);
        container.add(yField);
        container.add(zField);

        ButtonGroup radioButtons = new ButtonGroup();
        radioButtons.add(RadioB1);
        radioButtons.add(RadioB2);
        container.add(RadioB1);
        container.add(RadioB2);

        ButtonGroup memoryButtons=new ButtonGroup();
        memoryButtons.add(Mem1);
        memoryButtons.add(Mem2);
        memoryButtons.add(Mem3);
        container.add(Mem1);
        container.add(Mem2);
        container.add(Mem3);
        buttonM.addActionListener(new ButtonEventListenerM());
        container.add(buttonM);
        buttonMC.addActionListener(new ButtonEventListenerMC());
        container.add(buttonMC);
    }

    public class ButtonEventListenerM implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {

                Double x = Double.parseDouble(xField.getText());
                Double y = Double.parseDouble(yField.getText());
                Double z = Double.parseDouble(zField.getText());
                Double res = RadioB1.isSelected() ? Fun_1(x, y, z) : Fun_2(x, y, z);

                String ChoosedMemory;
                double MemoryValue;
                if (Mem1.isSelected()){
                    mem1+=res;
                    ChoosedMemory="\nMemory#1: ";
                    MemoryValue=mem1;
                }
                else if (Mem2.isSelected()){
                    mem2+=res;
                    ChoosedMemory="\nMemory#2: ";
                    MemoryValue=mem2;
                }
                else{
                    mem3+=res;
                    ChoosedMemory="\nMemory#3: ";
                    MemoryValue=mem3;
                }
                JOptionPane.showMessageDialog(null, "Result: " + res + ChoosedMemory + MemoryValue, "M+ was pressed", JOptionPane.PLAIN_MESSAGE);
            }catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(null,"Incorrect entry of number", "Wrong input",JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public class ButtonEventListenerMC implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String ChoosedMemory;
            if (Mem1.isSelected()){
                mem1=0;
                ChoosedMemory="\nMemory#1";
            }
            else if (Mem2.isSelected()){
                mem2=0;
                ChoosedMemory="\nMemory#2";
            }
            else{
                mem3=0;
                ChoosedMemory="\nMemory#3";
            }
            JOptionPane.showMessageDialog(null, ChoosedMemory+" is cleared", "MC was pressed", JOptionPane.PLAIN_MESSAGE);
        }
    }

    public double Fun_1(double x, double y, double z) {
        return x + y + z;
    }

    public double Fun_2(double x, double y, double z) {
        return x * y * z;
    }
}
