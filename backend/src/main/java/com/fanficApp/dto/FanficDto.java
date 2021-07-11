package com.fanficApp.dto;

import com.fanficApp.entity.Chapter;
import com.fanficApp.entity.Image;
import com.fanficApp.entity.Tag;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    private Long imgId;
    private String imgUrl;

    public FanficDto() {
    }

    public FanficDto(String username, Long id, String title, String description, String fandom, Image image, List<Chapter> chapters,
                     Date addedDate, List<Tag> tags) {
        this.username = username;
        this.id = id;
        this.title = title;
        this.description = description;
        this.fandom = fandom;
        this.addedDate = addedDate;
        if(image != null) {
            this.imgId = image.getId();
            this.imgUrl = image.getUrl();
        }
        if(chapters != null)
            for(Chapter chap : chapters) this.chapters.add(new ChapterStruct(chap.getNumber(), chap.getTitle()));
        if(tags != null)
            this.tags = tags.stream().map(Tag::getName).collect(Collectors.toList());
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

    public Long getImgId() {
        return imgId;
    }

    public void setImgId(Long imgId) {
        this.imgId = imgId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
