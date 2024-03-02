package com.zjr.assistant.service;

import com.zjr.assistant.entities.Comment;

import java.util.List;

public interface CommentService {
    boolean addComment(Comment comment);
    List<Comment> getCommentsByArticleId(Integer id);
    boolean delComment(Integer id);

}
