package com.zjr.assistant.service.serviceImpl;

import com.zjr.assistant.entities.Report;
import com.zjr.assistant.mapper.ReportMapper;
import com.zjr.assistant.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ReportMapper reportMapper;

    @Override
    public Integer reportIllegal(Report report) {
        return reportMapper.reportIllegal(report);
    }

    @Override
    public Integer hasReported(Report report) {
        return reportMapper.hasReported(report);
    }
}
