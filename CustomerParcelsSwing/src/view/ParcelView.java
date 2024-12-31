package view;

import java.awt.BorderLayout;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.ParcelController;
import model.Parcel;

public class ParcelView {
	private JFrame jframe;
	private JTable jtable;
	private ParcelController controller;
	
	public ParcelView(ParcelController controller) {
		this.controller = controller;
		initialize();
	}
	
	private void initialize() {
		jframe = new JFrame("Parcel Viewer");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setSize(600,400);
		
		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel.addColumn("Parcel Id");
		tableModel.addColumn("weight");
		tableModel.addColumn("height");
		tableModel.addColumn("width");
		tableModel.addColumn("length");
		tableModel.addColumn("priority");
		
		jtable = new JTable(tableModel);
		JScrollPane jScrollPane = new JScrollPane(jtable);
		jframe.add(jScrollPane, BorderLayout.CENTER);
		
		JButton loadButton = new JButton("Load Parcels");
		loadButton.addActionListener(e -> loadParcelData());
		jframe.add(loadButton, BorderLayout.SOUTH);
		
		jframe.setVisible(true);
	}
	
	private void loadParcelData() {
		try {
			controller.loadParcelsFromCsv("C:\\\\Users\\\\User\\\\Downloads\\\\java course work\\\\java course work\\\\Parcels.csv");
			List<Parcel> parcels = controller.getParcels();
			
			DefaultTableModel model = (DefaultTableModel) jtable.getModel();
			model.setRowCount(0);
			for(Parcel parcel: parcels) {
				model.addRow(new Object[] {parcel.getParcelId(),parcel.getWeight(),parcel.getHeight(),parcel.getWidth(),parcel.getLength(),parcel.getPriority()});
			}
			
		}catch (IOException e) {
			JOptionPane.showMessageDialog(jframe, "Error loading data " + e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
