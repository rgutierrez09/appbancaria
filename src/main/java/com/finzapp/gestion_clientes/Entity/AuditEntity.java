package com.finzapp.gestion_clientes.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public class AuditEntity {
    @Column(name = "fecha_creacion")
    private LocalDateTime CreationDate;

    @Column(name = "fecha_modificacion")
    private LocalDateTime ModificationDate;
}
