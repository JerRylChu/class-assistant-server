package com.zjr.assistant.mapper;

import com.zjr.assistant.entities.*;
import io.swagger.models.auth.In;

import java.util.List;

public interface ApiMapper {
    Integer uploadTerm(List<Term> terms);
    Integer delTerms(Integer id);
    List<Term> getTerms(Integer id);
    Integer delCourses(Integer id);
    Integer uploadCourse(List<Course> courses);
    List<Course> getCourses(Integer id);
    Integer delToolBox(Integer id);
    Integer uploadToolBox(List<ToolBox> toolBoxes);
    List<ToolBox> getToolBoxes(Integer id);
    Integer delCounter(Integer id);
    Integer uploadCounter(List<Counter> counters);
    List<Counter> getCounter(Integer id);
    Integer delNotes(Integer id);
    Integer uploadNotes(List<Note> notes);
    List<Note> getNotes(Integer id);
    Integer delSettings(Integer id);
    Integer uploadSettings(Integer id);
    String getSettings(Integer id);
    Integer uploadInfo(MobileInfo mobileInfo);
    String getInfoByUserId(Integer id);
    Integer delInfo(Integer id);
}
