package com.example.admin.quanlysinhvien_demo;

/**
 * Created by Admin on 6/30/2016.
 */
public class Student {
    private int student_id;
    private String name;
    private String email;
    private int age;
    private int sdt;

    public Student(int student_id, String name, String email, int age, int sdt) {
        this.student_id = student_id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.sdt = sdt;
    }

    public Student(int id, String s, String toString, int i) {
    }

    public Student(String s, String s1, int i) {
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSdt() {
        return sdt;
    }

    public void setSdt(int sdt) {
        this.sdt = sdt;
    }
}
