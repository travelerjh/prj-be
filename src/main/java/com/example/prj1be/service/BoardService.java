package com.example.prj1be.service;

import com.example.prj1be.Mapper.BoardMapper;
import com.example.prj1be.domain.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

//특별한 컴포넌트
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper mapper;

    public boolean save(Board board) {
      return mapper.insert(board) ==1;
    }


    // 검증하는거
    public boolean validate(Board board) {
        if(board ==null){
            return false;
        }if (board.getContent()==null||board.getContent().isBlank()){
            return false;
        }if (board.getTitle()==null||board.getTitle().isBlank()){
            return  false;
        }if (board.getWriter()==null ||board.getWriter().isBlank()){
            return false;
        }
        return true;

    }

    public List<Board> list() {
        return  mapper.selectAll();
    }

    public Board get(Integer id) {
        return  mapper.selectById(id);
    }

    public boolean remove(Integer id) {
       return mapper.deleteById(id)==1;
    }


    public void update(Board board) {
        mapper.update(board);
    }
}
