/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package formtransaction;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 *
 * @author Adji
 */
public class FormTransaction {

    private JComboBox inProduct;
    private JTextField inPrice, inQuantity;
    private JButton addToCart, delete;
    private JTable tableData;
    private String productName[] = {"Vit", "Aqua", "Prima"};
    private int productPrice[] = {12000, 14000, 13000};
    private DefaultTableModel model;
    
    public static void main(String[] args) {
        FormTransaction ft = new FormTransaction();
        ft.mainFrame();
    }
    
    private void mainFrame(){
        // Create JFrame
        JFrame main = new JFrame();
        main.setLayout(new GridLayout(2,1, 10, 10));
        main.setLocationRelativeTo(null);
        main.setResizable(false);
        main.setSize(500,350);
       
        // Call formPanel
        JPanel formPnl = new JPanel();
        formPanel(formPnl);
        
        // Call tablePanel
        JPanel tablePnl = new JPanel();
        tablePanel(tablePnl);
        
        main.add(formPnl);
        main.add(tablePnl);
        
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setVisible(true);
    }
    
    // Title Panel 
    private void titlePanel(JPanel panel){
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        JLabel title = new JLabel("Form Transaction");
        panel.add(title);
    }
    
    // Form Panel
    private void formPanel(JPanel panel){
        panel.setLayout(new BorderLayout());
        
        // Call title panel
        JPanel title = new JPanel();
        titlePanel(title);
        
        // Call input panel
        JPanel input = new JPanel();
        inputPanel(input);
        
        // Call button group panel
        JPanel button = new JPanel();
        buttonPanel(button);
        
        panel.add(title, BorderLayout.NORTH);
        panel.add(input, BorderLayout.CENTER);
        panel.add(button, BorderLayout.SOUTH);  
    }
    
    // Form Input
    private void inputPanel(JPanel panel){
        panel.setLayout(new GridLayout(3,2, 5, 5));
        panel.setSize(480,200);
        
        // Create label
        JLabel productNameLb = new JLabel("Product name");
        JLabel priceLb = new JLabel("Price");
        JLabel quantityLb = new JLabel("Quantity");
        
        // Create input form
        inProduct = new JComboBox( productName);
        inPrice = new JTextField();
        inQuantity = new JTextField();
        
        panel.add(productNameLb);
        panel.add(inProduct);
        
        panel.add(priceLb);
        panel.add(inPrice);
        
        panel.add(quantityLb);
        panel.add(inQuantity);
        
        inPrice.setEditable(false);
        inPrice.setText(getPrice());
        
        // Product onChange Listener
        inProduct.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                inPrice.setText(getPrice());
            }
        });
    }
    
    // Form Button
    private void buttonPanel(JPanel panel){
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        addToCart = new JButton("Add to Cart");
        delete = new JButton("Delete");
        
        panel.add(addToCart);
        panel.add(delete);
        
        // Add to Cart Button Listener
        addToCart.addActionListener((ActionEvent e) -> {
            addSelectedData();
        });
        
        // Delete Button Listener
        delete.addActionListener((ActionEvent e) -> {
            deleteSelectedData();
        });
    }
    
    // Table Panel
    private void tablePanel(JPanel panel){
        JScrollPane table = new JScrollPane();

        table = tableModel();
        
        panel.add(table, BorderLayout.NORTH);
    }
    
    // JScrollPane of Table
    private JScrollPane tableModel(){
        // Create table model
        model = new DefaultTableModel();
        model.addColumn("Product name");
        model.addColumn("Quantity");
        model.addColumn("Price");
        model.addColumn("Subtotal");
        
        tableData = new JTable(model );
        JScrollPane  jsp= new JScrollPane(tableData);
        
        return jsp;
    }
    
    // Add data to table
    private void addSelectedData(){
        String qty = inQuantity.getText().trim();
        
        // Check if qty not empty?, is numeric?, more than 0? (VALIDATION) 
        if(!qty.isEmpty() && qty.chars().allMatch(Character::isDigit) && Integer.parseInt(qty)>0) {
            
            // Get all input data
            String addProduct = String.valueOf(inProduct.getSelectedItem());
            String addQuantity = inQuantity.getText();
            String addPrice = getPrice();
            String addSubtotal = String.valueOf(Integer.parseInt(addPrice) * Integer.parseInt(addQuantity));
            
            // Check is product has been inputed?
            String insertedRow = getInsertedIndex(addProduct);
            
            if(insertedRow == null){
                // If the product has not been input
                model.addRow(new Object[]{addProduct, addQuantity, addPrice, addSubtotal});
                JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan", "Success!", JOptionPane.PLAIN_MESSAGE);
            }else{
                // If the product has been input (Update existing data)
                
                int curRow = Integer.parseInt(insertedRow);
                int exstQty = Integer.parseInt(tableData.getModel().getValueAt(curRow, 1).toString());
                int totalQty = exstQty + Integer.parseInt(addQuantity);
                
                // Sum total price after update data
                String totalPrice = String.valueOf(Integer.parseInt(addPrice)*totalQty);
                
                // Update data on table
                tableData.setValueAt(String.valueOf(totalQty), curRow, 1);
                tableData.setValueAt(addPrice, curRow, 2);
                tableData.setValueAt(totalPrice, curRow, 3);
                
                JOptionPane.showMessageDialog(null, "Data berhasil diupdate", "Success!", JOptionPane.PLAIN_MESSAGE);
            }

        }else{
            // If data not valid show error message
            JOptionPane.showMessageDialog(null, "Quantity tidak valid", "Stop!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Delete selected data
    private void deleteSelectedData(){
        // Check Selected Data (VALIDATION)
        if(!tableData.getSelectionModel().isSelectionEmpty()){
            // If there is data selected
            Integer row = tableData.getSelectedRow();
            model.removeRow(row);
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus", "Success!", JOptionPane.PLAIN_MESSAGE);
        }else{
            // If data has not selected
            JOptionPane.showMessageDialog(null, "Silahkan pilih data yang akan dihapus", "Stop!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Check existing data
    private String getInsertedIndex(String pName){
        for (int row = 0; row < tableData.getRowCount(); row++) {
            String rowData = String.valueOf(tableData.getValueAt(row, 0));
            
            // If there is the same data
            if (rowData.equals(pName)) {
                // Return existing index
                return String.valueOf(row);
            }
        }
        
        // If there is no the same data, return null
        return null;
    }

    // Get Price of selected product
    private String getPrice(){
        String price = String.valueOf(productPrice[inProduct.getSelectedIndex()]);
        
        return price;
    }
}

