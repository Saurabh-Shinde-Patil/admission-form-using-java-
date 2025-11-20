package studentForm;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AdmissionForm extends Frame implements ActionListener {
    
    Label lblTitle, lblFirstN,lblMidleN, lblEmail, lblPhone, lblCourse, lblStatus;
    TextField txtName, txtEmail, txtPhone, txtCourse;
    Button btnSubmit, btnClear;

    
    String url = "jdbc:mysql://localhost:3306/student_details"; 
                  
    String user = "root"; 
    String pass = "207429"; 

    public AdmissionForm() {
       
        setTitle("Student Admission Form");
        setSize(600, 800);
        setLayout(null);
        setBackground(Color.LIGHT_GRAY);
        setVisible(true);

        
        lblTitle = new Label("Admission Form");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setBounds(120, 40, 200, 30);

        lblFirstN = new Label("First Name:");
        lblFirstN.setBounds(50, 100, 80, 20);
        txtName = new TextField();
        txtName.setBounds(140, 100, 200, 20);
        
        

        lblEmail = new Label("Email:");
        lblEmail.setBounds(50, 140, 80, 20);
        txtEmail = new TextField();
        txtEmail.setBounds(140, 140, 200, 20);

        lblPhone = new Label("Phone:");
        lblPhone.setBounds(50, 180, 80, 20);
        txtPhone = new TextField();
        txtPhone.setBounds(140, 180, 200, 20);

        lblCourse = new Label("Course:");
        lblCourse.setBounds(50, 220, 80, 20);
        txtCourse = new TextField();
        txtCourse.setBounds(140, 220, 200, 20);

        btnSubmit = new Button("Register");
        btnSubmit.setBounds(100, 280, 80, 30);
        btnSubmit.addActionListener(this); // Add event listener

        btnClear = new Button("Clear");
        btnClear.setBounds(200, 280, 80, 30);
        btnClear.addActionListener(this);

        lblStatus = new Label("");
        lblStatus.setBounds(50, 330, 300, 20);
        lblStatus.setAlignment(Label.CENTER);

        //Add Components to Frame
        add(lblTitle);
        add(lblFirstN); add(txtName);
        add(lblEmail); add(txtEmail);
        add(lblPhone); add(txtPhone);
        add(lblCourse); add(txtCourse);
        add(btnSubmit); add(btnClear);
        add(lblStatus);

        //Handle Window Closing
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        
    }

    // Handle Button Clicks
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSubmit) {
            registerStudent();
        } else if (e.getSource() == btnClear) {
            txtName.setText("");
            txtEmail.setText("");
            txtPhone.setText("");
            txtCourse.setText("");
            lblStatus.setText("");
        }
    }

    //JDBC Connection Logic
    public void registerStudent() {
        try {
            // Load MySQL Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create Connection
            Connection con = DriverManager.getConnection(url, user, pass);

            // Prepare Statement
            String query = "INSERT INTO admissions (first_name, email, phone, course) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);

            // Set Values
            pst.setString(1, txtName.getText());
            pst.setString(2, txtEmail.getText());
            pst.setString(3, txtPhone.getText());
            pst.setString(4, txtCourse.getText());

            // Execute Update
            int rowCount = pst.executeUpdate();

            if (rowCount > 0) {
                lblStatus.setText("Registration Successful!");
                lblStatus.setForeground(Color.BLUE);
            } else {
                lblStatus.setText("Registration Failed.");
                lblStatus.setForeground(Color.RED);
            }

            con.close();

        } catch (Exception e) {
            lblStatus.setText("Error: " + e.getMessage());
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
//        new AdmissionForm();
        
    	AdmissionForm a =new AdmissionForm();
    }
}