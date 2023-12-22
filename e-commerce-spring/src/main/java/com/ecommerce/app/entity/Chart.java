package com.ecommerce.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "chart")
@SQLDelete(sql = "UPDATE chart SET is_deleted = true Where id = ?")
@SQLRestriction("is_deleted = false")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Chart {
    @Id
    @UuidGenerator
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "UUID")
    @GenericGenerator(name = "UUID", type = org.hibernate.id.uuid.UuidGenerator.class)
    private String id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "chart")
    @JsonIgnoreProperties("chart")
    private List<ChartItem> items = new ArrayList<>();
    private String status;

    private final Boolean is_deleted = Boolean.FALSE;

    @Override
    public String toString() {
        return "Chart{" +
                "id='" + id + '\'' +
                ", customer=" + customer +
                ", status='" + status + '\'' +
                '}';
    }
}
