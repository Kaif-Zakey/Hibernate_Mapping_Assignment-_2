package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Customer {
    @Id
    private int cid;
    private String name;

    @OneToMany(mappedBy = "cid",fetch = FetchType.EAGER)   //inverse end
     private List<Address> addresses;   /* !Important--->One Customer has Many Addresses*/
                                        /*(mappedBy = "customer")==== means tell Address to map foreign key
                                        by reference*/

}
