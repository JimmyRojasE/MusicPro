package cl.musicpro.bd_service.controller;

import cl.musicpro.bd_service.model.dto.CategoryDTO;
import cl.musicpro.bd_service.model.dto.CommonResponseDTO;
import cl.musicpro.bd_service.model.dto.ProductDTO;
import cl.musicpro.bd_service.services.CategoryService;
import cl.musicpro.bd_service.services.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/category-db")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping(path = "/category", produces = MediaType.APPLICATION_JSON_VALUE )
    public CommonResponseDTO getCategory(){
        log.info(" cl.musicpro.bd_service.controller Request:[getCategory]");
        return categoryService.getCategory();
    }

    @GetMapping(path = "/category/{id}", produces =MediaType.APPLICATION_JSON_VALUE )
    public CommonResponseDTO getCategoryOne(@PathVariable int id){
        log.info(" cl.musicpro.bd_service.controller Request:[getCategoryOne/{id}]");
        return categoryService.getByIdCategory(id);
    }

    @PostMapping(path = "/category", produces = MediaType.APPLICATION_JSON_VALUE , consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponseDTO> addCategory(@RequestBody CategoryDTO categoryDTO){
        log.info("[addCategory]Request: "+categoryDTO.toString());
        return categoryService.addCategory(categoryDTO);
    }
    @PutMapping(path = "/category/edit", produces = MediaType.APPLICATION_JSON_VALUE , consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponseDTO>EditCategory(@RequestBody CategoryDTO categoryDTO){
        log.info("[EditCategory]Request: "+categoryDTO.toString());
        return categoryService.editCategory(categoryDTO);
    }
    @DeleteMapping(path = "/category/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE )
    public CommonResponseDTO DeleteCategory(@PathVariable int id){
        log.info("cl.musicpro.bd_service.controller Request:[DeleteCategory/{id}]");
        return categoryService.deleteCategory(id);
    }
}
