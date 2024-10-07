package com.finzapp.gestion_clientes.Controller;

import com.finzapp.gestion_clientes.Dto.ClientDto;
import com.finzapp.gestion_clientes.Service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
public class ClientController {

    @Autowired
    private IClientService iClientService;

    @PostMapping("/create")
    public ResponseEntity<String> createClient(@RequestBody ClientDto clientDto) {
        try {
            iClientService.createClient(clientDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("CLIENTE CREADO CORRECTAMENTE");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR AL CREAR EL CLIENTE");
        }
    }

    @PatchMapping("/update/{numberIdentification}")
    public ResponseEntity<String> updateClient(@PathVariable String numberIdentification, @RequestBody ClientDto clientDto) {
        try {
            iClientService.updateClient(numberIdentification, clientDto);
            return ResponseEntity.ok("CLIENTE ACTUALIZADO CORRECTAMENTE");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("ERROR AL ACTUALIZAR EL CLIENTE");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("CLIENTE NO ENCONTRADO");
        }
    }

    @DeleteMapping("/delete/{numberIdentification}")
    public ResponseEntity<String> deleteClient(@PathVariable String numberIdentification) {
        try {
            iClientService.deleteClient(numberIdentification);
            return ResponseEntity.ok("CLIENTE ELIMINADO CORRECTAMENTE");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("ERROR AL ELIMINAR EL CLIENTE");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("CLIENTE NO ENCONTRADO");
        }
    }
}
