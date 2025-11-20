package studentForm;

import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class AdmissionForm extends Frame implements ActionListener {
	
	
	String url, user, pass;
    
    Label lblTitle, lblFirstN,lblMidleN, lblEmail, lblPhone, lblCourse, lblStatus;
    
    TextField txtName, txtEmail, txtPhone,textMname, txtCourse;
    
    Button btnSubmit, btnClear;

   

    public AdmissionForm(String url, String user, String pass) {
    	
    	this.url = url;
        this.user = user;
        this.pass = pass;
       
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
        
        lblMidleN = new Label("Middle Name:");
        lblMidleN.setBounds(50, 110, 80, 20);
        textMname = new TextField();
        textMname.setBounds(140, 110, 200, 20);
        
        
        

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

//    	for config file crediantial variable acccess like name url password 
    	Properties props = new Properties();
    	FileInputStream fis = null;
    	
    	try {
    		fis=new FileInputStream("config.properties");
    		props.load(fis);
    	}catch(IOException e) {
    		System.out.println(e.getMessage());
//    		e.printStackTrace();
    	}
    	
    	String url = props.getProperty("db.url");
        
        String user = props.getProperty("db. username");
        String pass = props.getProperty("db.password");
    	
//        create object
    	AdmissionForm a =new AdmissionForm(url,user,pass);
    }
}