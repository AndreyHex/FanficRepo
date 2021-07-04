package com.fanficApp.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String name;

    @Transient
    @ManyToMany(mappedBy = "tags")
    private List<Fanfic> fanfic;

    public Tag() {
    }

    public Tag(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Fanfic> getFanfic() {
        return fanfic;
    }

    public void setFanfic(List<Fanfic> fanfic) {
        this.fanfic = fanfic;
    }
}
