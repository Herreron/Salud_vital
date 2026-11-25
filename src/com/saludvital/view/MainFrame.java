package com.saludvital.view;

import com.saludvital.view.panels.LoginPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainFrame extends JFrame {

    public static final String LOGIN_VIEW = "LOGIN";
    public static final String RECEPTION_VIEW = "RECEPTION";
    public static final String ADMIN_VIEW = "ADMIN";

    private CardLayout cardLayout;
    private JPanel container;
    private LoginPanel loginPanel;
    private JPanel topBar;
    private JPanel panelExit;
    private JLabel labelExit;

    public MainFrame() {
        initComponents();
    }

    private void initComponents() {
        cardLayout = new CardLayout();
        container = new JPanel(cardLayout);
        loginPanel = new LoginPanel();
        topBar = new JPanel();
        panelExit = new JPanel();
        labelExit = new JLabel();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setName("mainFrame");
        setUndecorated(true);

        // Barra superior: izquierda reservada para el mini logo, derecha para salir
        topBar.setBackground(Color.BLACK);
        topBar.setPreferredSize(new Dimension(800, 36));
        topBar.setLayout(new BorderLayout());

        JPanel rightControls = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 4));
        rightControls.setOpaque(false);

        panelExit.setBackground(Color.BLACK);
        panelExit.setPreferredSize(new Dimension(32, 28));
        panelExit.setLayout(new BorderLayout());

        labelExit.setFont(new Font("SansSerif", Font.BOLD, 16));
        labelExit.setForeground(Color.WHITE);
        labelExit.setText("X");
        labelExit.setHorizontalAlignment(JLabel.CENTER);
        labelExit.setVerticalAlignment(JLabel.CENTER);
        labelExit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        labelExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                handleExitClicked();
            }

            @Override
            public void mouseEntered(MouseEvent evt) {
                panelExit.setBackground(new Color(220, 53, 69));
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                panelExit.setBackground(Color.BLACK);
            }
        });

        panelExit.add(labelExit, BorderLayout.CENTER);
        rightControls.add(panelExit);
        topBar.add(rightControls, BorderLayout.EAST);

        // Contenedor central con las cartas (vistas)
        container.add(loginPanel, LOGIN_VIEW);

        getContentPane().add(topBar, BorderLayout.PAGE_START);
        getContentPane().add(container, BorderLayout.CENTER);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    // Registra dinamicamente paneles adicionales (recepcion, admin, etc.)
    public void addView(JPanel panel, String viewName) {
        container.add(panel, viewName);
    }

    // Cambia la pantalla visible dentro del CardLayout
    public void showView(String viewName) {
        cardLayout.show(container, viewName);
    }

    public LoginPanel getLoginPanel() {
        return loginPanel;
    }

    private void handleExitClicked() {
        System.exit(0);
    }

    public void run() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }
}