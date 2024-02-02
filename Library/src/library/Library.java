/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package library;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 *
 * @author Adji
 */
public class Library {
    
    // DB Variable
    public static Statement st;
    public static ResultSet rs;
    static Connection cn = KoneksiDatabase.OpenConnection();
    
    private JTable tableData;
    private DefaultTableModel model;
    private JButton quit, save;
    private JFrame main, detailFrame;
    private JTextField dTitle, dCategory, dQuantity, inNama, inPhone, inEmail;
    
    public static void main(String[] args) {
        Library lb = new Library();
        lb.mainFrame();
    }
    
    
    // Main Frame
    public void mainFrame(){
        // Create JFrame
        main = new JFrame();
        main.setLayout(new BorderLayout());
        main.setLocationRelativeTo(null);
        main.setResizable(false);
        main.setSize(700,500);
        
        // Call title panel
        JPanel titlePnl = new JPanel();
        titlePanel(titlePnl);
        
        // Call table panel
        JPanel tablePnl = new JPanel();
        tablePanel(tablePnl);
        
        // Call button panel
        JPanel buttonPnl = new JPanel();
        buttonPanel(buttonPnl);
        
        main.add(titlePnl, BorderLayout.NORTH);
        main.add(tablePnl, BorderLayout.CENTER);
        main.add(buttonPnl, BorderLayout.SOUTH);
        
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setVisible(true);
    }
    
    // Title Panel 
    private void titlePanel(JPanel panel){
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        JLabel title = new JLabel("Book List");
        title.setFont(new Font("Serif", Font.BOLD, 24));
        
        panel.add(title);
    }
    
    // Table Panel
    private void tablePanel(JPanel panel){
        JScrollPane table = new JScrollPane();

        // Create TableModel
        table = tableModel();
        
        // Select All data from table book
        selectAllData();
        
        panel.add(table);
    }
    
