package com.finzapp.gestion_clientes.Mapper;

import com.finzapp.gestion_clientes.Dto.ProductDto;
import com.finzapp.gestion_clientes.Entity.ClientEntity;
import com.finzapp.gestion_clientes.Entity.ProductEntity;

public class ProductMapper {

    public static ProductEntity dtoProductEntity (ProductDto productDto){
       ProductEntity productEntity = new ProductEntity();
       productEntity.setState(productDto.getState());
       productEntity.setBalance(productDto.getBalance());
       productEntity.setNumberAccount(productDto.getNumberAccount());
       productEntity.setClientEntity(ClientEntity.builder().id(productDto.getClientId()).build());
       productEntity.setCreationDate(productDto.getCreationDate());
       productEntity.setExentGMF(productDto.isExentGMF());

        return productEntity;
    }
}

