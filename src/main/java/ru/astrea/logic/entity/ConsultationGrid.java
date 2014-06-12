package ru.astrea.logic.entity;

import java.util.List;

public class ConsultationGrid {
    private int totalPages;
    private int currentPage;
    private long totalRecords;
    private List<Consultation> consultationData;

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public List<Consultation> getConsultationData() {
        return consultationData;
    }

    public void setConsultationData(List<Consultation> consultationData) {
        this.consultationData = consultationData;
    }
}
