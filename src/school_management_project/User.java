
package school_management_project;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author SAM
 */
public class User {

    //private int st_id;
    //String first_name, last_name, dob, gender, phone, address, email, post_code, label, batch_name, schedule, reg_date;
    //byte[] st_image;
    //double fees;

//  byte[] person_image;
    
    private int stu_id;
    String  session_year, class_name, roll_num, s_name, user_id, password, s_gender, father_name, mother_name, phone_no, email_id, rellgion, address, s_group, birth_date, age;
    byte[] pic;
    //public User(int stu_id, String session_year,String class_name,String roll_num,String s_name,String user_id,String password,String s_gender,String father_name, String mother_name, String phone_no, String email_id, String rellgion,String address, byte[] pic, String s_group, String birth_date, String age;)
    public User(int stu_id, String session_year, String class_name, String roll_num, String s_name, String user_id, String password, String s_gender, String father_name, 
            String mother_name, String phone_no, String email_id, String rellgion, String address,byte[] pic, String s_group,String birth_date, String age
    ) {
        this.stu_id = stu_id;
        this.session_year = session_year;
        this.class_name = class_name;
        this.roll_num = roll_num;
        this.s_name = s_name;
        this.user_id = user_id;
        this.password = password;
        this.s_gender = s_gender;
        this.father_name = father_name;
        this.mother_name = mother_name;
        this.phone_no = phone_no;
        this.email_id = email_id;
        this.rellgion = rellgion;
        this.address = address;
        this.s_group = s_group;
        this.birth_date= birth_date;
        this.age= age;
    }

    
    
    
    
    
    
    
    
//    public int getst_id() {
//        return st_id;
//
//    }
//
//    public String getfirst_name() {
//        return first_name;
//
//    }
//
//    public String getlast_name() {
//        return last_name;
//
//    }
//
//    public String getdob() {
//        return dob;
//
//    }
//
//    public String getgender() {
//        return gender;
//
//    }
//
//    public String getphone() {
//        return phone;
//
//    }
//
//    public String getaddress() {
//        return address;
//
//    }
//
//    public String getemail() {
//        return email;
//
//    }
//
//    public String getpost_code() {
//        return post_code;
//
//    }
//
//    public byte[] getst_image() {
//        return st_image;
//
//    }
//
//    public String getlabel() {
//        return label;
//
//    }
//
//    public String getbatch_name() {
//        return batch_name;
//
//    }
//
//    public Double getfees() {
//        return fees;
//
//    }
//
//    public String getschedule() {
//        return schedule;
//
//    }
//
//    public String getreg_date() {
//        return reg_date;
//
//    }

    public int getStu_id() {
        return stu_id;
    }

    public String getSession_year() {
        return session_year;
    }

    public String getClass_name() {
        return class_name;
    }

    public String getRoll_num() {
        return roll_num;
    }

    public String getS_name() {
        return s_name;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getPassword() {
        return password;
    }

    public String getS_gender() {
        return s_gender;
    }

    public String getFather_name() {
        return father_name;
    }

    public String getMother_name() {
        return mother_name;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public String getEmail_id() {
        return email_id;
    }

    public String getRellgion() {
        return rellgion;
    }

    public String getAddress() {
        return address;
    }

    public String getS_group() {
        return s_group;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public String getAge() {
        return age;
    }

    public byte[] getPic() {
        return pic;
    }

}
