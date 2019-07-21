package com.fh.service;

import com.fh.domain.Student;
import com.fh.domain.Teacher;
import com.fh.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    public List<Student> getAllStudents(){
        return studentMapper.getAllStudents();
    }

    // 将方法运行的结果进行缓存

    /**
     * cacheNames 指定缓存的名字，可以是数组的方式
     * key缓存数据使用的key，默认是方法参数的值，也可以通过spel进行设置
     * keyGenerator key的生成器，可以自己指定key的生成器组件id 他和key二选一使用
     * cacheManager 指定缓存管理器
     * cacheResolver 指定缓存解析器 和cacheManager二选一使用
     * condition 当指定情况时缓存 可以使用spel
     * unless 满足条件时不缓存 可以使用spel
     * sync 是否使用异步
     * @param name
     * @return
     */
    @Cacheable(cacheNames = {"student"})
    public Student getStudentsByName(String name){
        System.out.println("查询"+name+"数据");
        return studentMapper.getStudentsByName(name);
    }

    @Cacheable(cacheNames = {"teacher"})
    public Teacher getTeacherByName(String name){
        System.out.println("查询"+name+"数据");
        return studentMapper.getTeacherByName(name);
    }

    /**
     * @CachePut 执行时机:在方法调用之后将结果缓存
     * @param name
     * @return
     */
    @CachePut(cacheNames = "student")
    public int update(String name){
        return studentMapper.update(name);
    }

    /**
     * value/cacheNames 需要清空的缓存，与@Cacheable中的cacheNames对应
     * key 需要清空的key
     * allEntries 是否全部清空
     * beforeInvocation 执行时机，默认为false表示方法执行完后执行，如果为
     * true表示方法执行前执行，这样如果方法中发生异常时会出现问题
     * @param name
     * @return
     */
    @CacheEvict(cacheNames = "emp")
    public int del(String name){
        return studentMapper.del(name);
    }

    public Student insert(String name){
        studentMapper.insert(name);
        return studentMapper.getStudentsByName(name);
    }
}
