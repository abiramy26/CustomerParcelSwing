package view;

import controller.ParcelController;
import model.Parcel;
import model.Worker;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ParcelView {
    private JFrame frame;
    private JTable table;
    private ParcelController controller;
    private Worker worker; 
    private String filePath = "C:\\Users\\User\\Downloads\\java course work\\java course work\\Parcels.csv";

    public ParcelView(ParcelController controller) {
        this.controller = controller;
        this.worker = new Worker();
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Parcel Management Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setLayout(new BorderLayout());

        // Menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem loadMenuItem = new JMenuItem("Load Parcels");
        loadMenuItem.addActionListener(e -> loadParcelData());
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(e -> System.exit(0));
        fileMenu.add(loadMenuItem);
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);

        // Table model
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Parcel ID");
        tableModel.addColumn("Weight (kg)");
        tableModel.addColumn("Height (cm)");
        tableModel.addColumn("Width (cm)");
        tableModel.addColumn("Length (cm)");
        tableModel.addColumn("Priority");
        tableModel.addColumn("Fee");
        tableModel.addColumn("Processed");

        // Table
        table = new JTable(tableModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Bottom panel with buttons
        JPanel buttonPanel = new JPanel();
        JButton loadButton = new JButton("Load Parcels");
        loadButton.addActionListener(e -> loadParcelData());
        JButton addButton = new JButton("Add Parcel");
        addButton.addActionListener(e -> addParcel());
        JButton removeButton = new JButton("Remove Parcel");
        removeButton.addActionListener(e -> removeParcel());
        JButton processButton = new JButton("Process Parcel");
        processButton.addActionListener(e -> processParcel()); // Added Process Parcel button
        buttonPanel.add(loadButton);
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(processButton); // Added Process Parcel button to panel
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void loadParcelData() {
        controller.loadParcelsFromCsv(); // Replace with your file path
        updateTable();
        JOptionPane.showMessageDialog(frame, "Parcels loaded successfully!");
    }

    private void updateTable() {
        List<Parcel> parcels = controller.getAllParcels();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear existing rows
        for (Parcel parcel : parcels) {
            model.addRow(new Object[] {
                parcel.getParcelId(),
                parcel.getWeight(),
                parcel.getHeight(),
                parcel.getWidth(),
                parcel.getLength(),
                parcel.getPriority(),
                parcel.getFee(), // Add Fee
                parcel.isProcessed() ? "Yes" : "No" // Add Processed status
            });
        }
    }

    private void addParcel() {
        JTextField parcelIdField = new JTextField();
        JTextField weightField = new JTextField();
        JTextField heightField = new JTextField();
        JTextField widthField = new JTextField();
        JTextField lengthField = new JTextField();
        JTextField priorityField = new JTextField();
        Object[] message = {
            "Parcel ID:", parcelIdField,
            "Weight (kg):", weightField,
            "Height (cm):", heightField,
            "Width (cm):", widthField,
            "Length (cm):", lengthField,
            "Priority:", priorityField
        };

        int option = JOptionPane.showConfirmDialog(frame, message, "Add New Parcel", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                String parcelId = parcelIdField.getText().trim();
                double weight = Double.parseDouble(weightField.getText().trim());
                int height = Integer.parseInt(heightField.getText().trim());
                int width = Integer.parseInt(widthField.getText().trim());
                int length = Integer.parseInt(lengthField.getText().trim());
                int priority = Integer.parseInt(priorityField.getText().trim());

                Parcel newParcel = new Parcel(parcelId, weight, height, width, length, priority);
                controller.addParcel(newParcel, filePath);
                updateTable();
                JOptionPane.showMessageDialog(frame, "Parcel added successfully!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input! Please enter valid numbers.");
            }
        }
    }

    private void removeParcel() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            String parcelId = (String) table.getValueAt(selectedRow, 0);
            controller.removeParcelById(parcelId, filePath);
            updateTable();
            JOptionPane.showMessageDialog(frame, "Parcel removed successfully: " + parcelId);
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a parcel to remove.");
        }
    }

    private void processParcel() {
        int selectedRow = table.getSelectedRow();  // Get the selected row in the table
        if (selectedRow >= 0) {
            String parcelId = (String) table.getValueAt(selectedRow, 0);  // Get the parcel ID
            Parcel parcel = controller.getParcelById(parcelId);  // Retrieve the parcel by ID

            if (parcel != null) {
                // Call the Worker method to process the parcel
                String resultMessage = worker.processCustomer(parcel, (DefaultTableModel) table.getModel(), selectedRow, filePath);

                // Check the result message returned by the Worker
                if (resultMessage.equals("Parcel processed successfully.")) {
                    // If successful, show a success message
                    JOptionPane.showMessageDialog(frame, "Parcel processed successfully: " + parcelId);
                } else {
                    // If the parcel is already processed, show the message
                    JOptionPane.showMessageDialog(frame, resultMessage);
                }

            } else {
                // If the parcel is not found, show an error message
                JOptionPane.showMessageDialog(frame, "Parcel not found!");
            }
        } else {
            // If no parcel is selected, prompt the user to select one
            JOptionPane.showMessageDialog(frame, "Please select a parcel to process.");
        }
    }


    
}
