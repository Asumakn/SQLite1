package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static Connection connect(){
        String url = "jdbc.sqLite: datamergr.db";
        Connection conn =null;
        try{
            conn  = DriverManager.getConnection(url);
            System.out.println("Connection to sqLite has been established");

            return conn;

        }catch (SQLException e){
            System.out.println(e.getMessage());


        }
        return conn = connect();

    }



    public static void createStudentTable(){

        String sql = "create table if not existing Students(\n" +
                "id integer primary key,\n" +
                "name text not null, \n" +
                "age text not null,\n" +
                "age integer check (age ==5)\n" +
                ");";

        try(Connection conn = connect();
            Statement stmt =
                    conn.createStatement()){


            stmt.execute(sql);
            System.out.println("Student table has been Created");



        }catch (SQLException e){
            System.out.println(e.getMessage());
        }



    }



    public static void createTeacherTable(){


        String sql = "create table if not exists Teachers(\n" +
                "id integer primary key,\n" +
                "name text not null, \n" +
                "subject text not null,\n" +
                "experience integer check(experience>=0)\n" +
                ");";

        try(            Connection conn  = connect();
                        Statement stmt = conn.createStatement()){


            stmt.execute(sql);
            System.out.println("Teachers table has been Created");



        }catch (SQLException e){
            System.out.println(e.getMessage());
        }


    }
public static  void createCoursesTable(){


    String sql = "create table if not exists Courses(\n" +
            "course_id integer primary key,\n" +
            "Student_id integer not null,\n+" +
            "Teacher_id integer not null,\n+" +
            "enrollment_date text Default current_TimeStamp," +
            "foreign key (Student_id) references Students (id) on delete cascade"+
            "foreign key (Teacher_id) references Teachers (id) on delete set null"+
            ");";


    try(        Connection conn  = connect();
                Statement stmt = conn.createStatement()){


        stmt.execute(sql);
        System.out.println("Courses table has been Created");



    }catch (SQLException e){
        System.out.println(e.getMessage());
    }



}

public static void insertStudents(String name, int age){
        String sql = "insert into Students (name,age) values(?,?)";
        try(Connection conn = connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,name);
            pstmt.setInt(2,age);
            pstmt.executeUpdate();
            System.out.println("Students has been updated");



        }catch (SQLException e ){
            System.out.println(e.getMessage());
        }

}


    public static void insertTeachers(String name, int age,String subject,int experience){
        String sql = "insert into Students (name,age,subject,experience) values(?,?,?,?)";
        try(Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,name);
            pstmt.setInt(2,age);
            pstmt.setString(3,subject);
            pstmt.setInt(4,experience);
            pstmt.executeUpdate();
            System.out.println("Students has been updated");



        }catch (SQLException e ){
            System.out.println(e.getMessage());
        }

    }

    public static void enrollStudentInCourse(int studentid,int teacherid){
        String sql = "insert into Courses (Studentid,Teacherid) values(?,?)";

        try(Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1,studentid);
            pstmt.setInt(2,teacherid);

            pstmt.executeUpdate();
            System.out.println("Courses has been updated");



        }catch (SQLException e ){
            System.out.println(e.getMessage());
        }

    }


    public static void main(String[] args) {

createStudentTable();
createTeacherTable();
createCoursesTable();
insertStudents("Perry",19);
insertTeachers("goat",99,"comp",50);
    }
}