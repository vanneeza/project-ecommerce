package com.ecommerce.app.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;

@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FilterDef(name = "deletedProductFilter", parameters = @ParamDef(name="isDeleted", type = java.lang.Boolean.class))
@Filter(name = "deletedProductFilter", condition = "is_deleted = :isDeleted")
@SQLDelete(sql = "UPDATE product SET is_deleted = true WHERE id = ?")
@Where(clause = "is_deleted = false")
public class Product {
    @Id
    @UuidGenerator
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "UUID")
    @GenericGenerator(name = "UUID", type = org.hibernate.id.uuid.UuidGenerator.class)
    private String id;
    private String name;
    private Integer stock;
    private Integer price;

    @JsonProperty("is_deleted")
    private boolean is_deleted = Boolean.FALSE;

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", stock=" + stock +
                ", price=" + price +
                ", is_deleted=" + is_deleted +
                '}';
    }
}
