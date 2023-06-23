package cl.musicpro.bd_service.model.dto;

import cl.musicpro.bd_service.model.entities.Product;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.Collection;

@Data
@NoArgsConstructor
public class CategoryDTO {
    @JsonProperty("category_id")
    private int category_id;
    @JsonProperty("category_name")
    private String name;

}
