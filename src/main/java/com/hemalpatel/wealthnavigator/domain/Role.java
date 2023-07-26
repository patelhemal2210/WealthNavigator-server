package com.hemalpatel.wealthnavigator.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "role")
@Data
@Accessors(chain = true)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_id")
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private ERole name;

    @Column(name = "description")
    private String description;
}
