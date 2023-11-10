package com.example.prj1be.Mapper;

import com.example.prj1be.domain.Member;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MemberMapper {

    @Insert("""
        INSERT  into member(id,password,email)
        values (#{id},#{password},#{email})
    """)
    int insert(Member member);

    @Select("""
                    select id from prj1.member where id =#{id};
    """)
    String selectId(String id);
}
