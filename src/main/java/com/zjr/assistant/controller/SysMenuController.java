package com.zjr.assistant.controller;

import com.zjr.assistant.entities.Menu;
import com.zjr.assistant.service.serviceImpl.SysMenuServiceImpl;
import com.zjr.assistant.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "个人中心菜单相关")
@RestController
@RequestMapping("/sysMenu")
public class SysMenuController {
    @Autowired
    private SysMenuServiceImpl sysMenuService;

    @ApiOperation("获取个人中心菜单")
    @GetMapping("/getMenu")
    public Result getMenu(){
        try{
            List<Menu> sysMenu = sysMenuService.getSysMenu();
            return new Result(sysMenu,200,"");
        }catch (Exception e){
            System.out.println(e);
            return new Result(null,500,"获取导航栏数据失败");
        }
    }
}
