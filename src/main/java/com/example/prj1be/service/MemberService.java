package com.example.prj1be.service;

import com.example.prj1be.Mapper.BoardMapper;
import com.example.prj1be.Mapper.MemberMapper;
import com.example.prj1be.domain.Auth;
import com.example.prj1be.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper mapper;
    private  final BoardMapper boardmapper;

    public boolean add(Member member) {
        return mapper.insert(member) == 1;
    }

    public String getId(String id) {
        return mapper.selectId(id);
    }

    public String getEmail(String email) {
        return mapper.selectEmail(email);

    }

    public boolean validate(Member member) {
        if (member == null) {
            return false;
        }

        if (member.getEmail().isBlank()) {
            return false;
        }

        if (member.getPassword().isBlank()) {
            return false;
        }

        if (member.getId().isBlank()) {
            return false;
        }
        return true;
    }

    public List<Member> list() {
        return mapper.selectAll();
    }

    public Member getMember(String id) {
        return mapper.selectById(id);
    }


    public boolean deleteMember(String id) {
        //작성 한 게시물 삭제
       boardmapper.deleteByWriter(id);
        // 맴버 삭제

        return mapper.deleteById(id) == 1;
    }

    public boolean update(Member member) {
//        Member oldMember = mapper.selectById(member.getId());
//
//        if (member.getPassword().equals("")) {
//            member.setPassword(oldMember.getPassword());
//        }

        return mapper.update(member) == 1;

    }

    public String getNickName(String nickName) {
        return mapper.selectNickName(nickName);
    }

    public boolean login(Member member, WebRequest request) {
        Member dbMember = mapper.selectById(member.getId());

        List<Auth> auth =mapper.selectAuthById(member.getId());
        dbMember.setAuth(auth);

        if (dbMember != null) {
            if (dbMember.getPassword().equals(member.getPassword())) {
                dbMember.setPassword("");
                request.setAttribute("login", dbMember, RequestAttributes.SCOPE_SESSION);
                return true;
            }
        }

        return false;
    }


    public boolean hasAccess(String id, Member login) {
        return login.getId().equals(id);
    }
}
