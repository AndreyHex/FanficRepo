package com.fanficApp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChapterDto {

    private long fanficId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String fanficTitle;

    private int number;
    private String title;
    private String text;

    public ChapterDto(long fanficId, String fanficTitle, int number, String title, String text) {
        this.fanficId = fanficId;
        this.fanficTitle = fanficTitle;
        this.number = number;
        this.title = title;
        this.text = text;
    }

    public long getFanficId() {
        return fanficId;
    }

    public void setFanficId(long fanficId) {
        this.fanficId = fanficId;
    }

    public String getFanficTitle() {
        return fanficTitle;
    }

    public void setFanficTitle(String fanficTitle) {
        this.fanficTitle = fanficTitle;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
