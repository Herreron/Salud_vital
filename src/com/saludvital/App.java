package com.saludvital;

import com.formdev.flatlaf.FlatLightLaf;
import com.saludvital.view.MainFrame;
import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        
        // libreria FlatLaf para el frontend
        FlatLightLaf.setup();

        // abrimos un nuevo hilo para ejecutar la interfaz gráfica
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame mainFrame = new MainFrame();
                mainFrame.run();
            }
        });
    }
}