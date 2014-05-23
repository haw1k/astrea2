package ru.astrea.logic.entity;

import java.util.List;

public class TurnGrid {
    private int totalPages;
    private int currentPage;
    private long totalRecords;
    private List<Turn> turnData;

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
    }
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<Turn> getTurnData() {
        return turnData;
    }

    public void setTurnData(List<Turn> turnData) {
        this.turnData = turnData;
    }
}
