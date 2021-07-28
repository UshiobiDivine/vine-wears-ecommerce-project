package com.divine.project.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "item_size")
public class Size extends DateAudit{

    @Column
    private String name;

    public Size(String name) {
        this.name = name;
    }
}
