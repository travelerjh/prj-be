package com.example.prj1be.controller;


import com.example.prj1be.domain.Board;
import com.example.prj1be.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor

public class BoardController {

    //fianl ==> 스프링이 생성자를 만들어라 라는 의미
    private final BoardService service;

    //json으로 받는게 requestBody
    @PostMapping("add")
    public ResponseEntity add(@RequestBody Board board){
        System.out.println("board = " + board);


        if (service.save(board)){
            ResponseEntity.ok().build();
        }else {
            ResponseEntity.internalServerError().build();
        }

    };


}
