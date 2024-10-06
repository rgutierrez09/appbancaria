package com.finzapp.gestion_clientes.Repository;

import com.finzapp.gestion_clientes.Entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    Optional<ProductEntity> findProductEntityBynumberAccount(String numberAccount);
    Boolean findBynumberAccount(String number);
    Boolean existsBynumberAccount(String numero);
}
