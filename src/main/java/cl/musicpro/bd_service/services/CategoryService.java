package cl.musicpro.bd_service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.musicpro.bd_service.model.dto.CategoryDTO;
import cl.musicpro.bd_service.model.entities.Category;
import cl.musicpro.bd_service.model.repository.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public CategoryDTO getCategoryById(int category_Id){      
        Category category = categoryRepository.getReferenceById(category_Id);

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategory_id(category.getCategory_id());
        categoryDTO.setName(category.getName());
        
        return categoryDTO;
    }
}
