package com.zjr.assistant.controller;

import com.zjr.assistant.entities.Report;
import com.zjr.assistant.service.serviceImpl.ReportServiceImpl;
import com.zjr.assistant.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportController {
    @Autowired
    private ReportServiceImpl reportService;

    @RequestMapping(value = "/reportIllegal",method = RequestMethod.POST)
    public Result reportIllegal(@RequestBody Report report){
        if(reportService.hasReported(report) > 0){
            return new Result(null,201,"您已经举报过该篇文章，请勿重复举报，我们会尽快审核");
        }else {
            try {
                reportService.reportIllegal(report);
                return new Result(null,200,"举报成功，我们会尽快审核");
            }catch (Exception e){
                System.out.println(e);
                return new Result(null,500,"服务器出错了");
            }
        }
    }
}
