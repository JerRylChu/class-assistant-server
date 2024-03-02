package com.zjr.assistant.controller;

import com.zjr.assistant.entities.User;
import com.zjr.assistant.service.serviceImpl.FileServiceImpl;
import com.zjr.assistant.service.serviceImpl.UserServiceImpl;
import com.zjr.assistant.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@Api(tags = "文件上传相关")
@RestController
@RequestMapping("/file")
@CrossOrigin
public class FileController {
    @Autowired
    private FileServiceImpl fileService;
    @Autowired
    private UserServiceImpl userService;

    /**
     * 上传图片
     * @param file
     * @return
     */
    @ApiOperation("相册上传单个照片")
    @RequestMapping("/imgUpload")
    public Result imgUpload(@ApiParam(value = "文件流",required = true) MultipartFile file){
        Result result = fileService.imgUpload(file);
        return result;
    }

    @ApiOperation("下载相册照片")
    @RequestMapping("/imgDownload/{fileName}")
    public Result ImgDownload(@PathVariable("fileName") String fileName, HttpServletResponse response){
        fileService.imgDownload(fileName,response);
        return null;
    }

    @ApiOperation("文章中图片的上传")
    @RequestMapping("/articleImgUpload")
    public Result articleImgUpload(@RequestParam("file") @ApiParam("文章图片文件") MultipartFile file){
        Result result = fileService.articleImgUpload(file);
        return result;
    }

    @ApiOperation("文章中图片的下载")
    @RequestMapping("/articleImgDownload/{fileName}")
    public Result articleImgDownload(@PathVariable("fileName") @ApiParam(value = "图片的名字") String fileName, HttpServletResponse response){
        fileService.articleImgDownload(fileName,response);
        return null;
    }

    @RequestMapping("/avatar/{id}")
    public Result changeAvatar(MultipartFile file, @PathVariable("id")Integer id){
        try {
            Result result = fileService.avatarUpload(file);
            User user = new User();
            user.setId(id);
            user.setHeadIconUrl(result.getData().toString());
            userService.updateAvatar(user);
            return result;
        }catch (Exception e){
            System.out.println("头像上传报错"+e);
            return new Result(e,500,"");
        }
    }

    @RequestMapping("/avatarDownload/{fileName}")
    public Result avatarDownload(@PathVariable("fileName") String fileName, HttpServletResponse response){
        try {
            fileService.avatarDownload(fileName,response);
            return null;
        }catch (Exception e){
            System.out.println("头像下载报错"+e);
            return null;
        }
    }
}
