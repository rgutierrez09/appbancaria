package com.finzapp.gestion_clientes.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cuenta")
@Getter
@Setter
public class AccountEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Tipo_de_cuenta")
    private String typeAccount;


    @ManyToOne
    @JoinColumn(name = "Product_id")
    private ProductEntity productEntity;
}
