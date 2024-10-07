package com.finzapp.gestion_clientes.Controller;

import com.finzapp.gestion_clientes.Dto.ClientDto;
import com.finzapp.gestion_clientes.Enum.TypeDocument;
import com.finzapp.gestion_clientes.Service.IClientService;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class ClientControllerTest {

    @InjectMocks
    private ClientController clientController;

    @Mock
    private IClientService iClientService;

    @Test
    void createClient_Success() {
        ClientDto clientDto = ClientDto.builder()
                .name("aaaaa")
                .lastName("bbbbbb")
                .email("aaaaa@gmail.com")
                .DateOfBirth("2004-08-09")
                .typeIdentification(TypeDocument.CC)
                .build();

        when(iClientService.createClient(any(ClientDto.class))).thenReturn(true);
        clientController.createClient(clientDto);
    }

    @Test
    void createClient_Failure() {
        ClientDto clientDto = ClientDto.builder().build();
        when(iClientService.createClient(any(ClientDto.class))).thenThrow(new RuntimeException("CUENTA CANCELADA"));
        clientController.createClient(clientDto);
    }

    @Test
    void updateClient_Success() {
        ClientDto clientDto = ClientDto.builder()
                .typeIdentification(TypeDocument.CC)
                .build();
        String numberIdenti = "123456677";

        when(iClientService.updateClient(eq(numberIdenti), any(ClientDto.class))).thenReturn(true);
        clientController.updateClient(numberIdenti, clientDto);
    }

    @Test
    void updateClient_NotFound() {
        ClientDto clientDto = ClientDto.builder()
                .typeIdentification(TypeDocument.CC)
                .build();
        String numberIdenti = "123456677";

        when(iClientService.updateClient(eq(numberIdenti), any(ClientDto.class))).thenThrow(new IllegalArgumentException("CUENTA NO ENCONTRADA"));
        clientController.updateClient(numberIdenti, clientDto);
    }

    @Test
    void updateClient_Exception() {
        ClientDto clientDto = ClientDto.builder()
                .typeIdentification(TypeDocument.CC)
                .build();
        String numberIdenti = "123456677";

        when(iClientService.updateClient(eq(numberIdenti), any(ClientDto.class))).thenThrow(new RuntimeException("CUENTA ELIMINADA"));
        clientController.updateClient(numberIdenti, clientDto);
    }

    @Test
    void deleteClient_Success() {
        String numberIdenti = "123456677";
        when(iClientService.deleteClient(eq(numberIdenti))).thenReturn("CUENTA CANCELADA");
        clientController.deleteClient(numberIdenti);
    }

    @Test
    void deleteClient_NotFound() {
        String numberIdenti = "123456677";
        when(iClientService.deleteClient(eq(numberIdenti))).thenThrow(new IllegalArgumentException("CUENTA NO ENCONTRADA"));
        clientController.deleteClient(numberIdenti);
    }

    @Test
    void deleteClient_Exception() {
        String numberIdenti = "123456677";
        when(iClientService.deleteClient(eq(numberIdenti))).thenThrow(new RuntimeException("CUENTA ELIMINADA"));
        clientController.deleteClient(numberIdenti);
    }

}