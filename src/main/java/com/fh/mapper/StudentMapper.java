package com.fh.mapper;

import com.fh.domain.Student;
import com.fh.domain.Teacher;
import org.apache.ibatis.annotations.Select;

import java.util.List;

// 标明是一个mapper
//@Mapper
public interface StudentMapper {

    List<Student> getAllStudents();

    Student getStudentsByName(String name);

    @Select("select * from teacher where name=#{name}")
    Teacher getTeacherByName(String name);

    int update(String name);

    int del(String name);

    int insert(String name);
}
