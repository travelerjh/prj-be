package com.example.prj1be.Mapper;

import com.example.prj1be.domain.Member;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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

    @Select("""
                   select email from member where email=#{email};
     """)
    String selectEmail(String email);

    @Select("""
                select * from prj1.member order by inserted desc ;
        """)
    List<Member> selectAll();

    @Select("""
                select * from prj1.member where id=#{id};
        """)
    Member selectById(String id);

    @Delete("""
                    delete from member where id=#{id};
    """)
    int deletebtId(String id);
}
