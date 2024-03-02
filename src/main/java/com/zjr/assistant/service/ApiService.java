package com.zjr.assistant.service;

import com.zjr.assistant.entities.*;

import java.util.List;

public interface ApiService {
    Boolean uploadTerm(List<Term> terms);
    Boolean delTerms(Integer id);
    List<Term> getTerms(Integer id);
    Boolean delCourses(Integer id);
    Boolean uploadCourse(List<Course> courses);
    List<Course> getCourses(Integer id);
    Boolean delToolBox(Integer id);
    Boolean uploadToolBox(List<ToolBox> toolBoxes);
    List<ToolBox> getToolBoxes(Integer id);
    Boolean delCounter(Integer id);
    Boolean uploadCounter(List<Counter> counters);
    List<Counter> getCounter(Integer id);
    Boolean delNotes(Integer id);
    Boolean uploadNotes(List<Note> notes);
    List<Note> getNotes(Integer id);
    Boolean delSettings(Integer id);
    Boolean uploadSettings(Integer id);
    String getSettings(Integer id);
    Integer uploadInfo(MobileInfo mobileInfo);
    String getInfoByUserId(Integer id);
    Integer delInfo(Integer id);
}
