package com.fh.controller;

import com.fh.domain.Student;
import com.fh.domain.Teacher;
import com.fh.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @RequestMapping("getAll")
    public List<Student> getAll() {
        List<Student> list = studentService.getAllStudents();
        return list;
    }

    @RequestMapping("getByName")
    public Student getById(@RequestParam("name") String name) {

        return studentService.getStudentsByName(name);
    }

    @RequestMapping("insert")
    public Student insert(@RequestParam("name") String name) {
        return studentService.insert(name);
    }

    @RequestMapping("del")
    public int del(@RequestParam("name") String name) {
        return studentService.del(name);
    }

    @RequestMapping("update")
    public int update(@RequestParam("name") String name) {
        return studentService.update(name);
    }

    @RequestMapping("getTeacherByName")
    public Teacher getTeacherByName(@RequestParam("name") String name) {

        return studentService.getTeacherByName(name);
    }

}
