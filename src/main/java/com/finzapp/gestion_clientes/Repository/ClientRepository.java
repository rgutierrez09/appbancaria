package com.finzapp.gestion_clientes.Repository;

import com.finzapp.gestion_clientes.Entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    Optional<ClientEntity> findClientEntityBynumberIdentification(String numberIdentification);
}
