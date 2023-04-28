package com.projectNoBay.NoBay.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Data
@Builder
@Entity
//@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private float price;
    private int quantity;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User user;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "id")
    private List<BeautyProducts> beautyProductsCart;

}
