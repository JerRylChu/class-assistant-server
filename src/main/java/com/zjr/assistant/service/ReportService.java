package com.zjr.assistant.service;

import com.zjr.assistant.entities.Report;

public interface ReportService {
    Integer reportIllegal(Report report);
    Integer hasReported(Report report);

}
