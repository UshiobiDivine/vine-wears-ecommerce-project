package com.divine.project.model;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "item")
public class Item extends DateAudit{

    @Column
    private String name;

    @Column
    private String imageUrl;

    @Column
    private String price;

    @Column
    private boolean isNew;

    @Column
    private String description;

    @Column
    private String quantityAvailable;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "item_category", joinColumns = @JoinColumn(name = "item_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Size> sizes;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Color> colors;

}
