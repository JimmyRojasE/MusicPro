package cl.musicpro.bd_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cl.musicpro.bd_service.model.dto.CommonResponseDTO;
import cl.musicpro.bd_service.model.dto.ProductDTO;
import cl.musicpro.bd_service.services.ProductService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/products-db")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping(path = "/products", produces =MediaType.APPLICATION_JSON_VALUE )
    public CommonResponseDTO getAll(){
        log.info(" cl.musicpro.bd_service.controller Request:[getAll]");
        return productService.getProducts();
    }

    @GetMapping(path = "/product/{id}", produces =MediaType.APPLICATION_JSON_VALUE )
    public CommonResponseDTO getProduct(@PathVariable int id){
        log.info(" cl.musicpro.bd_service.controller Request:[getProduct/{id}]");
        return productService.getProduct(id);
    }
    
    @PostMapping(path = "/product", produces = MediaType.APPLICATION_JSON_VALUE , consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponseDTO>addProduct(@RequestBody ProductDTO productoDTO){
        log.info("[addProduct]Request: "+productoDTO.toString());
        return productService.addProduct(productoDTO);
    }

    @PostMapping(path = "/product/edit", produces = MediaType.APPLICATION_JSON_VALUE , consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponseDTO>EditProduct(@RequestBody ProductDTO productoDTO){
        log.info("[editProduct]Request: "+productoDTO.toString());
        return productService.editProduct(productoDTO);
    }

    @DeleteMapping(path = "/product/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE )
    public CommonResponseDTO DeleteProduct(@PathVariable int id){
        log.info("cl.musicpro.bd_service.controller Request:[DeleteProduct/{id}]");
        return productService.deleteProduct(id);
    }
}
