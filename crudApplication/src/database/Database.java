package database;

import java.util.Scanner;
import java.sql.*;

class info {

    public static String names;
    String roll;
    String course;
    String branch;
    String result1;
    String result;

    public void admin() {
        System.out.println("Login Page : Please  Login Here !!..");
        String email;
        String pass;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your Email id:");
        email = sc.nextLine();
        System.out.println("Enter Your Password");
        pass = sc.nextLine();
        String query = "select*from user Where email=\"" + email + "\" and password=\"" + pass + "\";"; // congetation \""+ em +"\"
        // String query1="select*from user Where password="+pass;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/student", "root", "");
            Statement st = con.createStatement();

     //  Statement st1=con.createStatement();
            //  st.setString(1,names);
            // 5th step 
            if (email.isEmpty() && pass.isEmpty()) {
                System.out.println("Something went wrong");
                admin();
            } else {

                ResultSet rs = st.executeQuery(query);
                rs.next();
                result1 = rs.getString("email");
                result = rs.getString("password");

            }

    //  ResultSet rs1=st.executeQuery(query1);
            // String result1= rs.getString(2);
            //String result = rs1.getString(4);
            if (email.equals(result1) && pass.equals(result)) {
                System.out.println("Succesfully login");
                main();
            } else {

                System.out.println("Something went wrong");
            }

            con.close();

     // ResultSet rs = st.executeQuery(query);
        } catch (Exception e) {

            System.out.println(e);
        }

    }

    public void main() {

        int i;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Enter your choice");
            System.out.println("1 User registration!");
            System.out.println("2 for Insert data");
            System.out.println("3 show data");
            System.out.println("4 delete data");
            System.out.println("5 Update data");
            i = sc.nextInt();
            switch (i) {
                case 1:
                    signup();/// ;
                    break;
                case 2:
                    insert();
                    break;
                case 3:
                    show();// ;
                    break;
                case 4:
                    delete();
                    break;
                case 5:
                    update();
                    break;
                default:
                    System.out.println("Something went  wrong!");
                    break;
            }
        } while (i != 6);

    }

    public void data() {
        Scanner kp = new Scanner(System.in);
        System.out.println("Enter your name:");
        names = kp.nextLine();
        System.out.println("Enter your Course:");
        course = kp.nextLine();
        System.out.println("Enter your branch:");
        branch = kp.nextLine();
        System.out.println("Enter your roll Number:");
        roll = kp.nextLine();

    }

// insert data into database
    public void insert() {
        data();
        String query = "Insert into data(name,roll,course,branch)Value(?,?,?,?)";
        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/student", "root", "");
            PreparedStatement pst = con.prepareStatement(query);

            pst.setString(1, names);
            pst.setString(2, roll);
            pst.setString(3, course);
            pst.setString(4, branch);
            pst.execute();
            System.out.println("info Submited Succesfully");
            System.out.println("******************************");
     // ResultSet rs = st.executeQuery(query);

        } catch (Exception e) {

            System.out.println(e);
        }
    }

    public void show() {

        String query = "select*from data";
        try {
            //2nd step
            Class.forName("com.mysql.jdbc.Driver");
            // 3rd step
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/student", "root", "");
            // 4th step
            Statement st = con.createStatement();

     //  Statement st1=con.createStatement();
            //  st.setString(1,names);
            // 5th step 
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                //Retrieve by column name
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String roll = rs.getString("roll");
                String course = rs.getString("course");
                String branch = rs.getString("branch");

     //* String name= rs.getString(1);
                // String roll=rs.getString(2);
                //  String course= rs.getString(3);
                // String branch=rs.getString(4);
                System.out.println("*******************************");
                System.out.println("Enrollment id is:" + id);
                System.out.println("name is:" + name);
                System.out.println("Roll Number is:" + roll);
                System.out.println("Course is:" + course);
                System.out.println("branch is:" + branch);

                System.out.println("##########################################");
                System.out.println("      ");

            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);

        }

    }

    public void delete() {
        Scanner sc = new Scanner(System.in);
       //System.out.println("Enter name which want to delete: ");
        //int ids=sc.nextInt();
        //String delete="delete from data where id=" + ids;
        System.out.println("******************************************");

        System.out.println("Enter roll number which you want to delete");
        String rollnumber = sc.nextLine();
        String delete = "DELETE FROM data WHERE roll=" + rollnumber + "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/student", "root", "");
            PreparedStatement scs = con.prepareStatement(delete);
            scs.executeUpdate();
            System.out.println("deleted succesfully");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void update() {

        String uname, ucourse, ubranch;
        String uroll;
        Scanner sc = new Scanner(System.in);
        String ids;
        System.out.println("Enter id which  want to update");
        ids = sc.nextLine();
        System.out.println("Enter roll Number which  want to update");
        uroll = sc.nextLine();
        System.out.println(" ");
        System.out.println("Enter name which  want to update");
        uname = sc.nextLine();
        System.out.println("Enter course which  want to update");
        ucourse = sc.nextLine();
        System.out.println("Enter branch which  want to update");
        ubranch = sc.nextLine();
        System.out.println("data updated succefully");
        String update = "Update data Set name=? ,roll=?,course=?,branch=? where id=" + ids;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/student", "root", "");
            PreparedStatement scs = con.prepareStatement(update);
            scs.setString(1, uname);
            scs.setString(2, uroll);
            scs.setString(3, ucourse);
            scs.setString(4, ubranch);
            scs.executeUpdate();
            System.out.println("deleted succesfully");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void signup() {
        System.out.println(" User Registration");
        String name, email;
        String password, cpassword;
        String phone;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your Name:");
        name = sc.nextLine();
        System.out.println("Enter your Email id:");
        email = sc.nextLine();
        System.out.println("Enter your Phone:");
        phone = sc.nextLine();
        System.out.println("Enter your Password:");
        password = sc.nextLine();
        System.out.println("Enter your ConfirmPassword:");
        cpassword = sc.nextLine();
        System.out.println("####################");

        String query = "insert into user(name,email,phone,password,cpassword) Value(?,?,?,?,?)";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/student", "root", "");
            PreparedStatement sm = con.prepareStatement(query);
            sm.setString(1, name);
            sm.setString(2, email);
            sm.setString(3, phone);
            sm.setString(4, password);
            sm.setString(5, cpassword);
            if (password.equals(cpassword)) {
                sm.execute();
                System.out.println("USer registration succesfully !");
            } else {
                System.out.println("Password are not equle to the confirm password");
                signup();

            }

            System.out.println("***************************");

        } catch (Exception e) {
            System.out.println(e);
        }

    }

}

public class Database {

    public static void main(String[] args) {

        info in = new info();

        in.admin();

    }

}
