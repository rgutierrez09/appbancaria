package com.finzapp.gestion_clientes.Service;

import com.finzapp.gestion_clientes.Dto.AccountStateDto;
import com.finzapp.gestion_clientes.Dto.ClientAccountRequestDto;

import java.math.BigDecimal;

public interface IProductService {
    Object createAccount(ClientAccountRequestDto clientAccountRequestDto);
    Object updateStatAccount(AccountStateDto accountStateDto);
    Object cancelAccount(AccountStateDto accountStateDto);
    Object depositMoney(String numeroCuenta, BigDecimal aumont);
    Object withdrawMoney(String numeroCuenta, BigDecimal aumont);
    void trasferMoney(String origenCuenta, String destinoCuenta, BigDecimal aumont);
}
