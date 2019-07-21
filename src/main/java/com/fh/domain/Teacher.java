package com.fh.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Teacher implements Serializable{
    private String name;
    private String number;
    private String age;
    private String sex;
}
