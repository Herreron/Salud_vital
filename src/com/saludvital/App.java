package com.saludvital;

import com.formdev.flatlaf.FlatLightLaf;
import com.saludvital.view.MainFrame;
import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        FlatLightLaf.setup();

        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.run();
        });
    }
}

