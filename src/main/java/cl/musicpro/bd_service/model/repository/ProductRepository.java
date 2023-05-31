package cl.musicpro.bd_service.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.musicpro.bd_service.model.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {


    
}
