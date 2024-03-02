package com.zjr.assistant.mapper;

import com.zjr.assistant.entities.Report;

public interface ReportMapper {
    Integer reportIllegal(Report report);
    Integer hasReported(Report report);
}
