package com.example.prj1be.controller;

import com.example.prj1be.domain.Member;
import com.example.prj1be.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService service;

    @PostMapping("signup")
    public ResponseEntity signup(@RequestBody Member member) {
        if (service.validate(member)) {
            if (service.add(member)) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.internalServerError().build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping(value = "check", params = "id")
    public ResponseEntity checkId(String id) {
        if (service.getId(id) == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().build();
        }
    }

    @GetMapping("list")
    public List<Member> list(){
        return service.list();
    }







    @GetMapping(value = "check", params = "email")
    public ResponseEntity checkEmail(String email) {
        if (service.getEmail(email) == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().build();
        }
    }



    @GetMapping()
    public  ResponseEntity<Member>  view(String id) {
       Member member = service.getMember(id);
        return  ResponseEntity.ok(member);
    }

    @DeleteMapping
    public ResponseEntity delete(String id){
        // TODO: 로그인했는지?  --> 안했으면 401
        // TODO: 자기정보인지?  --> 아니면 403
        if(service.deleteid(id))
        {return ResponseEntity.ok().build();}
        else {
            return ResponseEntity.badRequest().build();
        }

    }

    @PutMapping("edit")
    public ResponseEntity edit(@RequestBody Member member){
        // TODO : 로그인 했는지  ? 자기정보인지 ?
        if(service.update(member)){
            return  ResponseEntity.ok().build();
        }else {
            return ResponseEntity.internalServerError().build();
        }
    }



    //웹리쿼스트 안에 세션에 어트리뷰트를 추가할수 있따.

    @PostMapping("login")
    public ResponseEntity login(@RequestBody Member member, WebRequest request){
        if(service.login(member,request)){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.status(401).build();
        }

    }



    @PostMapping("logout")
    public void logout(HttpSession session){
        if(session!=null){
            session.invalidate();
        }
    }




}