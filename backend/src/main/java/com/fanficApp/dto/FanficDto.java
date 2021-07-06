package com.fanficApp.dto;

import com.fanficApp.entity.Chapter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FanficDto {

    private String username;

    private Long id;
    private String title;
    private String description;
    private String fandom;
    private Date addedDate;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<ChapterStruct> chapters = new ArrayList<>();
    private List<String> tags;

    public FanficDto() {
    }

    public FanficDto(String username, Long id, String title, String description, String fandom, List<Chapter> chapters,
                     Date addedDate, List<String> tags) {
        this.username = username;
        this.id = id;
        this.title = title;
        this.description = description;
        this.fandom = fandom;
        this.addedDate = addedDate;
        for(Chapter chap : chapters) this.chapters.add(new ChapterStruct(chap.getNumber(), chap.getTitle()));
        this.tags = tags;
    }

    static private class ChapterStruct {
        public int number;
        public String title;

        public ChapterStruct(int number, String title) {
            this.number = number;
            this.title = title;
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFandom() {
        return fandom;
    }

    public void setFandom(String fandom) {
        this.fandom = fandom;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public List<ChapterStruct> getChapters() {
        return chapters;
    }

    public void setChapters(List<ChapterStruct> chapters) {
        this.chapters = chapters;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
