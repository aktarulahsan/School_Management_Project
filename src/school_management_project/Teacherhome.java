package school_management_project;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.text.DateFormat;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author aktar
 */
public class Teacherhome extends javax.swing.JFrame {

    /**
     * Creates new form Studenthome
     */
    String bangla, english, math, islam, computer, science, tmark, obtain, percen, gpa;
    String studentname, roll, faname, sclass, gu, regno;
    int b, engl, mat, isl, compu, sci;
    Double tmt, obt, pe;

    String filename = null;
    byte[] person_image = null;

    String name, reg, tuser, pass, sclas, gend, phone, father, mother, mail, ages, rellgion, address;

    Connection con = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    DefaultTableModel model;
    DefaultTableModel allstumodel;
    DefaultTableModel noticebord;
    DefaultTableModel allstuinfo;
    DefaultTableModel searchallstudent;

    public Teacherhome(String name) {
        initComponents();
        showdata(name);
        showteachdata(name);
        getteacherdata(name);
        chooseexam();

        choosesession();

        chooseclass();
        showfild();

        java.util.Date d = new java.util.Date();
        DateFormat df = DateFormat.getDateTimeInstance();
        date1.setText(df.format(d));
        model = new DefaultTableModel() {
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    case 3:
                        return Boolean.class;
                    default:
                        return String.class;
                }
            }
        };

        tts.setModel(model);
        model.addColumn("Roll");
        model.addColumn("REG");
        model.addColumn("Name");
        model.addColumn("Attandance");

        allstumodel = new DefaultTableModel();
        jTable5.setModel(allstumodel);
        allstumodel.addColumn("R_no");
        allstumodel.addColumn("S_id");
        allstumodel.addColumn("Session");
        allstumodel.addColumn("s_name");
        allstumodel.addColumn("f_name");
        allstumodel.addColumn("Class");

        allstuinfo = new DefaultTableModel();
        jTable4.setModel(allstuinfo);
        allstuinfo.addColumn("stu_id");
        allstuinfo.addColumn("session_year");

        allstuinfo.addColumn("class_name");
        allstuinfo.addColumn("roll_num");
        allstuinfo.addColumn("s_name");
        //allstuinfo.addColumn("s_gender");
        allstuinfo.addColumn("father_name");
        allstuinfo.addColumn("mother_name");
        allstuinfo.addColumn("phone_no");
        allstuinfo.addColumn("email_id");
        allstuinfo.addColumn("rellgion");
        allstuinfo.addColumn("address");

        noticebord = new DefaultTableModel();
        tt2.setModel(noticebord);
        noticebord.addColumn("Subject");
        noticebord.addColumn("Notice");

        shownoticedata();

        setattandance();

    }

    private void showdata(String name) {

        try {
            con = DBConnection.getDBConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("select *from teacherid where user_id='" + name + "'");
            System.out.println(" login");

            while (rs.next()) {

                jLabel2.setText(rs.getString(6));
                jLabel4.setText(rs.getString(4));
                jLabel6.setText(rs.getString(1));
                c4.setText(rs.getString(5));
                jLabel8.setText(rs.getString("join_date"));
                byte[] img = (userList().get(1).getPic());
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(pic.getWidth(), pic.getHeight(), Image.SCALE_DEFAULT));
                jLabel11.setIcon(imageIcon);

                tname.setText(rs.getString(4));
                cname.setText(rs.getString(5));
                jobid.setText(rs.getString(1));

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
                stmt.close();
                rs.close();
            } catch (Exception e) {
            }
        }
    }

    public ArrayList<Getpicture> userList() {

        ArrayList<Getpicture> userList = new ArrayList<>();

        try {
            Connection con = DBConnection.getDBConnection();
            String query1 = "select pic from teacherid ";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query1);
            Getpicture user;
            while (rs.next()) {

                //int stu_id, String session_year, String class_name, String roll_num, String s_name, String user_id, String password, String s_gender, String father_name, 
//            String mother_name, String phone_no, String email_id, String rellgion, String address,byte[] pic, String s_group,String birth_date, String age
//stu_id, session_year, class_name, roll_num, s_name, user_id, password, s_gender, father_name, mother_name, phone_no, email_id, rellgion, address, pic, s_group, birth_date, age   reg_date             
                user = new Getpicture(rs.getBytes("pic"));

                userList.add(user);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        return userList;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    public void getdata() {
        String name, jid, sb, user, pass, cl, gend, phone, father, mother, mail, ages, rellgion, address;
        name = n.getText();
        sb = sub.getText();
        user = u.getText();
        pass = p.getText();
        jid = re.getText();
        cl = c.getText();
        gend = ge.getSelectedItem().toString();
        phone = po.getText();
        mail = e.getText();
        father = fn.getText();
        mother = mn.getText();
        rellgion = rel.getText();
        ages = ag.getText();
        address = ad.getText();

    }

    public void chooseclass() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        sleclass.setSelectedIndex(0);
        jComboBox7.setSelectedIndex(0);
        jComboBox5.setSelectedIndex(0);
        //jComboBox5.setSelectedIndex(0);
        //seclasslim.setSelectedIndex(0);
        try {
            con = DBConnection.getDBConnection();
            //Create the Statement object
            stmt = con.createStatement();

            //Execute the query
            rs = stmt.executeQuery("select c_name from s_classes  ");
            //rs = stmt.executeQuery("select Name,Wardno from patient " );
            int count = 0;
            while (rs.next()) {
                sleclass.addItem(rs.getString(1));
                jComboBox7.addItem(rs.getString(1));
                jComboBox5.addItem(rs.getString(1));
                //seclass.addItem(rs.getString(1));
                //seclasslim.addItem(rs.getString(1));

                // rcmb.addItem(rs.getString(1));
                //pcmb.addItem(rs.getString(1));
                count++;
            }
            if (count == 0) {
                JOptionPane.showMessageDialog(rootPane, "There is no such record");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                //Close the connection and statement object
                con.close();
                stmt.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }

    }

    public void choosesession() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        jComboBox6.setSelectedIndex(0);
        jComboBox4.setSelectedIndex(0);
        //selsession.setSelectedIndex(0);
        //seseslimit.setSelectedIndex(0);
        try {
            con = DBConnection.getDBConnection();
            //Create the Statement object
            stmt = con.createStatement();

            //Execute the query
            rs = stmt.executeQuery("select ses_year from session  ");
            //rs = stmt.executeQuery("select Name,Wardno from patient " );
            int count = 0;
            while (rs.next()) {
                jComboBox6.addItem(rs.getString(1));
                jComboBox4.addItem(rs.getString(1));
                //selsession.addItem(rs.getString(1));
                //seseslimit.addItem(rs.getString(1));
                // rcmb.addItem(rs.getString(1));
                //pcmb.addItem(rs.getString(1));

                count++;
            }
            if (count == 0) {
                JOptionPane.showMessageDialog(rootPane, "There is no such record");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                //Close the connection and statement object
                con.close();
                stmt.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }

    }

    public void chooseexam() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        exname.setSelectedIndex(0);
        try {
            con = DBConnection.getDBConnection();
            //Create the Statement object
            stmt = con.createStatement();

            //Execute the query
            rs = stmt.executeQuery("select exam_name from s_exam  ");
            //rs = stmt.executeQuery("select Name,Wardno from patient " );
            int count = 0;
            while (rs.next()) {
                exname.addItem(rs.getString(1));
                //sexam1.addItem(rs.getString(1));
                //sexam2.addItem(rs.getString(1));
//                seclass.addItem(rs.getString(1));
//                seclasslim.addItem(rs.getString(1));
                // rcmb.addItem(rs.getString(1));
                //pcmb.addItem(rs.getString(1));

                count++;
            }
            if (count == 0) {
                JOptionPane.showMessageDialog(rootPane, "There is no such record");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                //Close the connection and statement object
                con.close();
                stmt.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }

    }

    private void setattandance() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String cl;
        cl = cname.getText();
        try {
            con = DBConnection.getDBConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("select * from student_info where class_name='" + cl + "'");

            while (rs.next()) {
                model.addRow(new Object[]{rs.getString("roll_num"), rs.getString("stu_id"), rs.getString("s_name"), false});
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
                stmt.close();
                rs.close();
            } catch (Exception e) {
            }
        }

    }

    public Teacherhome() {
        initComponents();

    }

    public void getteacherdata(String name) {
        String a;
        a = jLabel6.getText();

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ResultSet rsc = null;
        try {
            con = DBConnection.getDBConnection();
            stmt = con.createStatement();

            rs = stmt.executeQuery("select * from teacherid where user_id='" + name + "' ");
            while (rs.next()) {
                jLabel2.setText(rs.getString("user_id"));
                jLabel4.setText(rs.getString("t_name"));
                jLabel6.setText(rs.getString("t_jobid"));
                jLabel8.setText(rs.getString("join_date"));
                c4.setText(rs.getString("m_class"));

//                tname.setText(rs.getString(5));
//                cname.setText(rs.getString(6));
//                jobid.setText(rs.getString(2));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
                stmt.close();
            } catch (SQLException e) {
            }
        }

    }

    private void showteachdata(String name) {

        String a;
        a = uname.getText();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getDBConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("select * from teacherid where user_id='" + name + "' ");
            int count = 0;
            while (rs.next()) {
                n.setText(rs.getString("t_name"));
                u.setText(rs.getString("user_id"));
                p.setText(rs.getString("pass"));
                re.setText(rs.getString("t_jobid"));
                sub.setText(rs.getString("subject"));
                c.setText(rs.getString("m_class"));
//tid, jobid, user, pass, name, m_class, subject, gender, father, mother, phone, email, address, pic
                //gender.setSelected(m, rootPaneCheckingEnabled);
                ge.setSelectedItem(rs.getString("gender"));
                po.setText(rs.getString("phone"));
                e.setText(rs.getString("email"));
                fn.setText(rs.getString("father"));
                mn.setText(rs.getString("mother"));
                rel.setText(rs.getString("rellgion"));
                //ag.setText(rs.getString("age"));
                ad.setText(rs.getString("address"));
                byte[] img = (userList().get(1).getPic());
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(pic.getWidth(), pic.getHeight(), Image.SCALE_DEFAULT));
                pic.setIcon(imageIcon);

                count++;
            }
            if (count > 0) {

            } else {
                JOptionPane.showMessageDialog(rootPane, "you have no profile");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
                stmt.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel12 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jLabel50 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jPanel24 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        uname = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        c4 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        n = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        sub = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        u = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        p = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        re = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        ge = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        po = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        e = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        pic = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        ad = new javax.swing.JTextField();
        ag = new javax.swing.JTextField();
        rel = new javax.swing.JTextField();
        mn = new javax.swing.JTextField();
        fn = new javax.swing.JTextField();
        c = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel26 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        sn = new javax.swing.JTextField();
        sroll = new javax.swing.JTextField();
        sess = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        fn3 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        regids = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        scla = new javax.swing.JComboBox<>();
        jPanel27 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        tm = new javax.swing.JTextField();
        om = new javax.swing.JTextField();
        per = new javax.swing.JTextField();
        grade = new javax.swing.JTextField();
        jPanel28 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jComboBox6 = new javax.swing.JComboBox<>();
        jComboBox7 = new javax.swing.JComboBox<>();
        jPanel18 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        x1 = new javax.swing.JLabel();
        x3 = new javax.swing.JLabel();
        x5 = new javax.swing.JLabel();
        x2 = new javax.swing.JLabel();
        m1 = new javax.swing.JTextField();
        m3 = new javax.swing.JTextField();
        m5 = new javax.swing.JTextField();
        m2 = new javax.swing.JTextField();
        x4 = new javax.swing.JLabel();
        m4 = new javax.swing.JTextField();
        x6 = new javax.swing.JLabel();
        m6 = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        x7 = new javax.swing.JLabel();
        m7 = new javax.swing.JTextField();
        x8 = new javax.swing.JLabel();
        m8 = new javax.swing.JTextField();
        m10 = new javax.swing.JTextField();
        m9 = new javax.swing.JTextField();
        x9 = new javax.swing.JLabel();
        x10 = new javax.swing.JLabel();
        x11 = new javax.swing.JLabel();
        x12 = new javax.swing.JLabel();
        m11 = new javax.swing.JTextField();
        m12 = new javax.swing.JTextField();
        sleclass = new javax.swing.JComboBox<>();
        jLabel60 = new javax.swing.JLabel();
        exname = new javax.swing.JComboBox<>();
        x13 = new javax.swing.JLabel();
        m13 = new javax.swing.JTextField();
        x14 = new javax.swing.JLabel();
        m14 = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tt2 = new javax.swing.JTable();
        jPanel21 = new javax.swing.JPanel();
        nsub = new javax.swing.JTextField();
        data = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        notice = new javax.swing.JTextArea();
        jPanel7 = new javax.swing.JPanel();
        jTextField6 = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jComboBox4 = new javax.swing.JComboBox<>();
        jComboBox5 = new javax.swing.JComboBox<>();
        jButton13 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        tname = new javax.swing.JLabel();
        cname = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        date1 = new javax.swing.JLabel();
        jobid = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tts = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();

        jLabel48.setText("Payment       :");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Select Month --" }));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        jButton7.setText("Submit");

        jLabel50.setText("Extra    fees      :");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel48)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(55, 55, 55))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel50)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(jButton7)
                .addGap(20, 20, 20))
        );

        jPanel24.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 255), 2, true), "Exam fees"));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 15, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(593, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(228, Short.MAX_VALUE))
        );

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ));
        jScrollPane7.setViewportView(jTable3);

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(77, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(217, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(184, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel9.setBackground(new java.awt.Color(45, 118, 232));

        jLabel23.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/images/group_52px_1.png"))); // NOI18N
        jLabel23.setText(" Teacher");

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/images/slogo100-100.png"))); // NOI18N

        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setText("Student ID: ");

        uname.setEditable(false);
        uname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(jLabel23))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel41)))
                .addGap(18, 18, 18)
                .addComponent(uname, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel41)
                            .addComponent(uname, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel24))
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addGap(32, 32, 32)
                            .addComponent(jLabel23))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 51, 51), 2, true), "User Info", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jLabel1.setText("User Id : ");

        jLabel3.setText("Teacher  Name : ");

        jLabel5.setText("Jobid");

        jLabel7.setText("Joining date  : ");

        jLabel9.setText("Class   : ");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton8.setText("Logout ");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(c4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(10, 10, 10)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(c4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton8)
                .addGap(33, 33, 33))
        );

        jLabel12.setFont(new java.awt.Font("Dotum", 3, 12)); // NOI18N
        jLabel12.setText("Full Name :");

        jLabel13.setFont(new java.awt.Font("Dotum", 3, 12)); // NOI18N
        jLabel13.setText("Subject  :");

        sub.setEditable(false);

        jLabel14.setFont(new java.awt.Font("Dotum", 3, 12)); // NOI18N
        jLabel14.setText("User id");

        u.setEditable(false);

        jLabel15.setFont(new java.awt.Font("Dotum", 3, 12)); // NOI18N
        jLabel15.setText("Password");

        jLabel16.setFont(new java.awt.Font("Dotum", 3, 12)); // NOI18N
        jLabel16.setText("Job id");

        re.setEditable(false);

        jLabel17.setFont(new java.awt.Font("Dotum", 3, 12)); // NOI18N
        jLabel17.setText("Class");

        jLabel18.setFont(new java.awt.Font("Dotum", 3, 12)); // NOI18N
        jLabel18.setText("Gender");

        ge.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Select Gender--", "Male", "Female", "gay" }));

        jLabel25.setFont(new java.awt.Font("Dotum", 3, 12)); // NOI18N
        jLabel25.setText("Phone No");

        jLabel26.setFont(new java.awt.Font("Dotum", 3, 12)); // NOI18N
        jLabel26.setText("Email");

        pic.setBackground(java.awt.SystemColor.activeCaption);
        pic.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        pic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/images/icons8_User_96px.png"))); // NOI18N
        pic.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 204, 255), new java.awt.Color(204, 204, 255), null, new java.awt.Color(153, 255, 255)));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pic, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pic, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel27.setFont(new java.awt.Font("Dotum", 3, 12)); // NOI18N
        jLabel27.setText("Father Name");

        jLabel28.setFont(new java.awt.Font("Dotum", 3, 12)); // NOI18N
        jLabel28.setText("Mother Name");

        jLabel29.setFont(new java.awt.Font("Dotum", 3, 12)); // NOI18N
        jLabel29.setText("Rellgion");

        jLabel30.setFont(new java.awt.Font("Dotum", 3, 12)); // NOI18N
        jLabel30.setText("Age");

        jLabel31.setFont(new java.awt.Font("Dotum", 3, 12)); // NOI18N
        jLabel31.setText("Address");

        jButton2.setText("Update info");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Cancel");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(73, Short.MAX_VALUE))
        );

        jButton16.setText("Choose");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(n, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ge, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(c, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(re, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(p, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(u, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(sub, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(fn, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(mn, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(rel, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(ag, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(ad, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(e, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(po, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(jButton16)))
                .addContainerGap(206, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(po, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(e, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fn, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(mn, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ag, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ad, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jButton16))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(n, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sub, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(u, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(re, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(c, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(83, 83, 83)
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Teacher Profile", jPanel4);

        jButton1.setText("print");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton4.setText("Cancel");

        jPanel26.setBackground(new java.awt.Color(0, 153, 255));
        jPanel26.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 255), null));

        jLabel10.setText("Student Roll No:");

        jLabel19.setText("Student Name:");

        jLabel20.setText("Session  :");

        jLabel21.setText("Father Name :");

        jLabel22.setText("REG no :");

        jLabel32.setText("Class :");

        scla.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Select-", "VI", "VII", "VIII", "IX", "X" }));

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sroll, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sn, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fn3, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                    .addComponent(sess))
                .addGap(18, 18, 18)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(regids)
                    .addComponent(scla, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addComponent(regids, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(scla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel22)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sess, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20)
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(fn3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel21))
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(sroll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(sn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel27.setBackground(new java.awt.Color(102, 204, 255));
        jPanel27.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel40.setText("Total Mark");

        jLabel47.setText("Obtain Mark");

        jLabel51.setText("percentage");

        jLabel52.setText("Grade");

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel51)
                            .addComponent(jLabel52))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(grade)
                            .addComponent(per)))
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                                .addComponent(jLabel47)
                                .addGap(18, 18, 18))
                            .addGroup(jPanel27Layout.createSequentialGroup()
                                .addComponent(jLabel40)
                                .addGap(26, 26, 26)))
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tm, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                            .addComponent(om))))
                .addContainerGap())
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(tm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47)
                    .addComponent(om, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel51)
                    .addComponent(per, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52)
                    .addComponent(grade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65))
        );

        jPanel28.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 102, 51), 2, true), "Find Student ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "S_Roll_no", "S_id", "Session", "S_name", "F_name", "Class"
            }
        ));
        jTable5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable5MouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(jTable5);

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Select Session --" }));

        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Select  Class --" }));
        jComboBox7.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox7ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jButton5.setText("ADD");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton9.setText("UPDATE");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setText("DELETE");

        jButton14.setText("Calculet ");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton12.setText("Show Result");

        jButton11.setText("CLEAR");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton9)
                        .addGap(18, 18, 18)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton10)
                    .addComponent(jButton9)
                    .addComponent(jButton14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton11)
                    .addComponent(jButton12))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel16.setBackground(new java.awt.Color(102, 255, 102));

        m1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        m1.setText("0");
        m1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m1ActionPerformed(evt);
            }
        });

        m3.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        m3.setText("0");
        m3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m3ActionPerformed(evt);
            }
        });

        m5.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        m5.setText("0");
        m5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m5ActionPerformed(evt);
            }
        });

        m2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        m2.setText("0");
        m2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m2ActionPerformed(evt);
            }
        });

        m4.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        m4.setText("0");
        m4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m4ActionPerformed(evt);
            }
        });

        m6.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        m6.setText("0");
        m6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m6ActionPerformed(evt);
            }
        });

        jLabel53.setText("Exam name");

        m7.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        m7.setText("0");
        m7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m7ActionPerformed(evt);
            }
        });

        m8.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        m8.setText("0");
        m8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m8ActionPerformed(evt);
            }
        });

        m10.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        m10.setText("0");
        m10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m10ActionPerformed(evt);
            }
        });

        m9.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        m9.setText("0");
        m9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m9ActionPerformed(evt);
            }
        });

        m11.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        m11.setText("0");
        m11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m11ActionPerformed(evt);
            }
        });

        m12.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        m12.setText("0");
        m12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m12ActionPerformed(evt);
            }
        });

        sleclass.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Select-" }));
        sleclass.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                sleclassItemStateChanged(evt);
            }
        });
        sleclass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sleclassActionPerformed(evt);
            }
        });

        jLabel60.setText("Class name");

        exname.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Select --" }));

        m13.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        m13.setText("0");
        m13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m13ActionPerformed(evt);
            }
        });

        m14.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        m14.setText("0");
        m14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m14ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(x3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(m3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52)
                                .addComponent(x4, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(m4, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGap(123, 123, 123)
                                .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sleclass, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(x7, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(m7, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(x5, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(m5, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(x9, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(m9, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(x11, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(m11, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(52, 52, 52)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(x12, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(m12, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(x10, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(m10, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel16Layout.createSequentialGroup()
                                    .addComponent(x6, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(m6, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel16Layout.createSequentialGroup()
                                    .addComponent(x8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(18, 18, 18)
                                    .addComponent(m8, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(x1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(m1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52)
                                .addComponent(x2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(m2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(exname, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(x13, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(m13, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(x14, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(m14, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sleclass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel60))
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel53)
                        .addComponent(exname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(x1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(x2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(m1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(m2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(x3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(m3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(x4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(m4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(x5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(m5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(x6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(m6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)))
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(x7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(m7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(x8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(m8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)))
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(x9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(m9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(x10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(m10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)))
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(x11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(m11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(x12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(m12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)))
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(x13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(m13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(x14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(m14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(564, 564, 564)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Exam Mark", jPanel5);

        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "Notice Board", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 24))); // NOI18N

        tt2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Date / Time", "Notice"
            }
        ));
        tt2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tt2MousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(tt2);

        notice.setColumns(20);
        notice.setRows(5);
        jScrollPane4.setViewportView(notice);

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
                    .addComponent(nsub)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(data, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(data, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(nsub, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(136, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 39, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Notice", jPanel11);

        jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField6KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField6KeyTyped(evt);
            }
        });

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10", "Title 11", "Title 12"
            }
        ));
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable4MousePressed(evt);
            }
        });
        jScrollPane8.setViewportView(jTable4);

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Select Session --" }));

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Select  Class --" }));

        jButton13.setText("Show Student ");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(jButton13)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(182, 182, 182)
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(442, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        jTabbedPane1.addTab("Student Info", jPanel7);

        jLabel45.setText(" Teacher Name:  ");

        jLabel46.setText("Class : ");

        jLabel61.setText("Date : ");

        jLabel62.setText("Job ID: ");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 483, Short.MAX_VALUE)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel45)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cname, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tname, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(308, 308, 308)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(date1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jobid, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tname, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel61, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(date1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cname, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jobid, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        tts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Roll", "Reg", "Name", "Attendance"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tts);

        jButton6.setText("submit");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton15.setText("Update");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton6)
                    .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(203, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Attandance", jPanel6);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 501, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void unameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_unameActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        getdata();

        String a = jLabel2.getText();
        try {
            con = DBConnection.getDBConnection();
            stmt = con.createStatement();
            int count = 0;
            System.out.println("data");
                                                                            //t_jobid, user_id, pass, t_name, m_class, subject, gender, father, mother, rellgion, birth_date, age, phone, email, address, pic, join_date
           
            count = stmt.executeUpdate("update teacherid set  t_name='" + name + "', user_id='" + tuser + "', pass='" + pass + "', t_jobid='" + reg + "',m_class='" + sclas + "', gender='" + gend + "', phone='" + phone + "', email='" + mail + "',father='" + father + "', mother='" + mother + "', rellgion='" + rellgion + "', address='" + address + "', pic='" + person_image + "' where t_jobid='" + a + "' ");

            if (count > 0) {
                JOptionPane.showMessageDialog(rootPane, "Update Successful");
            } else {
                JOptionPane.showMessageDialog(rootPane, "Update faild", "Worning", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
                stmt.close();
            } catch (Exception e) {
            }
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tt2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tt2MousePressed
        // TODO add your handling code here:

        int selectrow = tt2.getSelectedRow();
        try {
            nsub.setText(tt2.getValueAt(selectrow, 0).toString());
            notice.setText(tt2.getValueAt(selectrow, 1).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tt2MousePressed

    private void shownoticedata() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs;
        try {
            con = DBConnection.getDBConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("select * from noticeboard order by date_time desc");
            while (rs.next()) {
                noticebord.addRow(new Object[]{rs.getString("n_subject"), rs.getString("notice")});

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
                stmt.close();
            } catch (Exception e) {
            }
        }
    }


    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void m1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_m1ActionPerformed

    private void m3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_m3ActionPerformed

    private void m5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_m5ActionPerformed

    private void m2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_m2ActionPerformed

    private void m4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_m4ActionPerformed

    private void m6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_m6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        getdata();
        getalldata();
        if (bangla.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Please give Mark");
            m1.requestFocus();
        } else {
            if (english.isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "Please give Mark");
                m1.requestFocus();
            } else {
                if (math.isEmpty()) {
                    JOptionPane.showMessageDialog(rootPane, "Please give Mark");
                    m1.requestFocus();
                } else {
                    if (islam.isEmpty()) {
                        JOptionPane.showMessageDialog(rootPane, "Please give Mark");
                        m1.requestFocus();
                    } else {
                        if (computer.isEmpty()) {
                            JOptionPane.showMessageDialog(rootPane, "Please give Mark");
                            m1.requestFocus();
                        } else {
                            if (science.isEmpty()) {
                                JOptionPane.showMessageDialog(rootPane, "Please give Mark");
                                m1.requestFocus();
                            } else {
                                if (exname.getSelectedIndex() == 0) {
                                    JOptionPane.showMessageDialog(rootPane, "Select Exam type ");
                                } else {
                                    givemark();
                                    //mark();
                                }
                            }
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void mark() {
        studentname = sn.getText();
        roll = sroll.getText();
        faname = fn.getText();
        gu = sess.getText();
        regno = regids.getText();
        sclass = scla.getSelectedItem().toString();
        b = Integer.parseInt(m1.getText());
        engl = Integer.parseInt(m3.getText());
        mat = Integer.parseInt(m2.getText());
        isl = Integer.parseInt(m4.getText());
        compu = Integer.parseInt(m5.getText());
        sci = Integer.parseInt(m6.getText());
        tmt = Double.parseDouble(tm.getText());
        obt = Double.parseDouble(om.getText());
        pe = Double.parseDouble(per.getText());
        gpa = grade.getText();
        String exam = exname.getSelectedItem().toString();

        Connection con = null;
        PreparedStatement stmt = null;

        try {

            con = DBConnection.getDBConnection();

            stmt = con.prepareStatement("insert into exammark (regid, roll, name, father, gro, class, banglaq, englishq, mathq, islamq, computerq, scienceq, totalq, omarkq, percentageq, gradeq) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
            stmt.setString(1, regno);
            stmt.setString(2, roll);
            stmt.setString(3, studentname);
            stmt.setString(4, faname);
            stmt.setString(5, gu);
            stmt.setString(6, sclass);
            stmt.setInt(7, b);
            stmt.setInt(8, engl);
            stmt.setInt(9, mat);
            stmt.setInt(10, isl);
            stmt.setInt(11, compu);
            stmt.setInt(12, sci);
            stmt.setDouble(13, tmt);
            stmt.setDouble(14, obt);
            stmt.setDouble(15, pe);
            stmt.setString(16, gpa);

            int s = stmt.executeUpdate();
            if (s > 0) {

                JOptionPane.showMessageDialog(rootPane, "Insertion is done");
            } else {
                JOptionPane.showMessageDialog(this, "Insertion error", "error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
                stmt.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }

    }

    private void givemark() {
        String en, si, cn, sy, s, em;
        en = exname.getSelectedItem().toString();
        si = regids.getText();
        cn = sleclass.getSelectedItem().toString();
        sy = sess.getText();

        String s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12, s13, s14;
        s1 = x1.getText();
        s2 = x2.getText();
        s3 = x3.getText();
        s4 = x4.getText();
        s5 = x5.getText();
        s6 = x6.getText();
        s7 = x7.getText();
        s8 = x8.getText();
        s9 = x9.getText();
        s10 = x10.getText();
        s11 = x11.getText();
        s12 = x12.getText();
        s13 = x12.getText();
        s14 = x12.getText();

        String a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14;
        String y;
        y = sleclass.getSelectedItem().toString();
        a1 = m1.getText();
        a2 = m2.getText();
        a3 = m3.getText();
        a4 = m4.getText();
        a5 = m5.getText();
        a6 = m6.getText();
        a7 = m7.getText();
        a8 = m8.getText();
        a9 = m9.getText();
        a10 = m10.getText();
        a11 = m11.getText();
        a12 = m12.getText();
        a13 = m13.getText();
        a14 = m14.getText();

        String selcla;
        selcla = sleclass.getSelectedItem().toString();
        con = DBConnection.getDBConnection();
        ResultSet rs = null;
        ResultSet sub = null;
        try {
            stmt = con.createStatement();
            System.out.println("is this ");
            rs = stmt.executeQuery("select Subject_name  from  class_subject where class_name='" + selcla + "'");
            int count = 0;

            System.out.println(rs.next());

            int p = 0;
            while (rs.next()) {
                p++;

            }
            System.out.println(p);
            for (int i = 0; i <= p; i++) {
                if (i == 0) {
                    count = stmt.executeUpdate("insert into s_exam_mark (exam_name, stu_id, class_name, session_year,"
                            + "subject,e_mark) values('" + en + "','" + si + "', '" + cn + "', '" + sy + "', '" + s1 + "', '" + a1 + "')");

                } else {
                    if (i == 1) {
                        count = stmt.executeUpdate("insert into s_exam_mark (exam_name, stu_id, class_name, session_year,"
                                + "subject,e_mark) values('" + en + "','" + si + "', '" + cn + "', '" + sy + "', '" + s2 + "', '" + a2 + "')");
                    } else {
                        if (i == 2) {
                            count = stmt.executeUpdate("insert into s_exam_mark (exam_name, stu_id, class_name, session_year,"
                                    + "subject,e_mark) values('" + en + "','" + si + "', '" + cn + "', '" + sy + "', '" + s3 + "', '" + a3 + "')");
                        } else {
                            if (i == 3) {
                                count = stmt.executeUpdate("insert into s_exam_mark (exam_name, stu_id, class_name, session_year,"
                                        + "subject,e_mark) values('" + en + "','" + si + "', '" + cn + "', '" + sy + "', '" + s4 + "', '" + a4 + "')");
                            } else {
                                if (i == 4) {
                                    count = stmt.executeUpdate("insert into s_exam_mark (exam_name, stu_id, class_name, session_year,"
                                            + "subject,e_mark) values('" + en + "','" + si + "', '" + cn + "', '" + sy + "', '" + s5 + "', '" + a5 + "')");
                                } else {
                                    if (i == 5) {
                                        count = stmt.executeUpdate("insert into s_exam_mark (exam_name, stu_id, class_name, session_year,"
                                                + "subject,e_mark) values('" + en + "','" + si + "', '" + cn + "', '" + sy + "', '" + s6 + "', '" + a6 + "')");
                                    } else {
                                        if (i == 6) {
                                            count = stmt.executeUpdate("insert into s_exam_mark (exam_name, stu_id, class_name, session_year,"
                                                    + "subject,e_mark) values('" + en + "','" + si + "', '" + cn + "', '" + sy + "', '" + s7 + "', '" + a7 + "')");
                                        } else {
                                            if (i == 7) {
                                                count = stmt.executeUpdate("insert into s_exam_mark (exam_name, stu_id, class_name, session_year,"
                                                        + "subject,e_mark) values('" + en + "','" + si + "', '" + cn + "', '" + sy + "', '" + s8 + "', '" + a8 + "')");
                                            } else {
                                                if (i == 8) {
                                                    count = stmt.executeUpdate("insert into s_exam_mark (exam_name, stu_id, class_name, session_year,"
                                                            + "subject,e_mark) values('" + en + "','" + si + "', '" + cn + "', '" + sy + "', '" + s9 + "', '" + a9 + "')");
                                                } else {
                                                    if (i == 9) {
                                                        count = stmt.executeUpdate("insert into s_exam_mark (exam_name, stu_id, class_name, session_year,"
                                                                + "subject,e_mark) values('" + en + "','" + si + "', '" + cn + "', '" + sy + "', '" + s10 + "', '" + a10 + "')");
                                                    } else {
                                                        if (i == 10) {
                                                            count = stmt.executeUpdate("insert into s_exam_mark (exam_name, stu_id, class_name, session_year,"
                                                                    + "subject,e_mark) values('" + en + "','" + si + "', '" + cn + "', '" + sy + "', '" + s11 + "', '" + a11 + "')");
                                                        } else {
                                                            if (i == 11) {
                                                                count = stmt.executeUpdate("insert into s_exam_mark (exam_name, stu_id, class_name, session_year,"
                                                                        + "subject,e_mark) values('" + en + "','" + si + "', '" + cn + "', '" + sy + "', '" + s12 + "', '" + a12 + "')");
                                                            } else {
                                                                if (i == 12) {
                                                                    count = stmt.executeUpdate("insert into s_exam_mark (exam_name, stu_id, class_name, session_year,"
                                                                            + "subject,e_mark) values('" + en + "','" + si + "', '" + cn + "', '" + sy + "', '" + s13 + "', '" + a13 + "')");
                                                                } else {
                                                                    if (i == 13) {
                                                                        count = stmt.executeUpdate("insert into s_exam_mark (exam_name, stu_id, class_name, session_year,"
                                                                                + "subject,e_mark) values('" + en + "','" + si + "', '" + cn + "', '" + sy + "', '" + s14 + "', '" + a14 + "')");
                                                                    }
                                                                }

                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

            }
            JOptionPane.showMessageDialog(rootPane, "done");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {

            } catch (Exception e) {
            }

        }

    }

    private void getalldata() {
        //int bangla, english, math, islam, computer, science;totalmark;obtain, percen, gpa;

        bangla = m1.getText();
        english = m3.getText();
        math = m2.getText();
        islam = m4.getText();
        computer = m5.getText();
        science = m6.getText();
        tmark = tm.getText();
        obtain = om.getText();
        percen = per.getText();
        gpa = grade.getText();
    }

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void getconnection() {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getDBConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("select  name,father,regid, s_group from studentid where roll='" + roll + "' and class='" + sclass + "'");
            //rs= stmt.executeUpdate("select name, father  from studentid where roll='"+roll+"'");

            int count = 0;

            while (rs.next()) {
                sn.setText(rs.getString("name"));
                fn.setText(rs.getString("father"));
                regids.setText(rs.getString("regid"));
                sess.setText(rs.getString("s_group"));
                count++;

            }
            if (count == 0) {
                JOptionPane.showMessageDialog(rootPane, "There is no such record");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
                stmt.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }

    }


    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        //datamark();
        total();
    }//GEN-LAST:event_jButton14ActionPerformed

    private void datamark() {
        int bangla, english, math, islam, computer, science;
        bangla = Integer.parseInt(m1.getText());
        english = Integer.parseInt(m2.getText());
        math = Integer.parseInt(m3.getText());
        islam = Integer.parseInt(m4.getText());
        roll = sroll.getText();
        faname = fn.getText();
        gu = sess.getText();
        computer = Integer.parseInt(m5.getText());
        science = Integer.parseInt(m6.getText());

    }

    private void total() {

        int a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14;
        String y;
        y = sleclass.getSelectedItem().toString();
        a1 = Integer.parseInt(m1.getText());
        a2 = Integer.parseInt(m2.getText());
        a3 = Integer.parseInt(m3.getText());
        a4 = Integer.parseInt(m4.getText());
        a5 = Integer.parseInt(m5.getText());
        a6 = Integer.parseInt(m6.getText());
        a7 = Integer.parseInt(m7.getText());
        a8 = Integer.parseInt(m8.getText());
        a9 = Integer.parseInt(m9.getText());
        a10 = Integer.parseInt(m10.getText());
        a11 = Integer.parseInt(m11.getText());
        a12 = Integer.parseInt(m12.getText());
        a13 = Integer.parseInt(m13.getText());
        a14 = Integer.parseInt(m14.getText());
        int po = 0;
        try {
            con = DBConnection.getDBConnection();
            stmt = con.createStatement();
            int z = 0;
            //String m[];

            rs = stmt.executeQuery("select Subject_name from class_subject where class_name='" + y + "'");
            while (rs.next()) {
                po = rs.getRow();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        int totalmark;
        totalmark = (a1 + a2 + a3 + a4 + a5 + a6 + a7 + a8 + a9 + a10 + a11 + a12 + a13 + a14);

        int obtain, percen;
        obtain = totalmark / po;
        percen = (totalmark * 100) / (po * 100);

//        int totalmark;
//        totalmark = (a1+a2+a3+a4+a5+a6+a7+a8+a9+a10+a11+a12+a13+a14);
//        int obtain, percen;
//        obtain = totalmark / 6;
//        percen = (totalmark * 100) / 600;
        tm.setText(totalmark + "");
        om.setText(obtain + "");
        per.setText(percen + "");

        if (percen > 80) {
            grade.setText("A+");
        } else {
            if (percen > 70) {
                grade.setText("B+");
            } else {
                if (percen > 60) {
                    grade.setText("B");
                } else {
                    if (percen > 50) {
                        grade.setText("C+");
                    } else {
                        if (percen > 40) {
                            grade.setText("C");
                        } else {
                            grade.setText("Faild");
                        }
                    }
                }
            }
        }

    }

    private void m7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_m7ActionPerformed

    private void m8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_m8ActionPerformed

    private void m10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_m10ActionPerformed

    private void m9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_m9ActionPerformed

    private void m11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_m11ActionPerformed

    private void m12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_m12ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        savedata();

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        updateattndence();
    }//GEN-LAST:event_jButton15ActionPerformed

    private void sleclassItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_sleclassItemStateChanged
        // TODO add your handling code here:
        clearfild();
        String a = sleclass.getSelectedItem().toString();
        showfild();
        try {
            con = DBConnection.getDBConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("select * from class_subject where class_name='" + a + "'");

            int p = 0;
            while (rs.next()) {
                p++;
                for (int i = 0; i < p; i++) {
                    if (p == 1) {
                        x1.setText(rs.getString(3));

                    } else {
                        if (p == 2) {
                            x2.setText(rs.getString(3));
                        } else {
                            if (p == 3) {
                                x3.setText(rs.getString(3));
                            } else {
                                if (p == 4) {
                                    x4.setText(rs.getString(3));
                                } else {
                                    if (p == 5) {
                                        x5.setText(rs.getString(3));
                                    } else {
                                        if (p == 6) {
                                            x6.setText(rs.getString(3));
                                        } else {
                                            if (p == 7) {
                                                x7.setText(rs.getString(3));
                                            } else {
                                                if (p == 8) {
                                                    x8.setText(rs.getString(3));
                                                } else {
                                                    if (p == 9) {
                                                        x9.setText(rs.getString(3));
                                                    } else {
                                                        if (p == 10) {
                                                            x10.setText(rs.getString(3));
                                                        } else {
                                                            if (p == 11) {
                                                                x11.setText(rs.getString(3));
                                                            } else {
                                                                if (p == 12) {
                                                                    x12.setText(rs.getString(3));
                                                                } else {
                                                                    if (p == 13) {
                                                                        x13.setText(rs.getString(3));
                                                                    } else {
                                                                        if (p == 14) {
                                                                            x14.setText(rs.getString(3));
                                                                        }
                                                                    }

                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

            }

        } catch (Exception e) {
        }
        showfild();

    }//GEN-LAST:event_sleclassItemStateChanged


    private void jTable5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable5MouseClicked
        // TODO add your handling code here:
        int selectedrow = jTable5.getSelectedRow();
        try {
            sroll.setText(jTable5.getValueAt(selectedrow, 0).toString());
            regids.setText(jTable5.getValueAt(selectedrow, 1).toString());
            sess.setText(jTable5.getValueAt(selectedrow, 2).toString());
            sn.setText(jTable5.getValueAt(selectedrow, 3).toString());
            fn3.setText(jTable5.getValueAt(selectedrow, 3).toString());
            scla.setSelectedItem(jTable5.getValueAt(selectedrow, 3).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_jTable5MouseClicked

    private void jComboBox7ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox7ItemStateChanged
        // TODO add your handling code here:
        showallStudents();

    }//GEN-LAST:event_jComboBox7ItemStateChanged

    private void m13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_m13ActionPerformed

    private void m14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_m14ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        String n = uname.getText();
        UserLogin stu = new UserLogin();
        //stu.admin.setText(n);
        stu.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_jButton8ActionPerformed

    private void sleclassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sleclassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sleclassActionPerformed

    private void jTable4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable4MousePressed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        if (jComboBox4.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Select Student Session");
        } else {
            if (jComboBox5.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(rootPane, "Select Student Class");
            } else {
                showallstuinfo();

            }

        }

    }//GEN-LAST:event_jButton13ActionPerformed

    private void jTextField6KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyTyped
        // TODO add your handling code here:
        searchstudent();
    }//GEN-LAST:event_jTextField6KeyTyped

    private void jTextField6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyReleased
        // TODO add your handling code here:
        searchstudent();
    }//GEN-LAST:event_jTextField6KeyReleased

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
        
        getimg();
    }//GEN-LAST:event_jButton16ActionPerformed

    private void getimg() {
        JFileChooser ch = new JFileChooser();
        int i = ch.showOpenDialog(this);
        if (i == JFileChooser.APPROVE_OPTION) {

            File f = ch.getSelectedFile();
            filename = f.getAbsolutePath();
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(filename).getImage().getScaledInstance(pic.getWidth(), pic.getHeight(), Image.SCALE_DEFAULT));
            
//            byte[] img = (userList().get(i).getst_image());
//            ImageIcon imageIcon = new ImageIcon(new ImageIcon(filename).getImage().getScaledInstance(pic.getWidth(), pic.getHeight(), Image.SCALE_DEFAULT));
//            pic.setIcon(imageIcon);
//            person_image = filename;
//            
            
            pic.setIcon(imageIcon);
            try {
                File image = new File(filename);
                FileInputStream fis = new FileInputStream(image);
                ByteOutputStream bos = new ByteOutputStream();
                byte[] buf = new byte[1024];
                for (int readNum; (readNum = fis.read(buf)) != -1;) {
                    bos.write(buf, 0, readNum);
                }
                person_image = bos.toByteArray();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    
    
    
    
    
    
    private void showallstuinfo() {
        String a, b;
        a = jComboBox4.getSelectedItem().toString();
        b = jComboBox5.getSelectedItem().toString();
        if (jTable4.getRowCount() > 0) {
            for (int i = jTable4.getRowCount() - 1; i > -1; i--) {
                allstuinfo.removeRow(i);
            }
        }

        try {
            con = DBConnection.getDBConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("select * from student_info where session_year='" + a + "' and class_name='" + b + "'");
            while (rs.next()) {

                allstuinfo.addRow(new Object[]{rs.getString("stu_id"), rs.getString("session_year"), rs.getString("class_name"), rs.getString("roll_num"), rs.getString("s_name"), rs.getString("father_name"), rs.getString("mother_name"), rs.getString("phone_no"), rs.getString("email_id"), rs.getString("rellgion"), rs.getString("address")});
                //allstumodel.addRow(new Object[]{rs.getString("roll_num"), rs.getString("stu_id"), rs.getString("session_year"), rs.getString("s_name"), rs.getString("father_name"), rs.getString("class_name")});
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (Exception e) {
            }
        }

    }

    private void searchstudent() {
        String a, b;
        a = jTextField6.getText();
        if (jTable4.getRowCount() > 0) {
            for (int i = jTable4.getRowCount() - 1; i > -1; i--) {
                allstuinfo.removeRow(i);
            }
        }

        try {
            con = DBConnection.getDBConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("select * from student_info where stu_id='" + a + "' ");
            while (rs.next()) {

                allstuinfo.addRow(new Object[]{rs.getString("stu_id"), rs.getString("session_year"), rs.getString("class_name"), rs.getString("roll_num"), rs.getString("s_name"),  rs.getString("father_name"), rs.getString("mother_name"), rs.getString("phone_no"), rs.getString("email_id"), rs.getString("rellgion"), rs.getString("address")});
                //rs.getString("roll_num"), rs.getString("stu_id"), rs.getString("session_year"), rs.getString("s_name"), rs.getString("father_name"), rs.getString("class_name")});
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (Exception e) {
            }
        }

    }

    private void showallStudents() {

        String a, b;
        a = jComboBox6.getSelectedItem().toString();
        b = jComboBox7.getSelectedItem().toString();
        if (jTable5.getRowCount() > 0) {
            for (int i = jTable5.getRowCount() - 1; i > -1; i--) {
                allstumodel.removeRow(i);
            }
        }

        try {
            con = DBConnection.getDBConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("select * from student_info where session_year='" + a + "' and class_name='" + b + "'");
            while (rs.next()) {
                allstumodel.addRow(new Object[]{rs.getString("roll_num"), rs.getString("stu_id"), rs.getString("session_year"), rs.getString("s_name"), rs.getString("father_name"), rs.getString("class_name")});
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (Exception e) {
            }
        }

    }

    private void clearfild() {
        x1.setText("");
        x2.setText("");
        x3.setText("");
        x4.setText("");
        x5.setText("");
        x6.setText("");
        x7.setText("");
        x8.setText("");
        x9.setText("");
        x10.setText("");
        x11.setText("");
        x12.setText("");
        x13.setText("");
        x14.setText("");

    }

    private void showfild() {
        String s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12, s13, s14;
        s1 = x1.getText();
        s2 = x2.getText();
        s3 = x3.getText();
        s4 = x4.getText();
        s5 = x5.getText();
        s6 = x6.getText();
        s7 = x7.getText();
        s8 = x8.getText();
        s9 = x9.getText();
        s10 = x10.getText();
        s11 = x11.getText();
        s12 = x12.getText();
        s13 = x12.getText();
        s14 = x12.getText();

        if (s1.isEmpty()) {
            m1.setVisible(false);
        } else {
            m1.setVisible(true);
        }

        if (s2.isEmpty()) {
            m2.setVisible(false);
        } else {
            m2.setVisible(true);
        }

        if (s3.isEmpty()) {
            m3.setVisible(false);
        } else {
            m3.setVisible(true);
        }

        if (s4.isEmpty()) {
            m4.setVisible(false);
        } else {
            m4.setVisible(true);
        }

        if (s5.isEmpty()) {
            m5.setVisible(false);
        } else {
            m5.setVisible(true);
        }

        if (s6.isEmpty()) {
            m6.setVisible(false);
        } else {
            m6.setVisible(true);
        }

        if (s7.isEmpty()) {
            m7.setVisible(false);
        } else {
            m7.setVisible(true);
        }

        if (s8.isEmpty()) {
            m8.setVisible(false);
        } else {
            m8.setVisible(true);
        }

        if (s9.isEmpty()) {
            m9.setVisible(false);
        } else {
            m9.setVisible(true);
        }

        if (s10.isEmpty()) {
            m10.setVisible(false);
        } else {
            m10.setVisible(true);
        }

        if (s11.isEmpty()) {
            m11.setVisible(false);
        } else {
            m11.setVisible(true);
        }

        if (s12.isEmpty()) {
            m12.setVisible(false);
        } else {
            m12.setVisible(true);
        }

        if (s13.isEmpty()) {
            m13.setVisible(false);
        } else {
            m13.setVisible(true);
        }

        if (s14.isEmpty()) {
            m14.setVisible(false);
        } else {
            m14.setVisible(true);
        }

    }

    private void savedata() {
        Connection con = null;
        Statement stmt = null;
        String sql = null;
        try {
            con = DBConnection.getDBConnection();
            stmt = con.createStatement();

            //int sql=0;
            for (int i = 0;; i++) {
                sql = "insert into attandance (s_roll,stu_id, s_name, attan ) values(";
                sql += model.getValueAt(i, 0) + ", ";
                sql += "'" + model.getValueAt(i, 1) + "', ";
                sql += "'" + model.getValueAt(i, 2) + "', ";
                sql += model.getValueAt(i, 3) + ")";

                //sql += cname.getText();
                System.out.println(sql);
                stmt.execute(sql);
                sql = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
                stmt.close();
            } catch (Exception e) {
            }
        }
    }

    private void updateattndence() {
        Connection con = null;
        Statement stmt = null;
        String sql = null;
        try {
            con = DBConnection.getDBConnection();
            stmt = con.createStatement();

            //int sql=0;
            for (int i = 0;; i++) {
                sql = "update attandance set (s_roll,stu_id, s_name, attan ) values(";
                sql += model.getValueAt(i, 0) + ", ";
                sql += "'" + model.getValueAt(i, 1) + "', ";
                sql += "'" + model.getValueAt(i, 2) + "', ";
                sql += model.getValueAt(i, 3) + ")";

                //sql += cname.getText();
                System.out.println(sql);
                stmt.execute(sql);
                sql = "";
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
                stmt.close();
            } catch (Exception e) {
            }
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Teacherhome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Teacherhome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Teacherhome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Teacherhome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Teacherhome().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ad;
    private javax.swing.JTextField ag;
    private javax.swing.JTextField c;
    private javax.swing.JLabel c4;
    private javax.swing.JLabel cname;
    private javax.swing.JLabel data;
    private javax.swing.JLabel date1;
    private javax.swing.JTextField e;
    private javax.swing.JComboBox<String> exname;
    private javax.swing.JTextField fn;
    private javax.swing.JTextField fn3;
    private javax.swing.JComboBox<String> ge;
    private javax.swing.JTextField grade;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JLabel jobid;
    private javax.swing.JTextField m1;
    private javax.swing.JTextField m10;
    private javax.swing.JTextField m11;
    private javax.swing.JTextField m12;
    private javax.swing.JTextField m13;
    private javax.swing.JTextField m14;
    private javax.swing.JTextField m2;
    private javax.swing.JTextField m3;
    private javax.swing.JTextField m4;
    private javax.swing.JTextField m5;
    private javax.swing.JTextField m6;
    private javax.swing.JTextField m7;
    private javax.swing.JTextField m8;
    private javax.swing.JTextField m9;
    private javax.swing.JTextField mn;
    private javax.swing.JTextField n;
    private javax.swing.JTextArea notice;
    private javax.swing.JTextField nsub;
    private javax.swing.JTextField om;
    private javax.swing.JTextField p;
    private javax.swing.JTextField per;
    private javax.swing.JLabel pic;
    private javax.swing.JTextField po;
    private javax.swing.JTextField re;
    private javax.swing.JTextField regids;
    private javax.swing.JTextField rel;
    private javax.swing.JComboBox<String> scla;
    private javax.swing.JTextField sess;
    private javax.swing.JComboBox<String> sleclass;
    private javax.swing.JTextField sn;
    private javax.swing.JTextField sroll;
    private javax.swing.JTextField sub;
    private javax.swing.JTextField tm;
    private javax.swing.JLabel tname;
    private javax.swing.JTable tt2;
    private javax.swing.JTable tts;
    private javax.swing.JTextField u;
    public javax.swing.JTextField uname;
    private javax.swing.JLabel x1;
    private javax.swing.JLabel x10;
    private javax.swing.JLabel x11;
    private javax.swing.JLabel x12;
    private javax.swing.JLabel x13;
    private javax.swing.JLabel x14;
    private javax.swing.JLabel x2;
    private javax.swing.JLabel x3;
    private javax.swing.JLabel x4;
    private javax.swing.JLabel x5;
    private javax.swing.JLabel x6;
    private javax.swing.JLabel x7;
    private javax.swing.JLabel x8;
    private javax.swing.JLabel x9;
    // End of variables declaration//GEN-END:variables
}
