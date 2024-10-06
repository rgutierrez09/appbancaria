package com.finzapp.gestion_clientes.Controller;


import com.finzapp.gestion_clientes.Dto.ClientDto;
import com.finzapp.gestion_clientes.Service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
public class ClientController {

    @Autowired
    private IClientService iClientService;

    @PostMapping("/create")
    public String CreateClient(@RequestBody ClientDto clientDto){
        try{

        }
    }

}
