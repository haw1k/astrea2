package ru.astrea.logic.entity;

import java.util.List;

public class ReviewGrid {
    private int totalPages;
    private int currentPage;
    private long totalRecords;
    private List<Review> reviewData;

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

    public List<Review> getReviewData() {
        return reviewData;
    }

    public void setReviewData(List<Review> reviewData) {
        this.reviewData = reviewData;
    }
}
