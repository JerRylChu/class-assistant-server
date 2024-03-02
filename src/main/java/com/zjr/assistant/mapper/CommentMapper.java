package com.zjr.assistant.mapper;

import com.zjr.assistant.entities.Comment;

import java.util.List;

public interface CommentMapper {
    int addComment(Comment comment);
    List<Comment> getCommentsByArticleId(Integer id);
    int delComment(Integer id);
}
