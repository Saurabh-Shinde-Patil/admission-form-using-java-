

package studentForm;

import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Calendar;
import java.util.Properties;

public class AdmissionForm extends Frame implements ActionListener {

    String url, user, pass;

    Label lblTitle, lblFirstN, lblMidleN, lbllastN, lblEmail, lbldob, lblage,
            lblPhone, lblCourse, lblgender, lbladdr, lblProLang, lblLangKnow, lblStatus;

    TextField txtFirstN, txtlastN, textMname, txtEmail, txtage, txtPhone, txtCourse;


    TextField txtLangKnown;

    TextArea txtaddr;

    Button btnSubmit, btnClear;

    Choice chDay, chMonth, chYear;

    CheckboxGroup genderGroup;
    Checkbox rbMale, rbFemale;

    Checkbox cbJava, cbPython, cbC, cbJS, cbPHP;

    /* ⭐⭐⭐ UPDATED: Multi-select dropdown using AWT List ⭐⭐⭐ */
    List listLanguagesKnown;


    public AdmissionForm(String url, String user, String pass) {

        this.url = url;
        this.user = user;
        this.pass = pass;

        setTitle("Student Admission Form");
        setSize(700, 900);
        setLayout(null);
        setBackground(new Color(255, 250, 205));
        setVisible(true);

        lblTitle = new Label("Admission Form");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setBounds(120, 40, 200, 30);
        add(lblTitle);

        lblFirstN = new Label("First Name:");
        lblFirstN.setBounds(50, 100, 100, 20);
        txtFirstN = new TextField();
        txtFirstN.setBounds(160, 100, 200, 20);
        add(lblFirstN);
        add(txtFirstN);

        lblMidleN = new Label("Middle Name:");
        lblMidleN.setBounds(50, 130, 100, 20);
        textMname = new TextField();
        textMname.setBounds(160, 130, 200, 20);
        add(lblMidleN);
        add(textMname);

        lbllastN = new Label("Last Name:");
        lbllastN.setBounds(50, 160, 100, 20);
        txtlastN = new TextField();
        txtlastN.setBounds(160, 160, 200, 20);
        add(lbllastN);
        add(txtlastN);

        lblEmail = new Label("Email:");
        lblEmail.setBounds(50, 190, 100, 20);
        txtEmail = new TextField();
        txtEmail.setBounds(160, 190, 200, 20);
        add(lblEmail);
        add(txtEmail);

        lblPhone = new Label("Phone:");
        lblPhone.setBounds(50, 220, 100, 20);
        txtPhone = new TextField();
        txtPhone.setBounds(160, 220, 200, 20);
        add(lblPhone);
        add(txtPhone);

        lblCourse = new Label("Branch:");
        lblCourse.setBounds(50, 250, 100, 20);
        txtCourse = new TextField();
        txtCourse.setBounds(160, 250, 200, 20);
        add(lblCourse);
        add(txtCourse);

        lbladdr = new Label("Address:");
        lbladdr.setBounds(50, 280, 100, 20);
        txtaddr = new TextArea();
        txtaddr.setBounds(160, 280, 300, 60);
        add(lbladdr);
        add(txtaddr);

//        / DOB 
        lbldob = new Label("Date of Birth:");
        lbldob.setBounds(50, 350, 100, 20);
        add(lbldob);

        chDay = new Choice();
        for (int i = 1; i <= 31; i++) chDay.add(String.valueOf(i));
        chDay.setBounds(160, 350, 60, 20);
        add(chDay);

        chMonth = new Choice();
        for (int i = 1; i <= 12; i++) chMonth.add(String.valueOf(i));
        chMonth.setBounds(230, 350, 60, 20);
        add(chMonth);

        chYear = new Choice();
        for (int i = 1980; i <= 2025; i++) chYear.add(String.valueOf(i));
        chYear.setBounds(300, 350, 80, 20);
        add(chYear);

//         Gender 
        lblgender = new Label("Gender:");
        lblgender.setBounds(50, 385, 100, 20);
        add(lblgender);

        genderGroup = new CheckboxGroup();

        rbMale = new Checkbox("Male", genderGroup, true);
        rbMale.setBounds(160, 385, 60, 20);
        add(rbMale);

        rbFemale = new Checkbox("Female", genderGroup, false);
        rbFemale.setBounds(230, 385, 80, 20);
        add(rbFemale);


//        Programming languages 
        lblProLang = new Label("Programming Languages:");
        lblProLang.setBounds(50, 420, 200, 20);
        add(lblProLang);

        cbJava = new Checkbox("Java");
        cbJava.setBounds(60, 450, 80, 20);
        add(cbJava);

        cbPython = new Checkbox("Python");
        cbPython.setBounds(150, 450, 80, 20);
        add(cbPython);

        cbC = new Checkbox("C");
        cbC.setBounds(240, 450, 60, 20);
        add(cbC);

        cbJS = new Checkbox("JavaScript");
        cbJS.setBounds(300, 450, 100, 20);
        add(cbJS);

        cbPHP = new Checkbox("PHP");
        cbPHP.setBounds(410, 450, 60, 20);
        add(cbPHP);


//        
        lblLangKnow = new Label("Languages Known:");
        lblLangKnow.setBounds(50, 490, 150, 20);
        add(lblLangKnow);

        listLanguagesKnown = new List(5, true);  // MULTI-SELECT LIST
        listLanguagesKnown.add("Marathi");
        listLanguagesKnown.add("Hindi");
        listLanguagesKnown.add("English");
        listLanguagesKnown.add("Tamil");
        listLanguagesKnown.add("Telugu");
        listLanguagesKnown.add("Gujarati");
        listLanguagesKnown.setBounds(200, 490, 150, 80);
        add(listLanguagesKnown);

//         TEXTFIELD TO DISPLAY SELECTED LANGUAGES
        txtLangKnown = new TextField();
        txtLangKnown.setBounds(160, 580, 300, 25);
        txtLangKnown.setEditable(false);
        add(txtLangKnown);

//        UPDATE TEXTFIELD WHEN USER SELECTS MULTIPLE LANGUAGES 
        listLanguagesKnown.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                updateSelectedLanguages();
            }
        });


        btnSubmit = new Button("Register");
        btnSubmit.setBounds(100, 630, 80, 30);
        btnSubmit.addActionListener(this);
        add(btnSubmit);

        btnClear = new Button("Clear");
        btnClear.setBounds(200, 630, 80, 30);
        btnClear.addActionListener(this);
        add(btnClear);

        lblStatus = new Label("");
        lblStatus.setBounds(100, 660, 400, 20);
        lblStatus.setAlignment(Label.CENTER);
        add(lblStatus);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }


