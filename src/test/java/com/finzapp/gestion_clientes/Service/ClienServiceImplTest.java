package com.finzapp.gestion_clientes.Service;

import com.finzapp.gestion_clientes.Dto.ClientDto;
import com.finzapp.gestion_clientes.Entity.ClientEntity;
import com.finzapp.gestion_clientes.Enum.TypeDocument;
import com.finzapp.gestion_clientes.Repository.ClientRepository;
import com.finzapp.gestion_clientes.Service.Impl.ClientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ClienServiceImplTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createClient() {
        ClientDto clientDto = ClientDto.builder()
                .DateOfBirth("2004-04-09")
                .build();

        ClientEntity clientEntity = new ClientEntity();
        when(clientRepository.save(clientEntity)).thenReturn(clientEntity);
        clientService.createClient(clientDto);
    }
    @Test
    void creatClientMenor(){
        ClientDto clientDto = ClientDto.builder()
                .DateOfBirth("2009-02-01")
                .build();
    }
    @Test
    void updateClient() {
        ClientDto clientDto = ClientDto.builder()
                .numberIdentification("1075210529")
                .DateOfBirth("2004-04-09")
                .build();
        ClientEntity clientEntity = new ClientEntity();
        Optional<ClientEntity> existingClientEntityOptional = Optional.of(clientEntity);

        ClientRepository clientRepositoryMock =mock(ClientRepository.class);
        when(clientRepositoryMock.findClientEntityBynumberIdentification(clientDto.getNumberIdentification())).thenReturn(existingClientEntityOptional);
        when(clientRepository.save(clientEntity)).thenReturn(clientEntity);

        clientService.updateClient(clientDto.getNumberIdentification(), clientDto);
    }
    @Test
    void updateClientMenor(){
        ClientDto clientDto = ClientDto.builder()
                .numberIdentification("1079210529")
                .DateOfBirth("2014-04-09")
                .build();
        ClientEntity clientEntity = new ClientEntity();
        Optional<ClientEntity> existingClientEntityOptional = Optional.of(clientEntity);

        ClientRepository clientRepositoryMock =mock(ClientRepository.class);
        when(clientRepositoryMock.findClientEntityBynumberIdentification(clientDto.getNumberIdentification())).thenReturn(existingClientEntityOptional);
        when(clientRepository.save(clientEntity)).thenReturn(clientEntity);

        clientService.updateClient(clientDto.getNumberIdentification(), clientDto);
    }
    @Test
    void updateClientnoexist(){
        String numberIdentification = "ClienteExistente";
        ClientDto clientDto = ClientDto.builder()
                .typeIdentification(TypeDocument.CC)
                .numberIdentification(numberIdentification)
                .name("Ronald")
                .lastName("Gutierrez")
                .email("ronald@gmail.com")
                .DateOfBirth("2001-05-04")
                .build();

        ClientEntity clientEntityE = new ClientEntity();
        clientEntityE.setId(1L);
        Optional<ClientEntity> existingClientEntityOptional = Optional.of(clientEntityE);

        when(clientRepository.findClientEntityBynumberIdentification(numberIdentification)).thenReturn(existingClientEntityOptional);
        when(clientRepository.save(any(ClientEntity.class))).thenReturn(clientEntityE);

        clientService.updateClient(numberIdentification, clientDto);
    }

    @Test
    void deleteClient(){
        ClientDto clientDto = ClientDto.builder()
                .numberIdentification("4564655")
                .build();
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setId(1L);
        clientEntity.setProductEntity(new ArrayList<>());
        Optional<ClientEntity>clientEntityOptional = Optional.of(clientEntity);

        when(clientRepository.findClientEntityBynumberIdentification(clientDto.getNumberIdentification())).thenReturn(clientEntityOptional);
        when(clientRepository.existsById(clientEntity.getId())).thenReturn(true);

        clientService.deleteClient(clientDto.getNumberIdentification());
    }

    @Test
    void deleteWithProductAsociated(){
        ClientDto clientDto = ClientDto.builder()
                .numberIdentification("4564655")
                .build();
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setId(1L);
        clientEntity.setProductEntity(new ArrayList<>());
        Optional<ClientEntity>clientEntityOptional = Optional.of(clientEntity);

        when(clientRepository.findClientEntityBynumberIdentification(clientDto.getNumberIdentification())).thenReturn(clientEntityOptional);
        when(clientRepository.existsById(clientEntity.getId())).thenReturn(true);

        clientService.deleteClient(clientDto.getNumberIdentification());
    }

    @Test
    void deleteClientNoFound(){
        ClientDto clientDto = ClientDto.builder()
                .numberIdentification("4564655")
                .build();
        when(clientRepository.findClientEntityBynumberIdentification(clientDto.getNumberIdentification())).thenReturn(Optional.empty());

        clientService.deleteClient(clientDto.getNumberIdentification());

    }
}
