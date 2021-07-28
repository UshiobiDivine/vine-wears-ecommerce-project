package com.divine.project.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "item_color")
public class Color extends DateAudit {

    @Column
    private String name;

    public Color(String name) {
        this.name = name;
    }
}
