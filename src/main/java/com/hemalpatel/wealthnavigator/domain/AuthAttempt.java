package com.hemalpatel.wealthnavigator.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "auth_attempt")
@Data
@Accessors(chain = true)
public class AuthAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "success")
    private boolean success;

    @Column(name = "date")
    private Date date;
}
