package com.finzapp.gestion_clientes.Controller;


import com.finzapp.gestion_clientes.Dto.ClientDto;
import com.finzapp.gestion_clientes.Service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
public class ClientController {

    @Autowired
    private IClientService iClientService;

    @PostMapping("/create")
    public String createClient(@RequestBody ClientDto clientDto){
        try{
            iClientService.createClient(clientDto);
            return "CLIENTE CREADO CORRECTAMENTE";
        } catch (Exception e) {
            return "ERROR AL CREAR EL CLIENTE";
        }
    }

    @PatchMapping("/update/{numberIdentification}")
    public String updateClient(@PathVariable String numberIdentification, @RequestBody ClientDto clientDto){
        try {
            iClientService.updateClient(numberIdentification,clientDto);
            return "CLIENTE ACTUALIZADO CORRECTAMENTE";
        }catch (IllegalArgumentException e) {
            return "ERROR AL ACTUALIZAR EL CLIENTE";
        }catch (Exception e) {
            return "CUENTA ELIMINADA";
        }
    }

    @DeleteMapping("/delete/{numberIdentification}")
    public String deleteClient(@PathVariable String numberIdentification){
        try {
            iClientService.deleteClient(numberIdentification);
            return "CLIENTE ELIMINADA CORRECTAMENTE";
        }catch (IllegalArgumentException e) {
            return "ERROR AL ELIMINAR EL CLIENTE";
        }catch (Exception e) {
            return "CUENTA ELIMINADA";
        }
    }


}
