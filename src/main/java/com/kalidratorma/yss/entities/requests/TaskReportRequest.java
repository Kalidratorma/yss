package com.kalidratorma.yss.entities.requests;

import com.kalidratorma.yss.file.ContentFile;

import java.util.Date;
import java.util.List;

public class TaskReportRequest {
    private Date reportDate;
    private Long taskId;
    private Long playerId;
    private Date taskDate;
    private String report;
    private List<ContentFile> photoLinks;
    private List<ContentFile> videoLinks;

    public TaskReportRequest() {
    }

    public TaskReportRequest(Date reportDate, Long taskId, Long playerId, Date taskDate, String report, List<ContentFile> photoLinks, List<ContentFile> videoLinks) {
        this.reportDate = reportDate;
        this.taskId = taskId;
        this.playerId = playerId;
        this.taskDate = taskDate;
        this.report = report;
        this.photoLinks = photoLinks;
        this.videoLinks = videoLinks;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Date getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(Date taskDate) {
        this.taskDate = taskDate;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public List<ContentFile> getPhotoLinks() {
        return photoLinks;
    }

    public void setPhotoLinks(List<ContentFile> photoLinks) {
        this.photoLinks = photoLinks;
    }

    public List<ContentFile> getVideoLinks() {
        return videoLinks;
    }

    public void setVideoLinks(List<ContentFile> videoLinks) {
        this.videoLinks = videoLinks;
    }

    @Override
    public String toString() {
        return "TaskReportRequest{" +
                "reportDate=" + reportDate +
                ", taskId=" + taskId +
                ", playerId=" + playerId +
                ", taskDate=" + taskDate +
                ", report='" + report + '\'' +
                ", photoLinks=" + photoLinks +
                ", videoLinks=" + videoLinks +
                '}';
    }
}
