package com.fh;

import com.fh.domain.Student;
import com.fh.mapper.StudentMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot07CacheApplicationTests {

    @Autowired
    StudentMapper studentMapper;

    /**
     * redis常见操作数据类型
     * 字符串 opsForValue()
     * list opsForList()
     * set	opsForSet()
     * hash	opsForHash()
     * zset	opsForZSet()
     */
    @Autowired
    StringRedisTemplate stringRedisTemplate;// 操作k-v 都是字符串

    @Autowired
    RedisTemplate redisTemplate;// 操作k-v 都是对象

    @Autowired
    RedisTemplate<Object, Student> studentRedisTemplate;

    @Test
    public void contextLoads() {
        stringRedisTemplate.opsForValue().append("msg", "你好");
    }

    @Test
    public void test01() {
        System.out.println(stringRedisTemplate.opsForValue().get("msg"));
    }

    @Test
    public void test02() {
        redisTemplate.opsForValue().set("stu01", studentMapper.getStudentsByName("张三"));
        System.out.println(redisTemplate.opsForValue().get("stu01"));
    }

    @Test
    public void test03() {
        studentRedisTemplate.opsForValue().set("stu01", studentMapper.getStudentsByName("张三"));
        System.out.println(studentRedisTemplate.opsForValue().get("stu01"));
    }
}
