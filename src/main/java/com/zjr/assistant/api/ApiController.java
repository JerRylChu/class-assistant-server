package com.zjr.assistant.api;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.zjr.assistant.entities.*;
import com.zjr.assistant.service.ApiService;
import com.zjr.assistant.service.FileService;
import com.zjr.assistant.service.LoginService;
import com.zjr.assistant.service.serviceImpl.ArticleServiceImpl;
import com.zjr.assistant.utils.Md5Util;
import com.zjr.assistant.utils.Result;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private ApiService apiService;
    @Autowired
    private FileService fileService;
    @Autowired
    private ArticleServiceImpl articleService;

    @PostMapping("/user/login")
    public Result login(User user){
        if(Objects.isNull(user)){
            return new Result(null,-1,"登录用户数据为空");
        }
        if(!loginService.userIsExist(user.getPhoneNumber())){
            return new Result(null,-1,"用户不存在");
        }
        User userByPhoneNumber = loginService.getUserByPhoneNumber(user.getPhoneNumber());
        if(!userByPhoneNumber.getPassword().equals(Md5Util.getString(user.getPassword()))){
           return new Result(null,-1,"密码错误");
        }
        userByPhoneNumber.setPassword(null);
        return new Result(userByPhoneNumber,1,"登陆成功");
    }

    @PostMapping("/user/register")
    public Result register(User user){
        if(Objects.isNull(user)){
            return new Result(null,-1,"注册用户数据为空");
        }
        if(loginService.userIsExist(user.getPhoneNumber())){
            return new Result(null,-1,"手机号已被注册");
        }
        user.setPassword(Md5Util.getString(user.getPassword()));
        try {
            if(loginService.register(user)){
                return new Result(user,1,"注册成功");
            }
            return new Result(null,-1,"注册失败");
        }catch (Exception e){
            return new Result(user,1,"注册成功");
        }
    }

    @PostMapping("/sync/term/upload/{id}")
    public Result uploadTerm(@RequestBody List<Term> terms, @PathVariable("id") Integer id){
        if(Objects.isNull(terms)){
            return new Result(null,-1,"学期数据为空");
        }
        try {
            for(Term term : terms){
                term.setUserId(id);
                if(term.isSelection()){
                    term.setSelected(1);
                }else {
                    term.setSelected(0);
                }
            }
            apiService.delTerms(id);
            if(apiService.uploadTerm(terms)){
                return new Result(terms,1,"同步成功");
            }
            return new Result(null,-1,"数据库操作失败");
        }catch (Exception e){
            return new Result(null,-1,"服务器错误");
        }
    }

    @GetMapping("/sync/term/get")
    public Result getTerms(@RequestParam Integer id){
        if(id == 0){
            return new Result(null,-1,"用户id错误");
        }
        List<Term> terms = apiService.getTerms(id);
        return new Result(terms,1,"获取学期成功");
    }

