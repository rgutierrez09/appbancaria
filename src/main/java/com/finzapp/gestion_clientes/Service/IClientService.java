package com.finzapp.gestion_clientes.Service;

import com.finzapp.gestion_clientes.Dto.ClientDto;

public interface IClientService {
    Object createClient(ClientDto clientDTO);

    Object updateClient(String numberIdentification, ClientDto clientDTO);

    String deleteClient(String numberIdentification);
}
