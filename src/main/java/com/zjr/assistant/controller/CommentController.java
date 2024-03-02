package com.zjr.assistant.controller;

import com.zjr.assistant.entities.Comment;
import com.zjr.assistant.service.serviceImpl.CommentServiceImpl;
import com.zjr.assistant.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentServiceImpl commentService;

    @RequestMapping(value = "/addComment",method = RequestMethod.POST)
    public Result addComment(@RequestBody Comment comment){
        comment.setCreateTime(new Date());
        if(commentService.addComment(comment)){
            return new Result(null,200,"评论成功");
        }else {
            return new Result(null,500,"评论失败");
        }
    }

    @RequestMapping(value = "/getComments",method = RequestMethod.GET)
    public Result getCommentsByArticleId(Integer id){
        try {
            return new Result(commentService.getCommentsByArticleId(id),200,"获取成功");
        }catch (Exception e){
            System.out.println(e);
            return new Result(null,500,"获取失败");
        }
    }

    @RequestMapping(value = "/delComment",method = RequestMethod.GET)
    public Result delComment(Integer id){
        if(commentService.delComment(id)){
            return new Result(null,200,"删除成功");
        }else {
            return new Result(null,500,"删除失败");
        }
    }
}
