package com.example.kathyxu.googlesheetsapi;

import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

/**
 * Created by kathy on 3/10/2016.
 */
public class StudentAccess implements DbAccess{
    private final StudentContract studentContract;

    public StudentAccess(SQLiteOpenHelper sqLiteOpenHelper) {
        this.studentContract = new StudentContract(sqLiteOpenHelper);
    }

    //Example of overriding interface
    @Override
    public List<Student> getAll() {
        return studentContract.getStudents();
    }

    //Example standard contract methods
    public void insertStudent(Student stu){
        studentContract.insert(stu);
    }

    public void deleteStudent(int id){
        studentContract.delete(id);
    }

    //Example of extra methods
    public void insertStudents(List<Student> students){
        for(Student stu : students){
            studentContract.insert(stu);
        }
    }

    public void deleteStudents(int... ids){
        for(int id : ids){
            studentContract.delete(id);
        }
    }
    public Student getStudent(String number){
        return studentContract.getStudent(Integer.parseInt(number));
    }

    public boolean hasStu(int id){
        return studentContract.getStudent(id) != null;
    }
}
