package com.finzapp.gestion_clientes.Controller;

import com.finzapp.gestion_clientes.Dto.AccountStateDto;
import com.finzapp.gestion_clientes.Dto.ClientAccountRequestDto;
import com.finzapp.gestion_clientes.Service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/producto")
public class ProductController {

    @Autowired
    private IProductService iProductService;

    @PostMapping("/createcuenta")
    public ResponseEntity<String> createAccount(@RequestBody ClientAccountRequestDto clientAccountRequestDto) {
        try {
            iProductService.createAccount(clientAccountRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("CUENTA CREADA");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR AL CREAR CUENTA");
        }
    }

    @PatchMapping("/updatestateaccount")
    public ResponseEntity<String> updateStatAccount(@RequestBody AccountStateDto accountStateDto) {
        try {
            iProductService.updateStatAccount(accountStateDto);
            return ResponseEntity.ok("ACTUALIZADA");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR AL ACTUALIZAR CUENTA");
        }
    }

    @PostMapping("/deposit/{numberAccount}")
    public ResponseEntity<String> depositMoney(@PathVariable String numberAccount, @RequestParam BigDecimal balance) {
        try {
            iProductService.depositMoney(numberAccount, balance);
            return ResponseEntity.ok("DEPOSITO REALIZADO");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("CUENTA NO ENCONTRADA");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR AL DEPOSITAR");
        }
    }

    @PostMapping("/withdraw/{numberAccount}")
    public ResponseEntity<String> withdrawMoney(@PathVariable String numberAccount, @RequestParam BigDecimal balance) {
        try {
            iProductService.withdrawMoney(numberAccount, balance);
            return ResponseEntity.ok("RETIRO REALIZADO CORRECTAMENTE");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("CUENTA NO ENCONTRADA");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR AL RETIRAR");
        }
    }

    @PostMapping("/cancelAccount")
    public ResponseEntity<String> cancelAccount(@RequestBody AccountStateDto accountStateDto) {
        try {
            iProductService.cancelAccount(accountStateDto);
            return ResponseEntity.ok("CUENTA CANCELADA CORRECTAMENTE");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR AL CANCELAR");
        }
    }

    @PostMapping("/trasfer")
    public ResponseEntity<String> trasferMoney(@RequestParam String accountOrigin, @RequestParam String accountDestination, @RequestParam BigDecimal balance) {
        try {
            iProductService.trasferMoney(accountOrigin, accountDestination, balance);
            return ResponseEntity.ok("TRASFERENCIA REALIZADA CORRECTAMENTE");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("CUENTA NO ENCONTRADA");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR AL TRASFERENCIA");
        }
    }
}

