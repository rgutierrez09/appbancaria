package com.finzapp.gestion_clientes.Mapper;

import com.finzapp.gestion_clientes.Dto.ClientAccountRequestDto;
import com.finzapp.gestion_clientes.Entity.AccountEntity;
import com.finzapp.gestion_clientes.Entity.ProductEntity;

import java.util.List;
import java.util.stream.Collectors;

public class AccountMapper {

    public static AccountEntity dtoAccountEntity(ClientAccountRequestDto clientAccountRequestDto) {
        ProductEntity productEntity = ProductMapper.dtoProductEntity(clientAccountRequestDto.getProductDto());

        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setProductEntity(productEntity);
        accountEntity.setTypeAccount(clientAccountRequestDto.getTypeAccount());

        return accountEntity;
    }


    public static List<AccountEntity> ListDtoAccountEntity(List<ClientAccountRequestDto> accountDto) {
        return accountDto.stream()
                .map(AccountMapper::dtoAccountEntity)
                .collect(Collectors.toList());
    }

}
