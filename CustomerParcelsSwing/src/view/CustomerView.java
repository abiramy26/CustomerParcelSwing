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
        frame.setSize(800, 500);
        frame.setLayout(new BorderLayout());

        // Menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem loadMenuItem = new JMenuItem("Load Customers");
        loadMenuItem.addActionListener(e -> loadCustomerData());
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(e -> System.exit(0));
        fileMenu.add(loadMenuItem);
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);

        // Create table model
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Name");
        tableModel.addColumn("Parcel ID");

        // Create table
        table = new JTable(tableModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Bottom panel with buttons
        JPanel buttonPanel = new JPanel();
        JButton loadButton = new JButton("Load Customers");
        loadButton.addActionListener(e -> loadCustomerData());
        JButton addButton = new JButton("Add Customer");
        addButton.addActionListener(e -> addCustomer());
        JButton removeButton = new JButton("Remove Customer");
        removeButton.addActionListener(e -> removeCustomer());
        buttonPanel.add(loadButton);
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void loadCustomerData() {
        try {
            controller.loadCustomersFromCsv("C:\\Users\\User\\Downloads\\java course work\\java course work\\Custs.csv"); // Path to your CSV file
            updateTable();
            JOptionPane.showMessageDialog(frame, "Customers loaded successfully!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error loading customer data: " + e.getMessage());
        }
    }

    private void updateTable() {
        List<Customer> customers = controller.getCustomers();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear existing rows
        for (Customer customer : customers) {
            model.addRow(new Object[]{customer.getName(), customer.getParcelId()});
        }
    }

    private void addCustomer() {
        JTextField nameField = new JTextField();
        JTextField parcelIdField = new JTextField();
        Object[] message = {
                "Name:", nameField,
                "Parcel ID:", parcelIdField
        };

        int option = JOptionPane.showConfirmDialog(frame, message, "Add New Customer", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String name = nameField.getText().trim();
            String parcelId = parcelIdField.getText().trim();
            if (!name.isEmpty() && !parcelId.isEmpty()) {
                Customer newCustomer = new Customer(name, parcelId);
                controller.addCustomer(newCustomer);
                updateTable();
                JOptionPane.showMessageDialog(frame, "Customer added successfully!");
            } else {
                JOptionPane.showMessageDialog(frame, "Please fill in all fields.");
            }
        }
    }

    private void removeCustomer() {
        if (table.getRowCount() > 0) {
            Customer removedCustomer = controller.removeCustomer();
            if (removedCustomer != null) {
                updateTable();
                JOptionPane.showMessageDialog(frame, "Removed customer: " + removedCustomer.getName());
            } else {
                JOptionPane.showMessageDialog(frame, "No customers to remove!");
            }
        } else {
            JOptionPane.showMessageDialog(frame, "No customers in the queue.");
        }
    }
}
