package com.example.prj1be.service;

import com.example.prj1be.Mapper.MemberMapper;
import com.example.prj1be.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper mapper;

     public boolean add(Member member){
        mapper.insert(member);
         return false;
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
        return  mapper.selectAll();
     }

    public Member getMember(String id) {
      return   mapper.selectById(id);
     }

    public boolean deleteid(String id) {
        return  mapper.deletebtId(id)==1;
     }
}
