package com.finzapp.gestion_clientes.Dto;


import com.finzapp.gestion_clientes.Entity.AuditEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.finzapp.gestion_clientes.Enum.TypeDocument;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Builder
@Getter
@Setter
public class ClientDto extends AuditEntity {
    private TypeDocument typeIdentification;
    private  String numberIdentification;
    @Length(message = "El nombre debe tener al menos 2 caracteres", min = 2)
    private  String name;
    @Length(message = "El apellido debe tener al menos 2 caracteres", min = 2)
    private  String lastName;
    private  String email;
    private  String DateOfBirth;
    @JsonIgnore
    private List<ProductDto> productDto;
}
