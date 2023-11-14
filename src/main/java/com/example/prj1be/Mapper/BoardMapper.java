package com.example.prj1be.Mapper;

import com.example.prj1be.domain.Board;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface BoardMapper {

    @Insert("""
        INSERT INTO board (title, content, writer)
        VALUES (#{title}, #{content}, #{writer})
        """)
    int insert(Board board);

    @Select("""
        SELECT b.id, b.title, m.nickname, b.writer, b.inserted 
        FROM board b join member m ON b.writer=m.id
        ORDER BY b.id DESC
        """)
    List<Board> selectAll();

    @Select("""
        SELECT b.id, b.title, b.content, b.writer, b.inserted , m.nickname
        FROM board b join member m on b.writer = m.id
        WHERE b.id = #{id}
        """)
    Board selectById(Integer id);

    @Delete("""
        DELETE FROM board
        WHERE id = #{id}
        """)
    int deleteById(Integer id);

    @Update("""
        UPDATE board
        SET title = #{title},
            content = #{content},
            writer = #{writer}
        WHERE id = #{id}
        """)
    int update(Board board);


    @Delete("""
            delete from board where #{writer};
            """)
    int deleteByWriter(String writer);


}
