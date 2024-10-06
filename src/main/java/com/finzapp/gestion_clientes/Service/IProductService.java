package com.finzapp.gestion_clientes.Service;

import com.finzapp.gestion_clientes.Dto.AccountStateDto;
import com.finzapp.gestion_clientes.Dto.ClientAccountRequestDto;

import java.math.BigDecimal;

public interface IProductService {
    Object createCuenta(ClientAccountRequestDto clientAccountRequestDto);
    Object updateEstadoCuenta(AccountStateDto accountStateDto);
    Object cancelarCuenta(AccountStateDto accountStateDto);
    Object consignarDinero(String numeroCuenta, BigDecimal monto);
    Object retirarDinero(String numeroCuenta, BigDecimal monto);
    void tranferirDinero(String origenCuenta, String destinoCuenta, BigDecimal monto);
}
