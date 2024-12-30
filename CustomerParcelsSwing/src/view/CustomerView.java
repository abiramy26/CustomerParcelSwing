package view;

import controller.CustomerController;
import model.Customer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class CustomerView {
    private JFrame frame;
    private JTable table;
    private CustomerController controller;

    public CustomerView(CustomerController controller) {
        this.controller = controller;
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Customer Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Create table model.
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Name");
        tableModel.addColumn("Parcel ID");

        // Create table.
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Add Load Button.
        JButton loadButton = new JButton("Load Customers");
        loadButton.addActionListener(e -> loadCustomerData());
        frame.add(loadButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void loadCustomerData() {
        try {
            controller.loadCustomersFromCsv("C:\\Users\\User\\Downloads\\java course work\\java course work\\Custs.csv"); // Path to your CSV file.
            List<Customer> customers = controller.getCustomers();

            // Populate table with customer data.
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0); // Clear existing rows.
            for (Customer customer : customers) {
                model.addRow(new Object[]{customer.getName(), customer.getParcelId()});
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error loading customer data: " + e.getMessage());
        }
    }
}
