package cl.musicpro.bd_service.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            listaProducto.add(Parser_Product_DTO(product));
        }
        commonResponseDTO.setResponseCode("200");
        commonResponseDTO.setResponseMessage("OK");
        commonResponseDTO.setData(listaProducto);

        log.info("[getProducts] Response: "+commonResponseDTO.toString());
        return commonResponseDTO;
    }

    public CommonResponseDTO getProduct(int id){
        CommonResponseDTO commonResponseDTO=new CommonResponseDTO();
        log.info("Request cl.musicpro.bd_service.services.ProductService[getProduct]");
        Optional<Product> product;
        product= productRepository.findById(id);
        ProductDTO productDTO = new ProductDTO();
        if (product.isPresent()) {
            productDTO=Parser_Product_DTO(product.get());
        }



        commonResponseDTO.setResponseCode("200");
        commonResponseDTO.setResponseMessage("OK");
        commonResponseDTO.setData(productDTO);

        log.info("[getProduct] Response: "+commonResponseDTO.toString());
        return commonResponseDTO;
    }
    public CommonResponseDTO deleteProduct(int id){
        CommonResponseDTO commonResponseDTO=new CommonResponseDTO();
        log.info("Request cl.musicpro.bd_service.services.ProductService[deleteProduct]");
        Optional<Product> product;
        product= productRepository.findById(id);
        ProductDTO productDTO = new ProductDTO();
        if (product.isPresent()) {
            productDTO=Parser_Product_DTO(product.get());
        }
        productRepository.delete(product.get());



        commonResponseDTO.setResponseCode("200");
        commonResponseDTO.setResponseMessage("OK");
        commonResponseDTO.setData(productDTO);

        log.info("[deleteProduct] Response: "+commonResponseDTO.toString());
        return commonResponseDTO;
    }
    public ResponseEntity<CommonResponseDTO> addProduct(ProductDTO productDTO){
        log.info("Request cl.musicpro.bd_service.services.ProductService[addProduct]");
        Product product = Parser_DTO_Product(productDTO);
        log.info("Request cl.musicpro.bd_service.services.ProductService[addProduct(CREANDO UN NUEVO PRODUCTO)]");
        Product newProduct = productRepository.save(product);

        CommonResponseDTO commonResponseDTO = new CommonResponseDTO();
        commonResponseDTO.setResponseCode("200");
        commonResponseDTO.setResponseMessage("OK");
        commonResponseDTO.setData(newProduct);
        log.info("Request cl.musicpro.bd_service.services.ProductService[addProduct(RETORNA COMMONRESPONSEDTO DEL PRODUCTO CREADO)]");
        return new ResponseEntity<CommonResponseDTO>(commonResponseDTO, HttpStatus.OK);
    }
    public ResponseEntity<CommonResponseDTO> editProduct(ProductDTO productDTO){
        log.info("Request cl.musicpro.bd_service.services.ProductService[editProduct]");
        Product product = Parser_DTO_Product(productDTO);
        log.info("Request cl.musicpro.bd_service.services.ProductService[editProduct(ACTUALIZANDO PRODUCTO)]");
        Product newProduct = productRepository.save(product);
        CommonResponseDTO commonResponseDTO = new CommonResponseDTO();
        commonResponseDTO.setResponseCode("200");
        commonResponseDTO.setResponseMessage("OK");
        commonResponseDTO.setData(newProduct);
        log.info("Request cl.musicpro.bd_service.services.ProductService[addProduct(RETORNA COMMONRESPONSEDTO DEL PRODUCTO ACTUALIZADO)]");
        return new ResponseEntity<CommonResponseDTO>(commonResponseDTO, HttpStatus.OK);
    }

    public Product Parser_DTO_Product(ProductDTO productDTO){
        Product product = new Product();
        if (productDTO.getProduct_id()!=0){
            product.setProduct_id(productDTO.getProduct_id());
        }

        product.setName(productDTO.getName());
        product.setBrand(productDTO.getBrand());
        product.setDescription(productDTO.getDescription());
        product.setModel(productDTO.getModel());
        product.setPrice(productDTO.getPrice());
        product.setSku(productDTO.getSku());
        product.setCategory_id(productDTO.getCategory().getCategory_id());

        return product;
    }

    public ProductDTO Parser_Product_DTO(Product product){
        ProductDTO productDTO = new ProductDTO();

        productDTO.setProduct_id(product.getProduct_id());
        productDTO.setName(product.getName());
        productDTO.setBrand(product.getBrand());
        productDTO.setDescription(product.getDescription());
        productDTO.setModel(product.getModel());
        productDTO.setPrice(product.getPrice());
        productDTO.setSku(product.getSku());

        productDTO.setCategory(categoryService.getCategoryById(product.getCategory_id()));

        return productDTO;
    }
}
