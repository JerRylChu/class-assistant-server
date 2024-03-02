package com.zjr.assistant.controller;

import com.zjr.assistant.entities.TypeTree;
import com.zjr.assistant.service.serviceImpl.TypeTreeServiceImpl;
import com.zjr.assistant.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.geom.RectangularShape;
import java.util.Date;
import java.util.List;

@Api(tags = "照片分类目录相关")
@RestController
@RequestMapping("/type")
public class TypeTreeController {
    @Autowired
    private TypeTreeServiceImpl typeTreeService;

    @ApiOperation("获取分类")
    @RequestMapping("/getTypeTree")
    public Result getTypeTree(@ApiParam(value = "用户id", required = true) int id){
        try {
            List<TypeTree> typeTree = typeTreeService.getTypeTree(id);
            return new Result(typeTree,200,"查找成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(null,500,"查询失败");
        }
    }

    @ApiOperation("新增分类")
    @PostMapping("/addType")
    public Result addType(@RequestBody @ApiParam(value = "分类信息",required = true) TypeTree typeTree){
        try {
            typeTree.setCreateTime(new Date());
            if(typeTreeService.addType(typeTree)){
                return new Result(null,200,"添加成功");
            }else {
                return new Result(null,500,"添加失败");
            }
        }catch (Exception e){
            return new Result(null,500,"添加失败");
        }
    }
}
