package view;

import controller.MainController;

import javax.swing.*;
import java.awt.*;

public class MainView {
    private JFrame frame;

    public MainView(MainController mainController) {
        initialize(mainController);
    }

    private void initialize(MainController mainController) {
        frame = new JFrame("Main Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // Add a welcome label
        JLabel welcomeLabel = new JLabel("Welcome to the Parcel Management System", SwingConstants.CENTER);
        frame.add(welcomeLabel, BorderLayout.NORTH);

        // Add navigation buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10));

        JButton manageCustomersButton = new JButton("Manage Customers");
        manageCustomersButton.addActionListener(e -> {
            mainController.startCustomerManagement(); // Start Customer Management
            frame.dispose();
        });

        JButton manageParcelsButton = new JButton("Manage Parcels");
        manageParcelsButton.addActionListener(e -> {
            mainController.startParcelManagement(); // Start Parcel Management
            frame.dispose();
        });

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));

        buttonPanel.add(manageCustomersButton);
        buttonPanel.add(manageParcelsButton);
        buttonPanel.add(exitButton);

        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