    // JScrollPane of Table
    private JScrollPane tableModel(){
        // Create table model
        model = new DefaultTableModel();
        model.addColumn("Book ID");
        model.addColumn("Title");
        model.addColumn("Category");
        model.addColumn("Quantity");
        
        // Create table
        tableData = new JTable(model );
        tableData.setDefaultEditor(Object.class, null);
        
        // Add mouse event clicked on table
        tableData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Call method table mouse clicked
                tableDataMouseClicked(evt);
            }
        });
        
        // Set table column size
        setColumnSize();
        
        JScrollPane  jsp= new JScrollPane(tableData);
        return jsp;
    }
    
    // Set table column size
    private void setColumnSize(){
        TableColumnModel columnModel = tableData.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(60);
        columnModel.getColumn(0).setMaxWidth(60);
        columnModel.getColumn(2).setPreferredWidth(100);
        columnModel.getColumn(2).setMaxWidth(100);
        columnModel.getColumn(3).setPreferredWidth(60);
        columnModel.getColumn(3).setMaxWidth(60);
    }
    
    // Select all data from table book
    private void selectAllData(){
        try {
            st = cn.createStatement();
            
            // Create query
            rs = st.executeQuery("SELECT * FROM book");
            
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            model.setRowCount(0);
            
            // Add all data to table
            while(rs.next()){
                // Save Row to object
                Object[] data = {
                    rs.getString("id"),
                    rs.getString("title"),
                    rs.getString("category"),
                    rs.getString("quantity")
                };
                model.addRow(data);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "An error occurred while retrieving data from the database", "Stop!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Button Panel
    private void buttonPanel(JPanel panel){
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        quit = new JButton("KELUAR");
        
        // Exit program
        quit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.exit(0);
            }
        });
        
        panel.add(quit);
    }
    
    // Table data clicked
    private void tableDataMouseClicked(java.awt.event.MouseEvent evt){
        // Call detail frame (book detail & form peminjaman)
        detailFrame();
    }
    
    // Frame after row clicked
    private void detailFrame(){
        // Create JFrame
        detailFrame = new JFrame();
        detailFrame.setLayout(null);
        detailFrame.setLocationRelativeTo(null);
        detailFrame.setResizable(false);
        detailFrame.setSize(500,410);
        
        // Call book detail panel
        JPanel bookDetailPnl = new JPanel();
        bookDetailPanel(bookDetailPnl);
        bookDetailPnl.setBounds(10, 10, 470, 160);
        
        // Call form panel
        JPanel formPnl = new JPanel();
        formPanel(formPnl);
        formPnl.setBounds(10, 170, 480, 190);
        
        detailFrame.add(bookDetailPnl);
        detailFrame.add(formPnl);
        
        detailFrame.setVisible(true);
    }
    
    // Book detail panel
    private void bookDetailPanel(JPanel panel){
        panel.setLayout(null);
        panel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        
        // Add label
        JLabel titleDetail = new JLabel("Book Detail");
        titleDetail.setFont(new Font("Serif", Font.BOLD, 18));
        titleDetail.setBounds(20, 10, 200, 30);
        
        JLabel lTitle = new JLabel("Title");
        lTitle.setBounds(20, 50, 100, 30);
        
        JLabel lCategory = new JLabel("Category");
        lCategory.setBounds(20, 80, 100, 30);
        
        JLabel lQuantity = new JLabel("Quantity");
        lQuantity.setBounds(20, 110, 100, 30);
        
        // Add data with JTextField
        dTitle = new JTextField();
        dTitle.setBounds(100, 50, 350, 26);
        dTitle.setEditable(false);
        
        dCategory = new JTextField();
        dCategory.setBounds(100, 80, 350, 26);
        dCategory.setEditable(false);
        
        dQuantity = new JTextField();
        dQuantity.setBounds(100, 110, 350, 26);
        dQuantity.setEditable(false);
        
        // Set JTextField value
        setDetailValue();
        
        panel.add(titleDetail);
        panel.add(lTitle);
        panel.add(lCategory);
        panel.add(lQuantity);
        panel.add(dTitle);
        panel.add(dCategory);
        panel.add(dQuantity);
    }
    
    // Set Detail value
    private void setDetailValue(){
        int row = tableData.getSelectedRow();
        
        // Set detail book based on table selected row
        dTitle.setText(tableData.getModel().getValueAt(row, 1).toString());
        dCategory.setText(tableData.getModel().getValueAt(row, 2).toString());
        dQuantity.setText(tableData.getModel().getValueAt(row, 3).toString());
    }
    
    // Form Panel
    private void formPanel(JPanel panel){
        panel.setLayout(null);
        
        // Add label
        JLabel titleForm = new JLabel("Form Peminjaman");
        titleForm.setFont(new Font("Serif", Font.BOLD, 22));
        titleForm.setBounds(20, 10, 200, 30);
        
        JLabel lNama = new JLabel("Nama");
        lNama.setBounds(20, 60, 100, 30);
        
        JLabel lNo = new JLabel("No. Handphone");
        lNo.setBounds(20, 90, 100, 30);
        
        JLabel lEmail = new JLabel("Email");
        lEmail.setBounds(20, 120, 100, 30);
        
        // Add input field
        inNama = new JTextField();
        inNama.setBounds(130, 60, 320, 26);
        
        inPhone = new JTextField();
        inPhone.setBounds(130, 90, 320, 26);
        
        inEmail = new JTextField();
        inEmail.setBounds(130, 120, 320, 26);
        
        // Add save button
        save = new JButton("SAVE");
        save.setBounds(350, 160, 100, 30);

        panel.add(titleForm);
        panel.add(lNama);
        panel.add(lNo);
        panel.add(lEmail);
        panel.add(inNama);
        panel.add(inPhone);
        panel.add(inEmail);
        panel.add(save);
        
        // Save mouseclicked listener
        save.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // call save data function
                saveData();
            }
        });
    }
    
    // Save data to database
    private void saveData(){
        String nama = inNama.getText();
        String phone = inPhone.getText();
        String email = inEmail.getText();
        
        // Validataion EMPTY
        if(!nama.trim().isEmpty() && !phone.trim().isEmpty() && !email.trim().isEmpty()){
            // Validation Phone Number is NUMERIC
            if(phone.chars().allMatch(Character::isDigit)){
                // Validation Email contains '@'
                if(email.contains("@")){
                    // Validation quantity
                    if(isQuantityAvailable()){
                        // Update data if input data valid
                        updateData();
                    }else{
                        JOptionPane.showMessageDialog(null, "Mohon maaf, buku tidak tersedia", "Stop!", JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Alamat Email tidak valid", "Stop!", JOptionPane.ERROR_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Nomor Handphone harus berupa angka", "Stop!", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Silahkan isi data dengan benar", "Stop!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Check quantity
    private boolean isQuantityAvailable(){
        int row = tableData.getSelectedRow();
        int qty = Integer.parseInt(tableData.getModel().getValueAt(row, 3).toString());
        
        if(qty>0){
            return true;
        }else{
            return false;
        }
    }
    
    // Update data after validation
    private void updateData(){
        int row = tableData.getSelectedRow();
        String bookId = tableData.getModel().getValueAt(row, 0).toString();
        String qty = tableData.getModel().getValueAt(row, 3).toString();
        int qtyAfter = Integer.parseInt(qty) - 1;
        
        try {
            st = cn.createStatement();
            
            // Create Query Update
            String sql = "UPDATE book SET quantity ='"+qtyAfter+"' WHERE id='"+bookId+"'";
            st.executeUpdate(sql);
            
            JOptionPane.showMessageDialog(null,"Data Berhasil disimpan");
            detailFrame.setVisible(false);
            
            // Refresh data on table
            selectAllData();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat meng-update data", "Stop!", JOptionPane.ERROR_MESSAGE);
        }
    }
}
