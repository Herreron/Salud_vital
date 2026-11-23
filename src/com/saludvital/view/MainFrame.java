package com.saludvital.view;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
    
    public MainFrame() {
        setTitle("Salud Vital");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public void run() {
        setVisible(true);
    }
}
