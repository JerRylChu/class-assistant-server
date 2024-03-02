package com.zjr.assistant.service.serviceImpl;

import com.zjr.assistant.entities.*;
import com.zjr.assistant.mapper.ApiMapper;
import com.zjr.assistant.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiServiceImpl implements ApiService {
    @Autowired
    private ApiMapper apiMapper;

    @Override
    public Boolean uploadTerm(List<Term> terms) {
        return apiMapper.uploadTerm(terms) > 0;
    }

    @Override
    public Boolean delTerms(Integer id) {
        return apiMapper.delTerms(id) > 0;
    }

    @Override
    public List<Term> getTerms(Integer id) {
        return apiMapper.getTerms(id);
    }

    @Override
    public Boolean delCourses(Integer id) {
        return apiMapper.delCourses(id) > 0;
    }

    @Override
    public Boolean uploadCourse(List<Course> courses) {
        return apiMapper.uploadCourse(courses) > 0;
    }

    @Override
    public List<Course> getCourses(Integer id) {
        return apiMapper.getCourses(id);
    }

    @Override
    public Boolean delToolBox(Integer id) {
        return apiMapper.delToolBox(id) > 0;
    }

    @Override
    public Boolean uploadToolBox(List<ToolBox> toolBoxes) {
        return apiMapper.uploadToolBox(toolBoxes) > 0;
    }

    @Override
    public List<ToolBox> getToolBoxes(Integer id) {
        return apiMapper.getToolBoxes(id);
    }

    @Override
    public Boolean delCounter(Integer id) {
        return apiMapper.delCounter(id) > 0;
    }

    @Override
    public Boolean uploadCounter(List<Counter> counters) {
        return apiMapper.uploadCounter(counters) > 0;
    }

    @Override
    public List<Counter> getCounter(Integer id) {
        return apiMapper.getCounter(id);
    }

    @Override
    public Boolean delNotes(Integer id) {
        return apiMapper.delNotes(id) > 0;
    }

    @Override
    public Boolean uploadNotes(List<Note> notes) {
        return apiMapper.uploadNotes(notes) > 0;
    }

    @Override
    public List<Note> getNotes(Integer id) {
        return apiMapper.getNotes(id);
    }

    @Override
    public Boolean delSettings(Integer id) {
        return apiMapper.delSettings(id) > 0;
    }

    @Override
    public Boolean uploadSettings(Integer id) {
        return apiMapper.uploadSettings(id) > 0;
    }

    @Override
    public String getSettings(Integer id) {
        return apiMapper.getSettings(id);
    }

    @Override
    public Integer uploadInfo(MobileInfo mobileInfo) {
        return apiMapper.uploadInfo(mobileInfo);
    }

    @Override
    public String getInfoByUserId(Integer id) {
        return apiMapper.getInfoByUserId(id);
    }

    @Override
    public Integer delInfo(Integer id) {
        return apiMapper.delInfo(id);
    }
}
