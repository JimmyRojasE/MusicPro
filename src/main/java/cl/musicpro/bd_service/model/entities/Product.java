package cl.musicpro.bd_service.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(schema = "musicpro2023",name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int product_id;
    
    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "brand")
    private String brand;

    @Basic
    @Column(name = "model")
    private String model;

    @Basic
    @Column(name = "description")
    private String description;

    @Basic
    @Column(name = "sku")
    private String sku;

    @Basic
    @Column(name = "price")
    private float price;

    @Basic
    @Column(name = "category_id")
    private int category_id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id", insertable =false, updatable = false)
    @JsonBackReference 
    private Category productByCategoryId_fk;
}