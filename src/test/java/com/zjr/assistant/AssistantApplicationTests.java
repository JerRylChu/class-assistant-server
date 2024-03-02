package com.zjr.assistant;

import com.zjr.assistant.entities.TypeTree;
import com.zjr.assistant.service.serviceImpl.LoginServiceImpl;
import com.zjr.assistant.service.serviceImpl.TypeTreeServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.swing.text.StyledEditorKit;
import java.beans.Transient;
import java.io.File;
import java.util.List;

@SpringBootTest
class AssistantApplicationTests {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private LoginServiceImpl loginService;
    private static String PATH = System.getProperty("user.dir");
    @Test
    void contextLoads() {
    }

    @Test
    void testPostQQ(){
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("2759442036@qq.com");//发送者
        msg.setTo("\n" +
                "2759442036@qq.com");//接收者
        msg.setSubject("邮箱标题");//标题
        msg.setText("测试测试");//内容
        javaMailSender.send(msg);
    }

    @Test
    void testPath(){
        List<String> imgPath = loginService.getImgPath();
        for(int i = 0; i < imgPath.size(); i++){
            imgPath.set(i,imgPath.get(i).split("/")[6]);
        }
        for(String path : imgPath){
            System.out.println(path);
        }
    }

    @Test
    void testFile(){
        File file = new File(PATH+"/file/images/avatars");
        File[] files = file.listFiles();
        if(files.length == 0){
            System.out.println("没有任何图片");
            return;
        }
        for(int i = 0; i < files.length; i++){
            File file1 = files[i];
            System.out.println(file1.getName());
        }
    }

}
