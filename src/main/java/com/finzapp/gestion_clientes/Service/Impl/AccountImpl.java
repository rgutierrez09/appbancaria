package com.finzapp.gestion_clientes.Service.Impl;

import com.finzapp.gestion_clientes.Dto.ClientAccountRequestDto;
import com.finzapp.gestion_clientes.Entity.AccountEntity;
import com.finzapp.gestion_clientes.Entity.ProductEntity;
import com.finzapp.gestion_clientes.Mapper.ProductMapper;
import com.finzapp.gestion_clientes.Repository.AccountRepository;
import com.finzapp.gestion_clientes.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class AccountImpl {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AccountRepository accountRepository;

    public Object createSaveAccount(ClientAccountRequestDto clientAccountRequestDto) {
        if (clientAccountRequestDto.getProductDto().getBalance().compareTo(BigDecimal.ZERO)<0){
            return "El producto no puede ser negativo";
        }
        clientAccountRequestDto.getProductDto().setNumberAccount(null);

        clientAccountRequestDto.getProductDto().setNumberAccount(generateAccountNumber("53"));
        clientAccountRequestDto.getProductDto().setState(clientAccountRequestDto.getProductDto().getState());
        clientAccountRequestDto.getProductDto().setCreationDate(LocalDateTime.now());

        ProductEntity product = ProductMapper.dtoProductEntity(clientAccountRequestDto.getProductDto());
        productRepository.save(product);

        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setTypeAccount(clientAccountRequestDto.getTypeAccount());
        accountEntity.setProductEntity(product);
        accountRepository.save(accountEntity);

        return "CUENTA CREADA";
    }
    public Object createCorrientAccount(ClientAccountRequestDto clientAccountRequestDto) {

        clientAccountRequestDto.getProductDto().setNumberAccount(null);

        clientAccountRequestDto.getProductDto().setNumberAccount(generateAccountNumber("33"));
        clientAccountRequestDto.getProductDto().setState(clientAccountRequestDto.getProductDto().getState());
        clientAccountRequestDto.getProductDto().setCreationDate(LocalDateTime.now());

        ProductEntity product = ProductMapper.dtoProductEntity(clientAccountRequestDto.getProductDto());
        productRepository.save(product);

        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setTypeAccount(clientAccountRequestDto.getTypeAccount());
        accountEntity.setProductEntity(product);
        accountRepository.save(accountEntity);

        return "CUENTA CREADA";
    }

    public String generateAccountNumber(String number) {
        Random random = new Random();
        int randomNumber = 10000000 + random.nextInt(90000000);

        while(productRepository.existsBynumberAccount(number+randomNumber)){
            randomNumber = 10000000 + random.nextInt(90000000);
        }

        return number+randomNumber;
    }
}
