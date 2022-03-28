package com.xyzcorp.testproject.domain;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
@Data
@Builder
public class User {
    @Id
    @Column(nullable = false)
    private String name;

    private float salary;

    @Tolerate
    public User() {
    }
}
