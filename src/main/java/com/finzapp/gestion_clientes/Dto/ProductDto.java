package com.finzapp.gestion_clientes.Dto;

import com.finzapp.gestion_clientes.Entity.AuditEntity;
import com.finzapp.gestion_clientes.Enum.StatAccount;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductDto extends AuditEntity {

    private String typeAccount;
    private String numberAccount;
    private StatAccount state;
    private BigDecimal balance;
    private boolean exentGMF;
    private Long clientId;
}
