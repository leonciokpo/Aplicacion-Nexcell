package vista;

import javax.swing.*;
import java.awt.*;

public class AdminUI extends JFrame {

    public AdminUI() {
        setTitle("Panel de Control - Administrador");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar en pantalla

        setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Bienvenido, Administrador", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 22));
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(welcomeLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        JButton manageUsersBtn = new JButton("Gestionar Usuarios");
        JButton viewReportsBtn = new JButton("Ver Reportes");
        JButton logoutBtn = new JButton("Cerrar Sesión");

        optionsPanel.add(manageUsersBtn);
        optionsPanel.add(viewReportsBtn);
        optionsPanel.add(logoutBtn);

        add(optionsPanel, BorderLayout.CENTER);

        logoutBtn.addActionListener(e -> {
            this.dispose();

            LoginUI loginWindow = new LoginUI();
            loginWindow.setVisible(true);
        });
    }
}
