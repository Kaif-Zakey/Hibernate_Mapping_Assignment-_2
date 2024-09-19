package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Customer {
    @Id
    private int cid;
    private String name;

    @OneToMany(mappedBy = "customer")//inverse end

    /* !Important--->One Customer has Many Addresses*/
    /*(mappedBy = "customer")==== means tell Address to map foreign key by reference*/
    private List<Address> addresses;

    public Customer() {
    }

    public Customer(int cid, String name, List<Address> addresses) {
        this.cid = cid;
        this.name = name;
        this.addresses = addresses;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
