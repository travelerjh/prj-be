package com.example.prj1be.Mapper;

import com.example.prj1be.domain.Member;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MemberMapper {

    @Insert("""
        INSERT  into member(id,password,nickname,email)
        values (#{id},#{password},#{nickname},#{email})
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
                select id, password, email, nickname, inserted from prj1.member order by inserted desc ;
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

    @Update(
            """
            update member 
            set
             password=#{password},nickname=#{nickname},email=#{email}  
            where id=#{id};
            """
    )

    int update(Member member);

}
