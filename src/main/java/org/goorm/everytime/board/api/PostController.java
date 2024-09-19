package org.goorm.everytime.board.api;

import lombok.RequiredArgsConstructor;
import org.goorm.everytime.board.service.PostProvider;
import org.goorm.everytime.global.common.dto.BaseResponse;
import org.goorm.everytime.global.common.exception.SuccessCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostProvider postProvider;

    @GetMapping("/boards/{boardId}")
    public BaseResponse providePostsOfBoard(@PathVariable Long boardId) {
        return BaseResponse.success(SuccessCode.GET_POSTS_OF_BOARD, postProvider.providePostsOfBoard(boardId));
    }

    @GetMapping("/boards")
    public BaseResponse provideAllBoard() {
        return BaseResponse.success(SuccessCode.GET_ALL_BOARD, postProvider.provideAllBoard());
    }
}
