package org.fasttackit.onlineshop.domain;

import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
public class Review {
    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;


}