//    UPDATE TEXTFIELD FOR MULTI-SELECT 
    public void updateSelectedLanguages() {
        String[] selected = listLanguagesKnown.getSelectedItems();

        String result = "";
        for (String s : selected)
            result += s + ", ";

        if (!result.isEmpty())
            result = result.substring(0, result.length() - 2);

        txtLangKnown.setText(result);
    }


    /* ⭐⭐⭐ JDBC LOGIC UPDATED FOR MULTIPLE LANGUAGES ⭐⭐⭐ */
    public void registerStudent() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(url, user, pass);

            String query =
                "INSERT INTO admissions (first_name, middle_name, last_name, email, phone, course, gender, address, prog_langs, language_known, dob) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pst = con.prepareStatement(query);

            pst.setString(1, txtFirstN.getText());
            pst.setString(2, textMname.getText());
            pst.setString(3, txtlastN.getText());
            pst.setString(4, txtEmail.getText());
            pst.setString(5, txtPhone.getText());
            pst.setString(6, txtCourse.getText());

            String gender = rbMale.getState() ? "Male" : rbFemale.getState() ? "Female" : "";
            pst.setString(7, gender);

            pst.setString(8, txtaddr.getText());

            String prog = "";
            if (cbJava.getState()) prog += "Java, ";
            if (cbPython.getState()) prog += "Python, ";
            if (cbC.getState()) prog += "C, ";
            if (cbJS.getState()) prog += "JavaScript, ";
            if (cbPHP.getState()) prog += "PHP, ";
            if (!prog.isEmpty()) prog = prog.substring(0, prog.length() - 2);
            pst.setString(9, prog);

//            STORE MULTI-SELECT LANGUAGES 
            pst.setString(10, txtLangKnown.getText());

            int d = Integer.parseInt(chDay.getSelectedItem());
            int m = Integer.parseInt(chMonth.getSelectedItem());
            int y = Integer.parseInt(chYear.getSelectedItem());

            java.sql.Date sqlDob = java.sql.Date.valueOf(y + "-" + m + "-" + d);
            pst.setDate(11, sqlDob);

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


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSubmit) {
            registerStudent();
        } else if (e.getSource() == btnClear) {
            clear();
        }
    }


    public void clear() {

        txtFirstN.setText("");
        textMname.setText("");
        txtlastN.setText("");
        txtEmail.setText("");
        txtPhone.setText("");
        txtCourse.setText("");
        txtaddr.setText("");

        chDay.select(0);
        chMonth.select(0);
        chYear.select(0);

        genderGroup.setSelectedCheckbox(null);

        cbJava.setState(false);
        cbPython.setState(false);
        cbC.setState(false);
        cbJS.setState(false);
        cbPHP.setState(false);

//       CLEAR ALL SELECTED LANGUAGES 
        listLanguagesKnown.deselect(listLanguagesKnown.getSelectedIndex());
        txtLangKnown.setText("");
    }


    public static void main(String[] args) {

        Properties props = new Properties();
        FileInputStream fis = null;

        try {
            fis = new FileInputStream("config.properties ");
            props.load(fis);
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.username");
        String pass = props.getProperty("db.password");

        AdmissionForm a = new AdmissionForm(url,user,pass);
    }
}









