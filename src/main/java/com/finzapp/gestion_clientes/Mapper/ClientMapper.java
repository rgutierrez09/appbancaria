package com.finzapp.gestion_clientes.Mapper;

import com.finzapp.gestion_clientes.Dto.ClientDto;
import com.finzapp.gestion_clientes.Entity.ClientEntity;

public class ClientMapper {
    public static ClientEntity dtoClientEntity(ClientDto clientDto) {
        ClientEntity dtoClientEntity = new ClientEntity();
        dtoClientEntity.setTypeIdentification(clientDto.getTypeIdentification());
        dtoClientEntity.setNumberIdentification(clientDto.getNumberIdentification());
        dtoClientEntity.setName(clientDto.getName());
        dtoClientEntity.setLastName(clientDto.getLastName());
        dtoClientEntity.setEmail(clientDto.getEmail());
        dtoClientEntity.setDateOfBirth(clientDto.getDateOfBirth());
        dtoClientEntity.setCreationDate(clientDto.getCreationDate());
        dtoClientEntity.setModificationDate(clientDto.getModificationDate());

        return dtoClientEntity;
    }
}
