package com.example.prj1be.service;

import com.example.prj1be.Mapper.MemberMapper;
import com.example.prj1be.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper mapper;

     public void add(Member member){
        mapper.insert(member);
    }


    public String getId(String id) {
        return mapper.selectId(id);
     }

    public String getEmail(String email) {
        return mapper.selectEmail(email);

    }
}
