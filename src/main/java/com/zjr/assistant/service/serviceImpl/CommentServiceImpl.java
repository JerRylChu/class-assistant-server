package com.zjr.assistant.service.serviceImpl;

import com.zjr.assistant.entities.Comment;
import com.zjr.assistant.mapper.CommentMapper;
import com.zjr.assistant.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public boolean addComment(Comment comment) {
        return commentMapper.addComment(comment) > 0;
    }

    @Override
    public List<Comment> getCommentsByArticleId(Integer id) {
        return commentMapper.getCommentsByArticleId(id);
    }

    @Override
    public boolean delComment(Integer id) {
        return commentMapper.delComment(id)>0;
    }
}
