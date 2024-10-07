package com.finzapp.gestion_clientes.Service;


import com.finzapp.gestion_clientes.Dto.AccountStateDto;
import com.finzapp.gestion_clientes.Dto.ClientAccountRequestDto;
import com.finzapp.gestion_clientes.Dto.ProductDto;
import com.finzapp.gestion_clientes.Entity.ProductEntity;
import com.finzapp.gestion_clientes.Enum.StatAccount;
import com.finzapp.gestion_clientes.Enum.TypeAccount;
import com.finzapp.gestion_clientes.Repository.AccountRepository;
import com.finzapp.gestion_clientes.Repository.ProductRepository;
import com.finzapp.gestion_clientes.Service.Impl.AccountImpl;
import com.finzapp.gestion_clientes.Service.Impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountImpl accountImpl;

    @InjectMocks
    private ProductServiceImpl productServiceImpl;

    @BeforeEach
    void setUp() {MockitoAnnotations.openMocks(this);}

    @Test
    void CreateDiferentAccount(){
        ClientAccountRequestDto accountRequestDto = new ClientAccountRequestDto();
        ProductDto productDto = new ProductDto();
        accountRequestDto.setProductDto(productDto);
        accountRequestDto.setTypeAccount("CREDITO");

        Object result = productServiceImpl.createAccount(accountRequestDto);
    }

    @Test
    void createAccountSave(){
        ClientAccountRequestDto accountRequestDto = new ClientAccountRequestDto();
        ProductDto productDto = new ProductDto();
        accountRequestDto.setTypeAccount(TypeAccount.AHORROS.name());
        accountRequestDto.setProductDto(productDto);

        productDto.setBalance(BigDecimal.valueOf(-100));

        Object result = productServiceImpl.createAccount(accountRequestDto);

    }

    @Test
    void createAccountCorrient(){
        ClientAccountRequestDto accountRequestDto = new ClientAccountRequestDto();
        ProductDto productDto = new ProductDto();
        accountRequestDto.setTypeAccount(TypeAccount.AHORROS.name());
        accountRequestDto.setProductDto(productDto);

        Object result = productServiceImpl.createAccount(accountRequestDto);
    }


    @Test
    void updateStateAccount(){
        AccountStateDto accountStateDto = new AccountStateDto();
        accountStateDto.setNumberAccount("444");
        accountStateDto.setStats(StatAccount.ACTIVA);

        ProductEntity productEntity = new ProductEntity();

        when(productRepository.findProductEntityBynumberAccount(anyString()))
                .thenReturn(Optional.of(productEntity));
        when(productRepository.save(productEntity))
                .thenReturn(productEntity);

        Object result = productServiceImpl.updateStatAccount(accountStateDto);
        assertNotNull(result);
    }
    @Test
    void updateStatAccountNoFound(){
        AccountStateDto accountStateDto = new AccountStateDto();
        accountStateDto.setNumberAccount("444");

        when(productRepository.findProductEntityBynumberAccount(anyString()))
                    .thenReturn(Optional.empty());

        productServiceImpl.updateStatAccount(accountStateDto);
    }

    @Test
    void canceledAccountSuccess(){
        AccountStateDto accountStateDto = new AccountStateDto();
        accountStateDto.setNumberAccount("444");


        ProductEntity productEntity = new ProductEntity();
        productEntity.setBalance(BigDecimal.valueOf(0.00));

        when(productRepository.findProductEntityBynumberAccount(anyString()))
                .thenReturn(Optional.of(productEntity));

        when(productRepository.save(productEntity))
                .thenReturn(productEntity);

        productServiceImpl.cancelAccount(accountStateDto);
    }

    @Test
    void canceledAccountBalanceMajor(){
        AccountStateDto accountStateDto = new AccountStateDto();
        accountStateDto.setNumberAccount("444");


        ProductEntity productEntity = new ProductEntity();
        productEntity.setBalance(BigDecimal.valueOf(100.00));

        when(productRepository.findProductEntityBynumberAccount(anyString()))
                .thenReturn(Optional.of(productEntity));
        productServiceImpl.cancelAccount(accountStateDto);
    }

    @Test
    void canceledAccountNoFound(){
        AccountStateDto accountStateDto = new AccountStateDto();
        accountStateDto.setNumberAccount("444");

        when(productRepository.findProductEntityBynumberAccount(anyString()))
                        .thenReturn(Optional.empty());

        productServiceImpl.cancelAccount(accountStateDto);
    }

    @Test
    void deposityMoneyCorrect(){
        String numberAccount = "444";
        BigDecimal amount = BigDecimal.valueOf(1000.000);

        ProductEntity productEntity = new ProductEntity();
        productEntity.setBalance(BigDecimal.valueOf(1000.000));

        when(productRepository.findProductEntityBynumberAccount(anyString()))
                .thenReturn(Optional.of(productEntity));
        when(productRepository.save(productEntity))
                .thenReturn(productEntity);

        productServiceImpl.depositMoney(numberAccount, amount);
    }

    @Test
    void deposityMoneyNoFound(){
        String numberAccount = "333";
        BigDecimal amount = BigDecimal.ZERO;

        productServiceImpl.depositMoney(numberAccount, amount);
    }

    @Test
    void deposityMoneyAccountNotFound(){
        String numberAccount = "ooo";
        BigDecimal amount = BigDecimal.TEN;

        when(productRepository.findProductEntityBynumberAccount(numberAccount)).thenReturn(Optional.empty());

        productServiceImpl.depositMoney(numberAccount, amount);
    }
    @Test
    void withdrawMoneyCorrect(){
        String numberAccount = "444";
        BigDecimal amount = BigDecimal.valueOf(1000.000);

        ProductEntity productEntity = new ProductEntity();
        productEntity.setBalance(BigDecimal.valueOf(7000.000));

        when(productRepository.findProductEntityBynumberAccount(anyString()))
                .thenReturn(Optional.of(productEntity));
        when(productRepository.save(productEntity))
                .thenReturn(productEntity);

        productServiceImpl.withdrawMoney(numberAccount, amount);
    }
    @Test
    void withdrawMoneyNoFound(){
        String numberAccount = "333";
        BigDecimal amount = BigDecimal.valueOf(300.000);

        when(productRepository.findProductEntityBynumberAccount(anyString()))
            .thenReturn(Optional.empty());

        productServiceImpl.withdrawMoney(numberAccount, amount);
    }
    @Test
    void withdrawMoneyAccountInsufficient(){
        String numberAccount = "444";
        BigDecimal amount = BigDecimal.valueOf(1000.000);

        ProductEntity productEntity = new ProductEntity();
        productEntity.setBalance(BigDecimal.valueOf(1000.000));

        when(productRepository.findProductEntityBynumberAccount(anyString()))
            .thenReturn(Optional.of(productEntity));
        productServiceImpl.withdrawMoney(numberAccount, amount);
    }
    @Test
    void transferMoney(){
        String OriginNumberAccount = "444";
        String DestinationNumberAccount = "333";
        BigDecimal Amount = BigDecimal.valueOf(1000.000);

        ProductEntity productEntity = new ProductEntity();
        productEntity.setBalance(BigDecimal.valueOf(1000.000));

        when(productRepository.findProductEntityBynumberAccount(anyString()))
                .thenReturn(Optional.of(productEntity));

        when(productRepository.findProductEntityBynumberAccount(anyString()))
                .thenReturn(Optional.of(productEntity));

        when(productRepository.save(productEntity))
                .thenReturn(productEntity);

        productServiceImpl.trasferMoney(OriginNumberAccount, DestinationNumberAccount, Amount);

    }

}
