package com.example.prj1be.Mapper;

import com.example.prj1be.domain.Board;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BoardMapper {

    //insert일 경우 반환 값은 int로 넣어줘라
    @Insert("""
       insert into board (title,content,writer)
       value (#{title},#{content},#{writer})
        """)

    int insert(Board board);

    @Select(
            """
                                    select id,title,writer,inserted from prj1.board order by  id desc ;
    """
    )
    List<Board> selectAll();

    @Select("""
                select * from prj1.board where id= #{id};
        """)
    Board selectById(Integer id);

    @Delete("""
                delete from prj1.board where id =#{id};
        """)
    int deleteById(Integer id);

    //업데이트 인서트 딜리트  int 로
    //나머지는 select는 필요한 dto --> list , map , javabean

}
