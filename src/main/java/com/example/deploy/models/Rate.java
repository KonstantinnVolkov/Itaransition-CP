package com.example.deploy.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "rates")
public class Rate {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long rate_id;

    @Column(name = "post_id")
    private long post_id;

    @Column(name = "rate")
    private int rate;
}
