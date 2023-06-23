package cl.musicpro.bd_service.services;

import cl.musicpro.bd_service.model.dto.CommonResponseDTO;
import cl.musicpro.bd_service.model.dto.ProductDTO;
import cl.musicpro.bd_service.model.entities.Product;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import cl.musicpro.bd_service.model.dto.CategoryDTO;
import cl.musicpro.bd_service.model.entities.Category;
import cl.musicpro.bd_service.model.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@Service
@Log4j2
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;




    public CommonResponseDTO getCategory(){
        CommonResponseDTO commonResponseDTO=new CommonResponseDTO();
        log.info("Request cl.musicpro.bd_service.services.CategoryService[getCategory]");
        List<CategoryDTO> listaCategoria = new ArrayList<>();

        for(Category category : categoryRepository.findAll()){
            listaCategoria.add(Parser_Category_DTO(category));
        }

        commonResponseDTO.setResponseCode("200");
        commonResponseDTO.setResponseMessage("OK");
        commonResponseDTO.setData(listaCategoria);


        
        return commonResponseDTO;
    }

    public CategoryDTO getCategoryById(int id){
        log.info("Request cl.musicpro.bd_service.services.CategoryService[getCategoryById]");
        Optional<Category> category;
        category= categoryRepository.findById(id);
        CategoryDTO categoryDTO = new CategoryDTO();
        if (category.isPresent()) {
            categoryDTO=Parser_Category_DTO(category.get());
        }

        return categoryDTO;
    }
    public CommonResponseDTO getByIdCategory(int id){
        CommonResponseDTO commonResponseDTO=new CommonResponseDTO();
        log.info("Request cl.musicpro.bd_service.services.CategoryService[getByIdCategory]");
        Optional<Category> category;
        category= categoryRepository.findById(id);
        CategoryDTO categoryDTO = new CategoryDTO();
        if (category.isPresent()) {
            categoryDTO=Parser_Category_DTO(category.get());
        }



        commonResponseDTO.setResponseCode("200");
        commonResponseDTO.setResponseMessage("OK");
        commonResponseDTO.setData(categoryDTO);

        log.info("[getProduct] Response: "+commonResponseDTO.toString());
        return commonResponseDTO;
    }
    public CommonResponseDTO deleteCategory(int id){
        CommonResponseDTO commonResponseDTO = new CommonResponseDTO();
        log.info("Request cl.musicpro.bd_service.services.CategoryService[deleteCategory]");
        Optional<Category> category;
        category= categoryRepository.findById(id);
        CategoryDTO categoryDTO = new CategoryDTO();
        if (category.isPresent()) {
            categoryDTO=Parser_Category_DTO(category.get());
        }

        categoryRepository.delete(category.get());



        commonResponseDTO.setResponseCode("200");
        commonResponseDTO.setResponseMessage("OK");
        commonResponseDTO.setData(categoryDTO);

        log.info("[deleteCategory] Response: "+commonResponseDTO.toString());
        return commonResponseDTO;
    }
    public ResponseEntity<CommonResponseDTO> addCategory(CategoryDTO categoryDTO){
        log.info("Request cl.musicpro.bd_service.services.CategoryService[addCategory]");
        Category category = Parser_DTO_Category(categoryDTO);
        log.info("Request cl.musicpro.bd_service.services.CategoryService[addCategory(CREANDO UN NUEVO CATEGORIA)]");
        Category newCategory = categoryRepository.save(category);

        CommonResponseDTO commonResponseDTO = new CommonResponseDTO();
        commonResponseDTO.setResponseCode("200");
        commonResponseDTO.setResponseMessage("OK");
        commonResponseDTO.setData(newCategory);
        log.info("Request cl.musicpro.bd_service.services.CategoryService[addCategory(RETORNA COMMONRESPONSEDTO DEL CATEGORIA CREADO)]");
        return new ResponseEntity<CommonResponseDTO>(commonResponseDTO, HttpStatus.OK);
    }
    public ResponseEntity<CommonResponseDTO> editCategory(CategoryDTO categoryDTO){
        log.info("Request cl.musicpro.bd_service.services.CategoryService[editCategory]");
        Category category = Parser_DTO_Category(categoryDTO);
        log.info("Request cl.musicpro.bd_service.services.CategoryService[editCategory(ACTUALIZANDO CATEGORIA)]");
        Category newCategory = categoryRepository.save(category);
        CommonResponseDTO commonResponseDTO = new CommonResponseDTO();
        commonResponseDTO.setResponseCode("200");
        commonResponseDTO.setResponseMessage("OK");
        commonResponseDTO.setData(newCategory);
        log.info("Request cl.musicpro.bd_service.services.CategoryService[editCategory(RETORNA COMMONRESPONSEDTO DEL CATEGORIA ACTUALIZADO)]");
        return new ResponseEntity<CommonResponseDTO>(commonResponseDTO, HttpStatus.OK);
    }

    public Category Parser_DTO_Category(CategoryDTO categoryDTO){
        Category category= new Category();
        if (categoryDTO.getCategory_id()!=0){
            category.setCategory_id(categoryDTO.getCategory_id());
        }
        category.setName(categoryDTO.getName());


        return category;
    }

    public CategoryDTO Parser_Category_DTO(Category category){
        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setCategory_id(category.getCategory_id());
        categoryDTO.setName(category.getName());


        return categoryDTO;
    }

}
