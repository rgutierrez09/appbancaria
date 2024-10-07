package com.finzapp.gestion_clientes.Controller;


import com.finzapp.gestion_clientes.Dto.AccountStateDto;
import com.finzapp.gestion_clientes.Dto.ClientAccountRequestDto;
import com.finzapp.gestion_clientes.Service.IProductService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private IProductService iProductService;

    @Test
    void createAccountSuccess() {
        ClientAccountRequestDto clientAccountRequestDto = new ClientAccountRequestDto();

        when(iProductService.createAccount(any(ClientAccountRequestDto.class))).thenReturn(true);
        productController.createAccount(clientAccountRequestDto);
    }

    @Test
    void createAccountFail() {
        ClientAccountRequestDto clientAccountRequestDto = new ClientAccountRequestDto();

        when(iProductService.createAccount(any(ClientAccountRequestDto.class))).thenThrow(new RuntimeException("ERROR AL CREAR LA CUENTA"));

        productController.createAccount(clientAccountRequestDto);
    }

    @Test
    void updateStateAccountSuccess() {
        AccountStateDto accountStateDto = new AccountStateDto();

        when(iProductService.updateStatAccount((any()))).thenReturn(null);
        productController.updateStatAccount(accountStateDto);
    }

    @Test
    void updateStateAccountNoFound() {
        AccountStateDto accountStateDto = new AccountStateDto();

        when(iProductService.updateStatAccount(any(AccountStateDto.class))).thenReturn(null);
    }

    @Test
    void deletedAccountSuccess() {
        AccountStateDto accountStateDto = new AccountStateDto();

        when(iProductService.cancelAccount(any())).thenReturn(null);
        productController.cancelAccount(accountStateDto);
    }

    @Test
    void deletedAccountFail() {
        AccountStateDto accountStateDto = new AccountStateDto();
        when(iProductService.cancelAccount(any())).thenThrow(new RuntimeException("EROR AL BORRAR LA CUENTA"));
        productController.cancelAccount(accountStateDto);
    }

    @Test
    void depositSuccess() {
        String numberAccount = "12333333";
        BigDecimal aumont = BigDecimal.valueOf(100);

        when(iProductService.depositMoney(any(), any())).thenReturn(null);

        productController.depositMoney(numberAccount, aumont);
    }

    @Test
    void depositNoFound() {
        String numberAccount = "12333333";
        BigDecimal aumont = BigDecimal.valueOf(100);

        when(iProductService.depositMoney(any(), any())).thenThrow(new IllegalArgumentException("ERORR CUENTA NO ENCONTRADA"));
        productController.depositMoney(numberAccount, aumont);
    }

    @Test
    void depositFail() {
        String numberAccount = "12333333";
        BigDecimal aumont = BigDecimal.valueOf(100);
        when(iProductService.depositMoney(any(), any())).thenThrow(new RuntimeException("ERROR NO SE PUDO CONSIGNAR"));
        productController.depositMoney(numberAccount, aumont);
    }

    @Test
    void withdrawSuccess() {
        String numberAccount = "12333333";
        BigDecimal aumont = BigDecimal.valueOf(100);
        when(iProductService.withdrawMoney(any(), any())).thenReturn(null);
        productController.withdrawMoney(numberAccount, aumont);
    }

    @Test
    void withdrawNoFound() {
        String numberAccount = "12333333";
        BigDecimal aumont = BigDecimal.valueOf(100);

        when(iProductService.withdrawMoney(any(), any())).thenThrow(new IllegalArgumentException("ERORR CUENTA NO ENCONTRADA"));
        productController.withdrawMoney(numberAccount, aumont);
    }

    @Test
    void withdrawFail() {
        String numberAccount = "12333333";
        BigDecimal aumont = BigDecimal.valueOf(100);

        when(iProductService.withdrawMoney(any(), any())).thenThrow(new RuntimeException("ERROR NO SE PUDO RETIRAR"));
        productController.withdrawMoney(numberAccount, aumont);
    }

    @Test
    void trasferSuccess() {
        String originNumberAccount = "12333333";
        String destinationNumberAccount = "12333233";
        BigDecimal amount = BigDecimal.valueOf(100);

        doThrow(new IllegalArgumentException("CUENTA NO ENCONTRADA"))
                .when(iProductService).trasferMoney(any(), any(), any());

        ResponseEntity<String> response = productController.trasferMoney(originNumberAccount, destinationNumberAccount, amount);
        String result = response.getBody();
        assertEquals("CUENTA NO ENCONTRADA", result);
    }

    @Test
    void trasferAccountNotFound() {
        String originNumberAccount = "12333333";
        String destinationNumberAccount = "12333233";
        BigDecimal amount = BigDecimal.valueOf(100);

        doThrow(new IllegalArgumentException("CUENTA NO ENCONTRADA"))
                .when(iProductService).trasferMoney(any(), any(), any());

        String result = productController.trasferMoney(originNumberAccount, destinationNumberAccount, amount).getBody();

        assertEquals("CUENTA NO ENCONTRADA", result);
    }

}
