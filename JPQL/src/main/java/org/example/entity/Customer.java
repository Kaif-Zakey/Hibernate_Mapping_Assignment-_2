package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {
    @Id
    private int cid;
    private String name;

    @OneToMany(mappedBy = "customer")//inverse end

    /* !Important--->One Customer has Many Addresses*/
    /*(mappedBy = "customer")==== means tell Address to map foreign key by reference*/
    private List<Address> addresses;

   }
