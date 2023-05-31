package cl.musicpro.bd_service.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.musicpro.bd_service.model.entities.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer>{
    
}
