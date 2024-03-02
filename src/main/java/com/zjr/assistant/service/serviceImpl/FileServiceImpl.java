package com.zjr.assistant.service.serviceImpl;

import com.zjr.assistant.controller.FileController;
import com.zjr.assistant.service.FileService;
import com.zjr.assistant.utils.Result;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import spire.math.Algebraic;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class FileServiceImpl implements FileService {
    private static String returnImgPath = "http://localhost:8081/dev-api/file/imgDownload/";
    private static String returnArticlePath = "http://localhost:8081/dev-api/file/articleImgDownload/";
    private static String returnAvatarPath = "http://localhost:8081/dev-api/file/avatarDownload/";
    private static String returnToolBoxPath = "http://localhost:8081/dev-api/file/toolBoxDownload/";
    private static String PATH = System.getProperty("user.dir");

    static {

            File file = new File(PATH+"/file");
            if(!file.exists()){
                file.mkdir();
            }
            file = new File(PATH+"/file/images");
            if(!file.exists()){
                file.mkdir();
            }
            file = new File(PATH+"/file/images/photos");
            if(!file.exists()){
                file.mkdir();
            }
            file = new File(PATH+"/file/images/avatars");
            if(!file.exists()){
                file.mkdir();
            }
            file = new File(PATH+"/file/images/articles");
            if(!file.exists()){
                file.mkdir();
            }
            file = new File(PATH+"/file/images/toolbox");
            if(!file.exists()){
                file.mkdir();
            }
    }

    @Autowired
    private LoginServiceImpl loginService;

    @Scheduled(cron = "30 45 12 26 * ?")
    public void delImage(){
        System.out.println("开始清理资源");
        List<String> imgPath = loginService.getImgPath();
        for(int i = 0; i < imgPath.size(); i++){
            imgPath.set(i,imgPath.get(i).split("/")[6]);
        }
        File file = new File(PATH+"/file/images/avatars");
        File[] files = file.listFiles();
        if(files.length == 0){
            System.out.println("没有任何图片");
            return;
        }
        for(int i = 0; i < files.length; i++){
            File file1 = files[i];
            if(!imgPath.contains(file1.getName())){
                file1.delete();
                System.out.println("删除资源"+file1.getName());
            }
        }
        System.out.println("资源清理完成");
    }

    public String toolBoxUpload(MultipartFile file){
        return imageUpload(file,PATH+"/file/images/toolbox" , returnToolBoxPath).getData().toString();
    }

    public Result avatarUpload(MultipartFile file){
        return imageUpload(file,PATH+"/file/images/avatars",returnAvatarPath);
    }

    public Result avatarDownload(String fileName, HttpServletResponse response){
        return imageDownload(fileName,response,PATH+"/file/images/avatars");
    }

    @Override
    public Result imgUpload(MultipartFile file) {
        return imageUpload(file,PATH+"/file/images/photos",returnImgPath);
    }

    /**
     * 相册图片下载
     * @param fileName
     * @param response
     * @return
     */
    @Override
    public Result imgDownload(String fileName, HttpServletResponse response) {
        return imageDownload(fileName,response,PATH+"/file/images/photos");
    }

    @Override
    public Result articleImgUpload(MultipartFile file) {
        return imageUpload(file,PATH+"/file/images/articles",returnArticlePath);
    }

    /**
     * 文章图片下载
     * @param fileName
     * @param response
     * @return
     */
    @Override
    public Result articleImgDownload(String fileName, HttpServletResponse response) {
        return imageDownload(fileName,response,PATH+"/file/images/articles");
    }

    /**
     * 图片上传实现
     * @param file 文件
     * @param path  保存路径
     * @return
     */
    private Result imageUpload(MultipartFile file, String path, String returnPath){
        if(file.isEmpty()){
            return new Result(null,500,"上传失败");
        }
        long time = new Date().getTime();
        String finalPath = path+"/"+time+".jpg";
        File destination = new File(finalPath);
        if(!destination.getParentFile().exists()){
            destination.getParentFile().mkdir();
        }
        try {
            file.transferTo(destination);
        }catch (IllegalStateException | IOException e){
            e.printStackTrace();
            return new Result(null,500,"上传失败");
        }
        return new Result(returnPath+time+".jpg",200,"上传成功");
    }

    /**
     * 实现图片下载
     * @param fileName
     * @param response
     * @param filePath
     * @return
     */
    private Result imageDownload(String fileName, HttpServletResponse response, String filePath){
        File file = new File(filePath + "/" + fileName);
        if(file.exists()){ //判断文件父目录是否存在
            response.setContentType("image/jpeg");
            response.setCharacterEncoding("UTF-8");
            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;
            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while(i != -1){
                    os.write(buffer);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return new Result(null,500,"获取资源失败");
            }
            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
                return new Result(null,500,"获取资源失败");
            }
        }
        return new Result(null,200,null);
    }

}
