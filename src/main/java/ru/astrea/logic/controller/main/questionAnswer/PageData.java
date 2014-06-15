package ru.astrea.logic.controller.main.questionAnswer;

public class PageData {
    private int numberPage;

    public int getNumberPage() {
        return numberPage;
    }

    public void setNumberPage(int numberPage) {
        this.numberPage = numberPage;
    }

    public PageData(int numberPage) {
        this.numberPage = numberPage;
    }

    public PageData() {
    }

    @Override
    public String toString() {
        return "PageData{" +
                "numberPage=" + numberPage +
                '}';
    }
}
