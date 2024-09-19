package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Address {
    @Id
    private int aid;
    private String no;
    private String road;
    private String city;

    @ManyToOne/*Many addresses have one Customer*/  //owner's end
    @JoinColumn(name = "Customer_Id")
    private Customer cid;

    }
