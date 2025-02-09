package com.projects.productservice.models;

import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.List;
import java.util.Properties;

@Getter
@Setter
@Entity
public class Category extends BaseModel {
    private String description;
//    @OneToMany(mappedBy = "category")
//    private List<Product> products;
}