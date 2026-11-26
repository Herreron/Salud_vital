package com.saludvital.view.panels;

import com.formdev.flatlaf.FlatClientProperties;
import com.saludvital.util.ResourceManager;
import com.saludvital.view.components.RoundedPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LoginPanel extends JPanel {

    private static final Color DARK_BG = new Color(5, 9, 31);
    private static final Color PRIMARY_TEAL = new Color(14, 143, 163);
    private static final Color TEXT_DARK = new Color(17, 24, 39);
    private static final Color TEXT_MUTED = new Color(107, 114, 128);
    private static final Color ERROR_RED = new Color(220, 53, 69);

    private static final int FIELD_WIDTH = 380;
    private static final int FIELD_HEIGHT = 46;

    private JTextField userField;
    private JPasswordField passwordField;
    private JButton togglePasswordButton;
    private JLabel errorLabel;
    private JButton loginButton;

    private ImageIcon eyeOpenIcon;
    private ImageIcon eyeClosedIcon;
    private char defaultEchoChar;
    private boolean passwordVisible;

    public LoginPanel() {
        this.passwordVisible = false;
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;

        // Panel izquierdo (42% del ancho)
        JPanel brandPanel = createBrandPanel();
        brandPanel.setPreferredSize(new Dimension(0, 0));
        gbc.gridx = 0;
        gbc.weightx = 0.42;
        add(brandPanel, gbc);

        // Panel derecho (58% del ancho)
        JPanel formContainer = createFormContainer();
        formContainer.setPreferredSize(new Dimension(0, 0));
        gbc.gridx = 1;
        gbc.weightx = 0.58;
        add(formContainer, gbc);
    }

    private JPanel createBrandPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(DARK_BG);

        JPanel contentStack = new JPanel();
        contentStack.setLayout(new BoxLayout(contentStack, BoxLayout.Y_AXIS));
        contentStack.setOpaque(false);

        // Contenedor blanco redondeado para el logotipo
        RoundedPanel logoCard = new RoundedPanel(36);
        logoCard.setBackground(Color.WHITE);
        logoCard.setLayout(new GridBagLayout());
        logoCard.setPreferredSize(new Dimension(250, 250));
        logoCard.setMaximumSize(new Dimension(250, 250));
        logoCard.setAlignmentX(Component.CENTER_ALIGNMENT);

        ImageIcon logoIcon = ResourceManager.loadIcon("logo.png", 190, -1);
        JLabel logoLabel = new JLabel(logoIcon);
        logoCard.add(logoLabel);

        // Subtitulo debajo del logo
        JLabel taglineLabel = new JLabel("Sistema Integral de Gestión Clínica");
        taglineLabel.setFont(ResourceManager.getRegularFont(21f));
        taglineLabel.setForeground(new Color(226, 232, 240));
        taglineLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        contentStack.add(logoCard);
        contentStack.add(Box.createVerticalStrut(24));
        contentStack.add(taglineLabel);

        panel.add(contentStack);
        return panel;
    }

    private JPanel createFormContainer() {
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(Color.WHITE);

        // Formulario centrado vertical y horizontalmente
        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setBackground(Color.WHITE);
        centerWrapper.add(createFormColumn());
        rightPanel.add(centerWrapper, BorderLayout.CENTER);

        // Pie de pagina con la version
        JLabel versionLabel = new JLabel("Salud Vital 1.0", SwingConstants.CENTER);
        versionLabel.setFont(ResourceManager.getRegularFont(15f));
        versionLabel.setForeground(TEXT_MUTED);
        versionLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 24, 0));
        rightPanel.add(versionLabel, BorderLayout.SOUTH);

        return rightPanel;
    }

    private JPanel createFormColumn() {
        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBackground(Color.WHITE);

        // Encabezado
        JLabel titleLabel = new JLabel("Inicia sesión");
        titleLabel.setFont(ResourceManager.getBoldFont(44f));
        titleLabel.setForeground(TEXT_DARK);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitleLabel = new JLabel("Ingresa tus credenciales para continuar");
        subtitleLabel.setFont(ResourceManager.getRegularFont(19f));
        subtitleLabel.setForeground(new Color(55, 65, 81));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Campo de usuario
        userField = new JTextField();
        configureTextField(userField, "Usuario o correo", "usuario.png");

        // Campo de contrasena con boton de visibilidad
        passwordField = new JPasswordField();
        defaultEchoChar = passwordField.getEchoChar();
        configureTextField(passwordField, "Contraseña", "candado.png");
        configurePasswordToggle();

        // Enlace de ayuda
        JLabel helpLabel = createHelpLabel();

        // Espacio reservado para errores en linea sin mover el boton
        errorLabel = new JLabel(" ");
        errorLabel.setFont(ResourceManager.getRegularFont(15f));
        errorLabel.setForeground(ERROR_RED);
        errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Boton principal
        loginButton = new JButton("Ingresar");
        loginButton.setFont(ResourceManager.getBoldFont(19f));
        loginButton.setForeground(Color.WHITE);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setPreferredSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));
        loginButton.setMaximumSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));
        loginButton.putClientProperty(FlatClientProperties.STYLE,
                "arc: 999;"
                + "background: #0E8FA3;"
                + "hoverBackground: #0A7282;"
                + "pressedBackground: #085B68;"
                + "borderWidth: 0;"
                + "focusWidth: 0;");

        // Al presionar Enter en usuario pasa al campo de contrasena
        userField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                passwordField.requestFocusInWindow();
            }
        });

        // Al presionar Enter en contrasena ejecuta el boton de ingresar
        passwordField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginButton.doClick();
            }
        });

        form.add(titleLabel);
        form.add(Box.createVerticalStrut(6));
        form.add(subtitleLabel);
        form.add(Box.createVerticalStrut(34));
        form.add(userField);
        form.add(Box.createVerticalStrut(16));
        form.add(passwordField);
        form.add(Box.createVerticalStrut(12));
        form.add(helpLabel);
        form.add(Box.createVerticalStrut(16));
        form.add(errorLabel);
        form.add(Box.createVerticalStrut(8));
        form.add(loginButton);

        return form;
    }

    private void configureTextField(JTextField field, String placeholder, String iconName) {
        field.setFont(ResourceManager.getRegularFont(17f));
        field.setForeground(TEXT_DARK);
        field.setAlignmentX(Component.CENTER_ALIGNMENT);
        field.setPreferredSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));
        field.setMaximumSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));

        field.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, placeholder);
        field.putClientProperty(FlatClientProperties.STYLE,
                "arc: 999;"
                + "background: #FFFFFF;"
                + "borderColor: #0E8FA3;"
                + "focusedBorderColor: #0A7282;"
                + "borderWidth: 2;"
                + "focusWidth: 1;"
                + "innerFocusWidth: 0;"
                + "margin: 0, 8, 0, 12;");

        ImageIcon icon = ResourceManager.loadIcon(iconName, 22, 22);
        if (icon != null) {
            JLabel iconLabel = new JLabel(icon);
            iconLabel.setBorder(BorderFactory.createEmptyBorder(0, 14, 0, 6));
            field.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_COMPONENT, iconLabel);
        }
    }

    private void configurePasswordToggle() {
        eyeOpenIcon = ResourceManager.loadIcon("ojo.png", 22, 22);
        eyeClosedIcon = ResourceManager.loadIcon("invisible.png", 22, 22);

        togglePasswordButton = new JButton(eyeOpenIcon);
        togglePasswordButton.setContentAreaFilled(false);
        togglePasswordButton.setBorderPainted(false);
        togglePasswordButton.setFocusPainted(false);
        togglePasswordButton.setFocusable(false);
        togglePasswordButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        togglePasswordButton.setBorder(BorderFactory.createEmptyBorder(0, 6, 0, 14));
        togglePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                togglePasswordVisibility();
            }
        });

        passwordField.putClientProperty(FlatClientProperties.TEXT_FIELD_TRAILING_COMPONENT, togglePasswordButton);
    }

    private void togglePasswordVisibility() {
        if (passwordVisible) {
            passwordVisible = false;
            passwordField.setEchoChar(defaultEchoChar);
            togglePasswordButton.setIcon(eyeOpenIcon);
        } else {
            passwordVisible = true;
            passwordField.setEchoChar((char) 0);
            togglePasswordButton.setIcon(eyeClosedIcon);
        }
    }

    private JLabel createHelpLabel() {
        JLabel helpLabel = new JLabel("¿Tienes problemas para iniciar sesión?");
        helpLabel.setFont(ResourceManager.getRegularFont(14f));
        helpLabel.setForeground(TEXT_MUTED);
        helpLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        helpLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        helpLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(
                        LoginPanel.this,
                        "Dirígete a Recursos Humanos y se te asignará un técnico para tu consulta.",
                        "Soporte de acceso",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                helpLabel.setForeground(PRIMARY_TEAL);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                helpLabel.setForeground(TEXT_MUTED);
            }
        });

        return helpLabel;
    }

    // Metodos expuestos para el controlador MVC
    public String getUsername() {
        return userField.getText().trim();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public void addLoginListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }

    public void showError(String message) {
        errorLabel.setText(message);
    }

    public void clearError() {
        errorLabel.setText(" ");
    }

    public void clearFields() {
        userField.setText("");
        passwordField.setText("");
        clearError();
        if (passwordVisible) {
            togglePasswordVisibility();
        }
    }

    public void focusUsernameField() {
        userField.requestFocusInWindow();
    }
}