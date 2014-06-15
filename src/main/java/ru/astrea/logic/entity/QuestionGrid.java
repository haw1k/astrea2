package ru.astrea.logic.entity;

import java.util.List;

public class QuestionGrid {
    private int totalPages;
    private int currentPage;
    private long totalRecords;
    private List<Question> questionData;

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

    public List<Question> getQuestionData() {
        return questionData;
    }

    public void setQuestionData(List<Question> questionData) {
        this.questionData = questionData;
    }
}
