package com.divine.project.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "category")
public class Category extends DateAudit{

    @Column
    private String title;

    @Column
    private String routeName;

    @Column
    private String imageUrl;

    @Column
    private String linkUrl;

    @ManyToMany(mappedBy = "categories", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Item> items;

    public Category(String title, String routeName, String imageUrl, String linkUrl) {
        this.title = title;
        this.routeName = routeName;
        this.imageUrl = imageUrl;
        this.linkUrl = linkUrl;
    }
}
