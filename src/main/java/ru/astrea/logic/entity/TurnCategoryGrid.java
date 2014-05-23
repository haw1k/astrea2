package ru.astrea.logic.entity;

import java.util.List;

public class TurnCategoryGrid {
    private int totalPages;
    private int currentPage;
    private long totalRecords;
    private List<TurnCategory> turnCategoryData;

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

    public List<TurnCategory> getTurnCategoryData() {
        return turnCategoryData;
    }

    public void setTurnCategoryData(List<TurnCategory> turnCategoryData) {
        this.turnCategoryData = turnCategoryData;
    }
}
