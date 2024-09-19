package org.goorm.everytime.board.service;

import lombok.RequiredArgsConstructor;
import org.goorm.everytime.board.api.dto.AllBoardDto;
import org.goorm.everytime.board.api.dto.BoardDto;
import org.goorm.everytime.board.domain.repository.BoardsRespository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostProvider {

    private final BoardsRespository boardsRespository;

    //개별 게시판 조회
    public AllBoardDto providePostsOfBoard(Long boardId) {
        return new AllBoardDto(List.of(boardsRespository.findBoardNameByBoardId(boardId)));
    }

    //전체 게시판 조회
    public AllBoardDto provideAllBoard() {
        return boardsRespository.findAllBoard();
    }
}
