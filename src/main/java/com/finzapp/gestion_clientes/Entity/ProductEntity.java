package com.finzapp.gestion_clientes.Entity;

import com.finzapp.gestion_clientes.Enum.StatAccount;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity extends AuditEntity{

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AccountEntity> account;

    @Column(name = "numero_cuenta",unique = true, nullable = false)
    private String numberAccount;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_cuenta")
    private StatAccount state;

    @Column(name = "saldo_cuenta")
    private BigDecimal balance;

    @Column(name = "exenta_gmf")
    private boolean exentGMF;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private ClientEntity clientEntity;
}
