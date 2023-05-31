package cl.musicpro.bd_service.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDTO {
   
    private int product_id;
    private String name;
    private String brand;
    private String model;
    private String description;
    private String sku;
    private float price;
    @JsonProperty("categoria_instrumento")
    private CategoryDTO category;
    
}
