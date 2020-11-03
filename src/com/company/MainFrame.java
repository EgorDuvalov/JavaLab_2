package com.company;


import com.sun.tools.jconsole.JConsolePlugin;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.zip.ZipEntry;

public class MainFrame extends JFrame {

    private JPanel panel = new JPanel();
    private JTextField xField = new JTextField(" ");
    private JTextField yField = new JTextField(" ");
    private JTextField zField = new JTextField(" ");
    private JTextField resField = new JTextField(" ");
    private JLabel Res = new JLabel("Res");
    private JButton buttonC = new JButton("Calculate");
    private JButton buttonM = new JButton("M+");
    private JButton buttonMC = new JButton("MC");
    private JRadioButton RadioB1 = new JRadioButton("Formula #1", true);
    private JRadioButton RadioB2 = new JRadioButton("Formula #2", false);
    private ImagePanel image = new ImagePanel();
    private JRadioButton Mem1 = new JRadioButton("Memory #1", true);
    private JRadioButton Mem2 = new JRadioButton("Memory #2", false);
    private JRadioButton Mem3 = new JRadioButton("Memory #3", false);
    private JTextField m1 = new JTextField("0.0");
    private JTextField m2 = new JTextField("0.0");
    private JTextField m3 = new JTextField("0.0");
    private Double mem1 = 0.0;
    private Double mem2 = 0.0;
    private Double mem3 = 0.0;
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;

    public MainFrame() {
        super("Calculator");
        this.setBounds(100, 100, WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Box container = Box.createVerticalBox();

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2));
        inputPanel.add(new JLabel("X"));
        inputPanel.add(xField);
        inputPanel.add(new JLabel("Y"));
        inputPanel.add(yField);
        inputPanel.add(new JLabel("Z"));
        inputPanel.add(zField);
        inputPanel.add(new JLabel("Result"));
        inputPanel.add(resField);
        container.add(inputPanel);

        ButtonGroup radioButtons = new ButtonGroup();
        RadioB1.addActionListener(new R1ButtonEventListener());
        RadioB2.addActionListener(new R2ButtonEventListener());
        radioButtons.add(RadioB1);
        radioButtons.add(RadioB2);
        JPanel radioP = new JPanel();
        radioP.add(RadioB1);
        radioP.add(RadioB2);
        container.add(radioP);

        container.add(image);

        ButtonGroup memoryButtons = new ButtonGroup();
        memoryButtons.add(Mem1);
        memoryButtons.add(Mem2);
        memoryButtons.add(Mem3);
        panel.setLayout(new GridLayout(2, 3));
        panel.add(Mem1);
        panel.add(Mem2);
        panel.add(Mem3);
        panel.add(m1);
        panel.add(m2);
        panel.add(m3);
        container.add(panel);

        buttonC.addActionListener(new ButtonEventListenerC());
        buttonM.addActionListener(new ButtonEventListenerM());
        buttonMC.addActionListener(new ButtonEventListenerMC());

        JPanel comP = new JPanel();
        comP.add(buttonC);
        comP.add(buttonMC);
        comP.add(buttonM);
        container.add(comP);

        getContentPane().add(container);
    }

    public class ButtonEventListenerC implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                Double x = Double.parseDouble(xField.getText());
                Double y = Double.parseDouble(yField.getText());
                Double z = Double.parseDouble(zField.getText());
                Double res = RadioB1.isSelected() ? Fun_1(x, y, z) : Fun_2(x, y, z);
                resField.setText(res.toString());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Incorrect entry of number", "Wrong input", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public class ButtonEventListenerM implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {

                Double x = Double.parseDouble(xField.getText());
                Double y = Double.parseDouble(yField.getText());
                Double z = Double.parseDouble(zField.getText());
                Double res = RadioB1.isSelected() ? Fun_1(x, y, z) : Fun_2(x, y, z);
                resField.setText(res.toString());

                String ChoosedMemory;
                double MemoryValue;
                if (Mem1.isSelected()) {
                    mem1 += res;
                    ChoosedMemory = "\nMemory #1: ";
                    MemoryValue = mem1;
                    m1.setText(mem1.toString());
                } else if (Mem2.isSelected()) {
                    mem2 += res;
                    ChoosedMemory = "\nMemory #2: ";
                    MemoryValue = mem2;
                    m2.setText(mem2.toString());
                } else {
                    mem3 += res;
                    ChoosedMemory = "\nMemory #3: ";
                    MemoryValue = mem3;
                    m3.setText(mem3.toString());
                }
                JOptionPane.showMessageDialog(null, "Result: " + res + ChoosedMemory + MemoryValue, "M+ was pressed", JOptionPane.PLAIN_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Incorrect entry of number", "Wrong input", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public class ButtonEventListenerMC implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String ChoosedMemory;
            if (Mem1.isSelected()) {
                mem1 = 0.0;
                ChoosedMemory = "\nMemory #1";
                m1.setText(mem1.toString());
            } else if (Mem2.isSelected()) {
                mem2 = 0.0;
                ChoosedMemory = "\nMemory #2";
                m2.setText(mem2.toString());
            } else {
                mem3 = 0.0;
                ChoosedMemory = "\nMemory #3";
                m3.setText(mem3.toString());
            }
            JOptionPane.showMessageDialog(null, ChoosedMemory + " is cleared", "MC was pressed", JOptionPane.PLAIN_MESSAGE);
        }
    }

    public class R1ButtonEventListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            image.ChangeFormulaToFst();
            image.repaint();
        }
    }

    public class R2ButtonEventListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            image.ChangeFormulaToSnd();
            image.repaint();
        }
    }

    public class ImagePanel extends JPanel {
        private BufferedImage image;

        public ImagePanel() {
            try {
                image = ImageIO.read(new File("Formula #1.bmp"));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "CANNOT OPEN IMAGE", "ERROR", JOptionPane.PLAIN_MESSAGE);
            }
        }

        public void paintComponent(Graphics g) {
            g.drawImage(image, 0, 0, null);
        }

        public void ChangeFormulaToFst() {
            try {
                image = ImageIO.read(new File("Formula #1.bmp"));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "CANNOT OPEN IMAGE Formula #1.bmp", "ERROR", JOptionPane.PLAIN_MESSAGE);
            }

        }

        public void ChangeFormulaToSnd() {
            try {
                image = ImageIO.read(new File("Formula #2.bmp"));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "CANNOT OPEN IMAGE Formula #2.bmp", "ERROR", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    public double Fun_1(double x, double y, double z) {
        return Math.sin(Math.log(y) + Math.sin(y * y * 3.14159)) * Math.pow(x * x + Math.sin(z) + Math.pow(2.718, Math.cos(z)), 1 / 4);
    }

    public double Fun_2(double x, double y, double z) {
        return Math.pow(Math.cos(Math.pow(2.718, x)) + 2 * Math.log(1 + y) + Math.sqrt(Math.pow(2.718, Math.cos(x)) + Math.pow(Math.sin(x * 3.14159), 2)) + Math.sqrt(1 / x) + Math.cos(y * y), Math.sin(z));
    }
}
