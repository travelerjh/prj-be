package com.example.prj1be.controller;


import com.example.prj1be.domain.Board;
import com.example.prj1be.domain.Member;
import com.example.prj1be.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor

public class BoardController {

    //fianl ==> 스프링이 생성자를 만들어라 라는 의미
    private final BoardService service;

    //json으로 받는게 requestBody
    @PostMapping("add")
    public ResponseEntity add(@RequestBody Board board,
                              @SessionAttribute(value="login",required=false) Member login      ) {

        if(login==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }


        System.out.println("login = " + login);
        if(!service.validate(board)){
            return ResponseEntity.badRequest().build();
        }


        if (service.save(board , login)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("list")
    private List<Board> list(){
        return service.list();
    }

    @GetMapping("id/{id}")
    public Board get(@PathVariable Integer id){
        return service.get(id);
    }

    @DeleteMapping("remove/{id}")
    public ResponseEntity remove(@PathVariable Integer id){
        if (service.remove(id)){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("edit")
    public ResponseEntity edit(@RequestBody Board board) {
//        System.out.println("board = " + board);
      if (service.validate(board)){
        if ( service.update(board)){
          return ResponseEntity.ok().build();}else {
          return ResponseEntity.internalServerError().build();
        }}else {
          return ResponseEntity.badRequest().build();
      }
    }

}