//    @PostMapping("/sync/course/upload/{id}")
//    public Result uploadCourse(@RequestBody List<Course> courses, @PathVariable("id") Integer id){
//        if(Objects.isNull(courses)){
//            return new Result(null,-1,"课程数据为空");
//        }
//        try {
//            for(Course course : courses){
//                course.setWeekJson(JSONObject.toJSONString(course.getWeekList()));
//            }
//            apiService.delCourses(id);
//            if(apiService.uploadCourse(courses)){
//                return new Result(courses,1,"同步成功");
//            }
//            return new Result(null,-1,"数据库操作失败");
//        }catch (Exception e){
//            return new Result(null,-1,"服务器错误");
//        }
//    }
//
//    @GetMapping("/sync/course/get")
//    public Result getCourses(@RequestParam Integer id){
//        if(id == 0){
//            return new Result(null,-1,"用户id错误");
//        }
//        List<Course> courses = apiService.getCourses(id);
//        for(Course course : courses){
//            course.setWeekList(JSONObject.parseObject(course.getWeekJson(),List.class));
//        }
//        return new Result(courses,1,"获取课程成功");
//    }

    @PostMapping("/sync/toolbox/upload/{id}")
    public Result uploadToolBox(@RequestBody List<ToolBox> toolBoxes, @PathVariable("id") Integer id){
        if(Objects.isNull(toolBoxes)){
            return new Result(null,-1,"工具数据为空");
        }
        try {
            for(ToolBox toolBox : toolBoxes){
                toolBox.setUserId(id);
                String url = fileService.toolBoxUpload(toolBox.getFile());
                toolBox.setHeadiconAddress(url);
            }
            apiService.delToolBox(id);
            if(apiService.uploadToolBox(toolBoxes)){
                return new Result(toolBoxes,1,"同步成功");
            }
            return new Result(null,-1,"数据库操作失败");
        }catch (Exception e){
            return new Result(null,-1,"服务器错误");
        }
    }

    @GetMapping("/sync/toolbox/get")
    public Result getToolBox(@RequestParam Integer id){
        if(id == 0){
            return new Result(null,-1,"用户id错误");
        }
        List<ToolBox> toolBoxes = apiService.getToolBoxes(id);
        return new Result(toolBoxes,1,"获取工具成功");
    }

    @PostMapping("/sync/counter/upload/{id}")
    public Result uploadCounter(@RequestBody List<Counter> counters, @PathVariable("id") Integer id){
        if(Objects.isNull(counters)){
            return new Result(null,-1,"计时器数据为空");
        }
        try {
            for (Counter counter: counters){
                if(counter.getFlag()){
                    counter.setFlagNum(1);
                }else {
                    counter.setFlagNum(0);
                }
                if(counter.getFoucs()){
                    counter.setFocusNum(1);
                }else {
                    counter.setFocusNum(0);
                }
                counter.setUserId(id);
                counter.setListFocusJson(JSONObject.toJSONString(counter.getListFocus()));
            }
            apiService.delCounter(id);
            if(apiService.uploadCounter(counters)){
                return new Result(counters,1,"同步成功");
            }
            return new Result(null,-1,"数据库操作失败");
        }catch (Exception e){
            return new Result(null,-1,"服务器错误");
        }
    }

    @GetMapping("/sync/counter/get")
    public Result getCounters(@RequestParam Integer id){
        if(id == 0){
            return new Result(null,-1,"用户id错误");
        }
        List<Counter> counters = apiService.getCounter(id);
        for(Counter counter : counters){
            if(counter.getFlagNum() == 1){
                counter.setFlag(true);
            }else {
                counter.setFlag(false);
            }
            if(counter.getFocusNum() == 1){
                counter.setFoucs(true);
            }else{
                counter.setFoucs(false);
            }
            counter.setListFocus(JSONObject.parseObject(counter.getListFocusJson(),List.class));
        }
        return new Result(counters,1,"获取计时器成功");
    }

    @PostMapping("/sync/note/upload/{id}")
    public Result uploadNotes(@RequestBody List<Note> notes, @PathVariable("id") Integer id){
        if(Objects.isNull(notes)){
            return new Result(null,-1,"笔记数据为空");
        }
        try {
            for(Note note : notes){
                note.setUserId(id);
            }
            apiService.delNotes(id);
            if(apiService.uploadNotes(notes)){
                return new Result(notes,1,"同步成功");
            }
            return new Result(null,-1,"数据库操作失败");
        }catch (Exception e){
            return new Result(null,-1,"服务器错误");
        }
    }

    @GetMapping("/sync/note/get")
    public Result getNotes(@RequestParam Integer id){
        try {
            List<Note> notes = apiService.getNotes(id);
            return new Result(notes,1,"获取笔记数据成功");
        }catch (Exception e){
            return new Result(null,-1,"服务器错误");
        }
    }

    @PostMapping("/sync/setting/upload/{id}")
    public Result uploadSettings(@RequestBody String settings, @PathVariable("id") Integer id){
        try {
            apiService.delSettings(id);
            if(apiService.uploadSettings(id)){
                return new Result(settings,1,"同步成功");
            }
            return new Result(null,-1,"数据库操作失败");
        }catch (Exception e){
            return new Result(null,-1,"服务器错误");
        }
    }

    @GetMapping("/sync/setting/get")
    public Result getSettings(@RequestParam Integer id){
        try {
            String settings = apiService.getSettings(id);
            return new Result(settings,1,"获取设置成功");
        }catch (Exception e){
            return new Result(null,-1,"服务器错误");
        }
    }

    @RequestMapping(value = "/getHomeInfo", method = RequestMethod.GET)
    public Result getHomeInfo(@RequestParam Integer id){
        HashMap<String,Integer> map = new HashMap<>();
        Integer stars = loginService.getStars(id);
        Integer articles = loginService.getArticleCount(id);
        Integer follows = loginService.getFollowCount(id);
        map.put("stars",stars);
        map.put("articles",articles);
        map.put("follows",follows);
        return new Result(map,200,"");
    }

    @RequestMapping("/upload")
    public Result upload(@RequestBody MobileInfo mobileInfo){
        try {
            mobileInfo.setUploadTime(new Date());
            apiService.delInfo(mobileInfo.getUserId());
            apiService.uploadInfo(mobileInfo);
            return new Result(null,200,"上传成功");
        }catch (Exception e){
            System.out.println("上传失败"+e);
            return new Result(null,500,"上传失败");
        }
    }

    @RequestMapping("/download/{id}")
    public String download(@PathVariable Integer id){
        try {
            String infoByUserId = apiService.getInfoByUserId(id);
            return infoByUserId;
        }catch (Exception e){
            System.out.println(e);
            return "";
        }
    }

}
