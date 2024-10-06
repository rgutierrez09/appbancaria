package com.finzapp.gestion_clientes.Service.Impl;

import com.finzapp.gestion_clientes.Dto.ClientDto;
import com.finzapp.gestion_clientes.Entity.ClientEntity;
import com.finzapp.gestion_clientes.Mapper.ClientMapper;
import com.finzapp.gestion_clientes.Repository.ClientRepository;
import com.finzapp.gestion_clientes.Service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Optional;

@Service
public class ClientServiceImpl implements IClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Object createClient(ClientDto clientDto) {
        Boolean validAge = validateAgeClient(clientDto.getDateOfBirth());
        if(!validAge){
            return "NO PUEDES SER CLIENTE SI ERES MENOR DE EDAD";
        }

        clientDto.setCreationDate(LocalDateTime.now());
        clientDto.setModificationDate(null);
        ClientEntity saveInformation = ClientMapper.dtoClientEntity(clientDto);
        return clientRepository.save(saveInformation);

    }

    @Override
    public Object updateClient (String numberIdentification, ClientDto clientDto){
        Optional<ClientEntity> existingClientEntity = clientRepository.findClientEntityBynumberIdentification(numberIdentification);

        boolean validAge = validateAgeClient(clientDto.getDateOfBirth());
        if(!validAge){
            return "NO PUEDES SER CLIENTE SI ERES MENOR DE EDAD";
        }

        if(existingClientEntity.isPresent()){
            existingClientEntity.get().setTypeIdentification(clientDto.getTypeIdentification());
            existingClientEntity.get().setNumberIdentification(clientDto.getNumberIdentification());
            existingClientEntity.get().setName(clientDto.getName());
            existingClientEntity.get().setLastName(clientDto.getLastName());
            existingClientEntity.get().setEmail(clientDto.getEmail());
            existingClientEntity.get().setDateOfBirth(clientDto.getDateOfBirth());
            existingClientEntity.get().setModificationDate(clientDto.getModificationDate());
            return clientRepository.save(existingClientEntity.get());

        }
        return null;
    }

    @Override
    public String deleteClient (String numberIdentification){
        Optional<ClientEntity> clientEntity = clientRepository.findClientEntityBynumberIdentification(numberIdentification);
        if(clientEntity.isPresent()){
            if(clientEntity.get().getProductEntity().isEmpty()){
                clientRepository.deleteById(clientEntity.get().getId());
                return "CLIENTE ELIMINADO";
            }
            return "ERROR AL ELIMINAR CLIENTE";
        }
        return "ERROR CLIENTE NO ENCONTRADO";
    }

    private boolean validateAgeClient(String dateOfBirth){
        LocalDate dateBir = LocalDate.parse(dateOfBirth);
        LocalDate now = LocalDate.now();
        Period period = Period.between(dateBir, now);
        int age = period.getYears();
        if(age < 18){
            return false;
        }
        return true;
    }

}
