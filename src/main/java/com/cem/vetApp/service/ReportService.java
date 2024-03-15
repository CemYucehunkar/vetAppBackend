package com.cem.vetApp.service;

import com.cem.vetApp.config.BaseService;
import com.cem.vetApp.config.ModelMapperService;
import com.cem.vetApp.dto.request.ReportRequest;
import com.cem.vetApp.dto.response.ReportResponse;
import com.cem.vetApp.entity.Report;
import com.cem.vetApp.repository.ReportRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService implements BaseService<Report, ReportRequest, ReportResponse> {
    private final ReportRepository reportRepository;
    private final ModelMapperService modelMapperService;

    @Override
    public Report getById (Long id)
        {
            return reportRepository.findById(id)
                    .orElseThrow (() -> new EntityNotFoundException ("Report with ID" + id + " not found"));
        }

    @Override
    public ReportResponse getResponseById (Long id)
        {
            return modelMapperService
                    .forResponse ()
                    .map (getById (id), ReportResponse.class);
        }

    @Override
    public List<Report> getAll ()
        {
            return reportRepository.findAll ();
        }

    @Override
    public List<ReportResponse> getAllResponses ()
        {
            return getAll ()
                    .stream ().map (report -> modelMapperService
                            .forResponse ()
                            .map (report, ReportResponse.class))
                    .toList ();
        }

    @Override
    public ReportResponse create (ReportRequest reportRequest)
        {
            Report report = modelMapperService
                    .forRequest ()
                    .map (reportRequest, Report.class);

            return modelMapperService
                    .forResponse ()
                    .map (reportRepository.save (report), ReportResponse.class);
        }

    @Override
    public ReportResponse update (Long id , ReportRequest reportRequest)
        {
            Report doesReportExist = getById (id);

            modelMapperService.forRequest ().map (reportRequest, doesReportExist);

            return modelMapperService
                    .forResponse ()
                    .map (reportRepository.save (doesReportExist), ReportResponse.class);
        }

    @Override
    public void deleteById (Long id)
        {
            reportRepository.delete (getById (id));
        }
}
