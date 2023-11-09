package com.example.prj1be.service;

import com.example.prj1be.Mapper.BoardMapper;
import com.example.prj1be.domain.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

//특별한 컴포넌트
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper mapper;

    public boolean save(Board board) {
      return mapper.insert(board) ==1;
    }
}
