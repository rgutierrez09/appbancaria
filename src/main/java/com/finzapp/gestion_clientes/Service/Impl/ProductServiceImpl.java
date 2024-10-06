package com.finzapp.gestion_clientes.Service.Impl;


import com.finzapp.gestion_clientes.Dto.AccountStateDto;
import com.finzapp.gestion_clientes.Dto.ClientAccountRequestDto;
import com.finzapp.gestion_clientes.Entity.ProductEntity;
import com.finzapp.gestion_clientes.Enum.StatAccount;
import com.finzapp.gestion_clientes.Enum.TypeAccount;
import com.finzapp.gestion_clientes.Repository.ProductRepository;
import com.finzapp.gestion_clientes.Service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AccountImpl cAccount;

    @Override
    public Object createAccount(ClientAccountRequestDto clientAccountRequestDto) {
        clientAccountRequestDto.getProductDto().setNumberAccount(null);
        String numberAccount;

        if (clientAccountRequestDto.getTypeAccount().equals(TypeAccount.AHORROS.name())){
            numberAccount = cAccount.generateAccountNumber("53");
            clientAccountRequestDto.getProductDto().setNumberAccount(numberAccount);
            return cAccount.createSaveAccount(clientAccountRequestDto);
        } else if (clientAccountRequestDto.getTypeAccount().equals(TypeAccount.CORRIENTE.name())) {
            numberAccount = cAccount.generateAccountNumber("33");
            clientAccountRequestDto.getProductDto().setNumberAccount(numberAccount);
            return cAccount.createCorrientAccount(clientAccountRequestDto);


        }
        return "ERORR, NO SE PUEDO CREAR LA CUENTA";
    }

    @Override
    public Object updateStatAccount(AccountStateDto accountStateDto) {
        Optional<ProductEntity>productEntity = productRepository.findProductEntityBynumberAccount(accountStateDto.getNumberAccount());

        if (productEntity.isPresent()) {
            productEntity.get().setState(accountStateDto.getStats());
            productEntity.get().setModificationDate(LocalDateTime.now());
            productRepository.save(productEntity.get());
            return "SE ACTUALIZO CORRECTAMENTE";
        }
        return "ERRO, CUENTA NO ENCONTRADA";
    }

    @Override
    public Object cancelAccount(AccountStateDto accountStateDto) {
        Optional<ProductEntity>productEntity = productRepository.findProductEntityBynumberAccount(accountStateDto.getNumberAccount());

        if (productEntity.isPresent()) {
            if (productEntity.get().getBalance().compareTo(BigDecimal.ZERO)==0){
                productEntity.get().setState(StatAccount.CANCELADA);
                productEntity.get().setModificationDate(LocalDateTime.now());
                productRepository.save(productEntity.get());
                return "CUENTA CANCELADA";
            }
            return "ERROR, LA CUENTA NO SE PUEDE CANCELAR";
        }
        return "ERRO, CUENTA NO ENCONTRADA";
    }

    @Override
    public Object depositMoney(String numberAccount, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO)<=0){
            return "ERRO, CUENTA NO SE PUEDE DEPOSITAR";
        }

        Optional<ProductEntity>productEntity = productRepository.findProductEntityBynumberAccount(numberAccount);
        if (productEntity.isEmpty()) {
            return "ERRO, CUENTA NO ENCONTRADA";
        }
        BigDecimal newBalance = productEntity.get().getBalance().add(amount);
        productEntity.get().setBalance(newBalance);
        productEntity.get().setModificationDate(LocalDateTime.now());

        return productRepository.save(productEntity.get());
    }

    @Override
    public Object withdrawMoney(String numberAccount, BigDecimal amount) {
        Optional<ProductEntity>productEntity = productRepository.findProductEntityBynumberAccount(numberAccount);

        if (productEntity.isEmpty()){
            return "ERRO, CUENTA NO ENCONTRADA";
        }
        if (amount.compareTo(BigDecimal.ZERO)>=0){
            productEntity.get().setBalance(productEntity.get().getBalance().subtract(amount));
            productEntity.get().setModificationDate(LocalDateTime.now());

            return productRepository.save(productEntity.get());
        }else {
            return "ERRO, CUENTA NO SE PUEDE DEPOSITAR";
        }
    }

    @Override
    public void trasferMoney(String originNumberAccount, String destinationNumberAccount, BigDecimal amount) {
        withdrawMoney(originNumberAccount, amount);
        depositMoney(destinationNumberAccount, amount);
    }

}
