package ru.astrea.logic.entity;

import java.util.List;

public class TurnPriceGrid {
    private int totalPages;
    private int currentPage;
    private long totalRecords;
    private List<TurnPrice> turnPriceData;

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

    public List<TurnPrice> getTurnPriceData() {
        return turnPriceData;
    }

    public void setTurnPriceData(List<TurnPrice> turnPriceData) {
        this.turnPriceData = turnPriceData;
    }
}
