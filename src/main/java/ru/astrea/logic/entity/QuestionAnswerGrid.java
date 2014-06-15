package ru.astrea.logic.entity;

import java.util.List;

public class QuestionAnswerGrid {
    private int totalPages;
    private int currentPage;
    private long totalRecords;

    private List<QuestionAnswer> questionAnswerData;

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

    public List<QuestionAnswer> getQuestionAnswerData() {
        return questionAnswerData;
    }

    public void setQuestionAnswerData(List<QuestionAnswer> questionAnswerData) {
        this.questionAnswerData = questionAnswerData;
    }
}
