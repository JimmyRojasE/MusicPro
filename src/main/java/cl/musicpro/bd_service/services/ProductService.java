package cl.musicpro.bd_service.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import cl.musicpro.bd_service.model.dto.CommonResponseDTO;
import cl.musicpro.bd_service.model.dto.ProductDTO;
import cl.musicpro.bd_service.model.entities.Product;
import cl.musicpro.bd_service.model.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryService categoryService;
    
    public CommonResponseDTO getProducts(){

        CommonResponseDTO commonResponseDTO=new CommonResponseDTO();

        log.info("Request cl.musicpro.bd_service.services.ProductService[getProducts]");

        List<ProductDTO> listaProducto = new ArrayList<>();

        for(Product product : productRepository.findAll()){
            ProductDTO productDTO = new ProductDTO();

            productDTO.setProduct_id(product.getProduct_id());
            productDTO.setName(product.getName());
            productDTO.setBrand(product.getBrand());
            productDTO.setDescription(product.getDescription());
            productDTO.setModel(product.getModel());
            productDTO.setPrice(product.getPrice());
            productDTO.setSku(product.getSku());
            
            productDTO.setCategory(categoryService.getCategoryById(product.getCategory_id()));
            
            listaProducto.add(productDTO);
           
        }
        commonResponseDTO.setResponseCode("200");
        commonResponseDTO.setResponseMessage("OK");
        commonResponseDTO.setData(listaProducto);

        log.info("[getProducts] Response: "+commonResponseDTO.toString());
        return commonResponseDTO;
     
    }

    public ResponseEntity<CommonResponseDTO> addProduct(ProductDTO productDTO){
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setBrand(productDTO.getBrand());
        product.setDescription(productDTO.getDescription());
        product.setModel(productDTO.getModel());
        product.setPrice(productDTO.getPrice());
        product.setSku(productDTO.getSku());

        Product newProduct = productRepository.save(product);

        CommonResponseDTO commonResponseDTO = new CommonResponseDTO();
        commonResponseDTO.setResponseCode("200");
        commonResponseDTO.setResponseMessage("OK");
        commonResponseDTO.setData(newProduct);

        return new ResponseEntity<CommonResponseDTO>(commonResponseDTO, HttpStatus.OK);
    }
}
