package com.student.ui;

import com.student.dao.StudentDao;
import com.student.obj.Student;

import java.util.List;
import java.util.Scanner;

public class Demo01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("=====学生信息管理系统=====");
            System.out.println("1、添加学生信息");
            System.out.println("2、根据姓名查询学生信息");
            System.out.println("3、更新学生信息");
            System.out.println("4、删除学生信息");
            System.out.println("5、查询所有学生的信息");
            System.out.println("6、退出系统");
            int choice = sc.nextInt();
            int rows;
            switch (choice) {
                case 1:
                    Student student = new Student();
                    System.out.println("请输入姓名：");
                    student.setName(sc.next());
                    System.out.println("请输入年龄：");
                    student.setAge(sc.nextInt());
                    System.out.println("请输入性别：");
                    student.setGender(sc.next());
                    //调用dao方法
                    StudentDao studentDao = new StudentDao();
                    int flag = studentDao.addStudent(student);
                    if (flag > 0) {
                        System.out.println("学生添加成功");
                    } else {
                        System.out.println("学生添加失败");
                    }
                    break;
                case 2:
                    StudentDao studentDao2 = new StudentDao();
                    System.out.println("请输入姓名：");
                    String studentName = sc.next();

                     List<Student> students = studentDao2.selectStudents(studentName);
                    if (students.size() > 0) {
                        for (int i = 0; i < students.size(); i++) {
                            System.out.println(students.get(i));
                        }
                    } else {
                        System.out.println("未找到！");
                    }
                    break;
                case 3:
                    StudentDao studentDao3 = new StudentDao();
                    Student student2 = new Student();
                    System.out.println("请输入学号：");
                    student2.setId(sc.nextInt());
                    System.out.println("请输入姓名：");
                    student2.setName(sc.next());
                    System.out.println("请输入年龄：");
                    student2.setAge(sc.nextInt());
                    System.out.println("请输入性别：");
                    student2.setGender(sc.next());
                    rows = studentDao3.updateStudent(student2);

                    if (rows > 0) {
                        System.out.println("修改成功");
                    } else {
                        System.out.println("未找到此人");
                    }
                    break;
                case 4:
                    StudentDao studentDao4 = new StudentDao();
                    System.out.println("请输入学生学号：");
                    int id = sc.nextInt();
                    rows = studentDao4.deleteStudent(id);
                    if (rows > 0) {
                        System.out.println("删除成功！");
                    } else {
                        System.out.println("未找到学生信息");
                    }
                    break;
                case 5:
                    StudentDao studentDao5 = new StudentDao();
                    List<Student> stuAll = studentDao5.selectAllStudents();
                    if (stuAll.size() > 0) {
                        for (int i = 0; i < stuAll.size(); i++) {
                            System.out.println(stuAll.get(i));
                        }
                    } else {
                        System.out.println("未找到信息");
                    }
                    break;
                case 6:
                    return;
                default:
                    System.out.println("请输入正确数字");
                    break;
            }
        }
    }
}