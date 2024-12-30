package controller;

import model.Customer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class CustomerController {
    private List<Customer> customers = new ArrayList<>();

    public void loadCustomersFromExcel(String filePath) throws IOException {
        FileInputStream file = new FileInputStream(new File(filePath));
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue; // Skip header row.

            String name = row.getCell(0).getStringCellValue();
            String parcelId = row.getCell(1).getStringCellValue();
            customers.add(new Customer(name, parcelId));
        }

        workbook.close();
        file.close();
    }

    public void loadCustomersFromCsv(String filePath) throws IOException {
        List<Customer> customers = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(","); // Assuming comma-separated values.
                if (values.length >= 2) {
                    String name = values[0].trim();
                    String parcelId = values[1].trim();
                    customers.add(new Customer(name, parcelId));
                }
            }
        }

        this.customers = customers;
    }
    public List<Customer> getCustomers() {
        return customers;
    }
}
