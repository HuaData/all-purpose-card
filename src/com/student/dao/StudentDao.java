package com.student.dao;


import com.student.obj.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {

    public int addStudent(Student student){
        int rows;
        try {
            //加载驱动包
            Class.forName("com.mysql.cj.jdbc.Driver");
            //链接数据库
            String url = "jdbc:mysql://localhost:3306/studentdb?useUnicode=true&characterEncoding=utf-8";
            String user = "root";
            String password = "root";
            Connection conn = DriverManager.getConnection(url,user,password);
            //运行数据库语句
            PreparedStatement ps = conn.prepareStatement("insert into students values(null,?,?,?)");
            ps.setString(1,student.getName());
            ps.setInt(2,student.getAge());
            ps.setString(3,student.getGender());
            //返回结果
            rows = ps.executeUpdate();
            //断开链接
            ps.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return rows;
    }

    public List<Student> selectStudents(String name){
        ArrayList<Student> students = new ArrayList<Student>();
        try {
            //加载驱动包
            Class.forName("com.mysql.cj.jdbc.Driver");

            //链接数据库
            String url = "jdbc:mysql://localhost:3306/studentdb?useUnicode=true&characterEncoding=utf-8";
            String user = "root";
            String password = "root";
            Connection conn = DriverManager.getConnection(url,user,password);
            //运行数据库语句
            PreparedStatement ps = conn.prepareStatement("select * from students where name=?");
            ps.setString(1,name);
            //将数据存入list集合
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Student student = new Student();
                student.setId(rs.getInt(1));
                student.setName(rs.getString(2));
                student.setAge(rs.getInt(3));
                student.setGender(rs.getString(4));
                students.add(student);
            }
            //断开连接
            rs.close();
            ps.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    public List<Student> selectAllStudents(){
        ArrayList<Student> students = new ArrayList<Student>();
        try {
            //加载驱动包
            Class.forName("com.mysql.cj.jdbc.Driver");

            //链接数据库
            String url = "jdbc:mysql://localhost:3306/studentdb?useUnicode=true&characterEncoding=utf-8";
            String user = "root";
            String password = "root";
            Connection conn = DriverManager.getConnection(url,user,password);
            //运行数据库语句
            PreparedStatement ps = conn.prepareStatement("select * from students");
            //将数据存入list集合
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                students.add(new Student(rs.getInt("student_id"),rs.getString("name"),rs.getInt("age"),rs.getString("gender")));
            }
            //断开连接
            rs.close();
            ps.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    public int updateStudent(Student student){
        int rows;
        try {
            //加载驱动包
            Class.forName("com.mysql.cj.jdbc.Driver");
            //链接数据库
            String url = "jdbc:mysql://localhost:3306/studentdb?useUnicode=true&characterEncoding=utf-8";
            String user = "root";
            String password = "root";
            Connection conn = DriverManager.getConnection(url,user,password);
            //运行数据库语句
            PreparedStatement ps = conn.prepareStatement("update students set name=?,age=?,gender=? where student_id=?");
            ps.setString(1,student.getName());
            ps.setInt(2,student.getAge());
            ps.setString(3,student.getGender());
            ps.setInt(4,student.getId());

            rows = ps.executeUpdate();

            ps.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return rows;
    }

    public int deleteStudent(int id){
        int rows;
        try {
            //加载驱动包
            Class.forName("com.mysql.cj.jdbc.Driver");
            //链接数据库
            String url = "jdbc:mysql://localhost:3306/studentdb?useUnicode=true&characterEncoding=utf-8";
            String user = "root";
            String password = "root";
            Connection conn = DriverManager.getConnection(url,user,password);
            //运行数据库语句
            PreparedStatement ps = conn.prepareStatement("delete from students where student_id=?");
            ps.setInt(1,id);
            //返回结果
            rows = ps.executeUpdate();
            //断开链接
            ps.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return rows;
    }
}
