package com.finzapp.gestion_clientes.Dto;

import com.finzapp.gestion_clientes.Entity.AuditEntity;
import com.finzapp.gestion_clientes.Enum.StatAccount;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountStateDto extends AuditEntity {

    private String numberAccount;
    @Enumerated(EnumType.STRING)
    private StatAccount stats;
}
