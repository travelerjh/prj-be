package com.example.prj1be.domain;


import lombok.Data;

@Data
public class Board {
    private  String id;
    private String title;
    private String content;
    private String writer;
    private String inserted;
}
