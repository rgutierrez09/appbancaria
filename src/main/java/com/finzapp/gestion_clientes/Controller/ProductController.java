package com.finzapp.gestion_clientes.Controller;


import com.finzapp.gestion_clientes.Dto.AccountStateDto;
import com.finzapp.gestion_clientes.Dto.ClientAccountRequestDto;
import com.finzapp.gestion_clientes.Service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/producto")
public class ProductController {

    @Autowired
    private IProductService iProductService;

    @PostMapping("/createcuenta")
    public String createAccount(@RequestBody ClientAccountRequestDto clientAccountRequestDto) {
        try{
            iProductService.createAccount(clientAccountRequestDto);
            return "CUENTA CREADA";
        }catch(Exception e){
            return "ERROR AL CREAR CUENTA";
        }
    }

    @PatchMapping("/updatestateaccount")
    public String updateStatAccount(@RequestBody AccountStateDto accountStateDto) {
        try {
            iProductService.updateStatAccount(accountStateDto);
            return "ACTUALIZADA";
        }catch(Exception e){
            return "ERROR AL ACTUALIZAR CUENTA";
        }
    }

    @PostMapping("/deposit/{numberAccount}")
    public String depositMoney(@PathVariable String numberAccount, @RequestParam BigDecimal balance) {
        try{
            iProductService.depositMoney(numberAccount, balance);
            return "DEPOSITO REALIZADO";
        }catch(IllegalArgumentException e){
            return "CUENTA NO ENCONTRADA";
        }catch(Exception e){
            return "ERROR AL DEPOSITAR";
        }
    }

    @PostMapping("/withdraw/{numberAccount}")
    public String withdrawMoney(@PathVariable String numberAccount, @RequestParam BigDecimal balance) {
       try{
           iProductService.withdrawMoney(numberAccount, balance);
           return "RETIRO REALIZADO CORRECTAMENTE";
       }catch(IllegalArgumentException e){
           return "CUENTA NO ENCONTRADA";
       }catch(Exception e){
           return "ERROR AL RETIRAR";
       }
    }

    @PostMapping ("/cancelAccount")
    public String cancelAccount(@RequestBody AccountStateDto AccountStateDto) {
        try {
            iProductService.cancelAccount(AccountStateDto);
            return "CUENTA CANCELADA CORRECTAMENTE";
        }catch(Exception e){
            return "ERROR AL CANCELAR";
        }
    }

    @PostMapping("/trasfer")
    public String trasferMoney(@RequestParam String accountOrigin, @RequestParam String accountDestination, @RequestParam BigDecimal balance ){
        try {
            iProductService.trasferMoney(accountOrigin, accountDestination, balance);
            return "TRASFERENCIA REALIZADA CORRECTAMENTE";
        }catch(IllegalArgumentException e){
            return "CUENTA NO ENCONTRADA";
        }catch(Exception e){
            return "ERROR AL TRASFERENCIA";
        }
    }

}
