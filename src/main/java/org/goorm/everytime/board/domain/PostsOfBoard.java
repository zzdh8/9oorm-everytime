package org.goorm.everytime.board.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PostsOfBoard {

    private final List<Post> posts = new ArrayList<>();

    public List<Post> getPosts() {
        return Collections.unmodifiableList(posts);
    }

    public int size() {
        return posts.size();
    }
}