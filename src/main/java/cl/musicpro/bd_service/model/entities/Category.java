package cl.musicpro.bd_service.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table(schema = "musicpro2023",name= "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "category_id")
    private int category_id;
    @Basic
    @Column (name = "name")
    private String name;

    @OneToMany(mappedBy = "productByCategoryId_fk", fetch = FetchType.LAZY)
    @Column(nullable = true)
    @JsonManagedReference
    private Collection<Product> productByCategoryId;
}
