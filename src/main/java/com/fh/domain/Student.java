package com.fh.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Student implements Serializable {
    private Integer id;
    private String name;
    private String age;
    private String sex;
    private String address;
    private String phone;
}
