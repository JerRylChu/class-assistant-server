package com.zjr.assistant.service;

import com.zjr.assistant.utils.Result;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface FileService {
    String toolBoxUpload(MultipartFile file);
    Result avatarUpload(MultipartFile file);
    public Result imgUpload(MultipartFile file);
    public Result articleImgUpload(MultipartFile file);
    public Result imgDownload(String fileName, HttpServletResponse response);
    public Result articleImgDownload(String fileName, HttpServletResponse response);
}
