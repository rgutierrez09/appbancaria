package com.finzapp.gestion_clientes.Service;

import com.finzapp.gestion_clientes.Dto.ClientAccountRequestDto;
import com.finzapp.gestion_clientes.Dto.ProductDto;
import com.finzapp.gestion_clientes.Entity.AccountEntity;
import com.finzapp.gestion_clientes.Entity.ProductEntity;
import com.finzapp.gestion_clientes.Enum.StatAccount;
import com.finzapp.gestion_clientes.Repository.AccountRepository;
import com.finzapp.gestion_clientes.Repository.ProductRepository;
import com.finzapp.gestion_clientes.Service.Impl.AccountImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountImplTest {

    @InjectMocks
    private AccountImpl accountImpl;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private AccountRepository accountRepository;

    @BeforeEach
    public void setUp() {
    }

    @Test
    void createAccountWithBalanceNegative(){
        ClientAccountRequestDto requestDto = new ClientAccountRequestDto();
        ProductDto productDto = new ProductDto();
        productDto.setBalance(new BigDecimal("-100"));
        requestDto.setProductDto(productDto);

        accountImpl.createSaveAccount(requestDto);
    }

    @Test
    void createAccountSave(){
        ClientAccountRequestDto requestDto = new ClientAccountRequestDto();
        requestDto.setTypeAccount("AHORRO");
        requestDto.setProductDto(new ProductDto());
        requestDto.getProductDto().setBalance(BigDecimal.valueOf(1000));

        when(productRepository.save(any(ProductEntity.class))).thenReturn(new ProductEntity());
        when(accountRepository.save(any(AccountEntity.class))).thenReturn(new AccountEntity());

        accountImpl.createSaveAccount(requestDto);
    }

    @Test
    void createAccountCorrient(){
        ClientAccountRequestDto requestDto = new ClientAccountRequestDto();
        requestDto.setTypeAccount("CORRIENTE");
        requestDto.setProductDto(new ProductDto());
        requestDto.setProductDto(new ProductDto());
        requestDto.getProductDto().setState(StatAccount.ACTIVA);

        when(productRepository.save(any(ProductEntity.class))).thenReturn(new ProductEntity());

        when(accountRepository.save(any(AccountEntity.class))).thenReturn(new AccountEntity());
        accountImpl.createSaveAccount(requestDto);
    }

    @Test
    void generateRandomAccountNumber_NumberNoexist(){
        when(productRepository.existsBynumberAccount(any())).thenReturn(false);
        accountImpl.generateAccountNumber("53");
    }
}
