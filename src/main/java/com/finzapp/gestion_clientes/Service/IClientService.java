package com.finzapp.gestion_clientes.Service;

import com.finzapp.gestion_clientes.Dto.ClientDto;

public interface IClientService {
    Object createCliente(ClientDto clientDTO);

    Object updateCliente(String numberIdentification, ClientDto clientDTO);

    String deleteCliente(String numberIdentification);
}
