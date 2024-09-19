package org.example;


import org.example.config.FactoryConfiguration;
import org.example.entity.Address;
import org.example.entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Session session= FactoryConfiguration.getInstance().getSession();
        Transaction transaction= session.beginTransaction();

//        Address address1 = new Address();
//        address1.setAid(1);
//        address1.setNo("314/11");
//        address1.setRoad("Main Rd");
//        address1.setCity("Colombo");
//
//        Address address2 = new Address();
//        address2.setAid(2);
//        address2.setNo("8/A");
//        address2.setRoad("Main Rd");
//        address2.setCity("Panadura");
//
//        List<Address>addresses=new ArrayList<>();
//        addresses.add(address1);
//        addresses.add(address2);
//
//        Customer customer =new Customer();
//        customer.setCid(1);
//        customer.setName("Shaan");
//        customer.setAddresses(addresses);
//
//        address1.setCustomer(customer);
//        address2.setCustomer(customer);
//
//        session.save(address1);
//        session.save(address2);
//        session.save(customer);


//        // Insert (In JPQL, no query for insert)
//        Customer customer = new Customer();
//        customer.setCid(2);
//        customer.setName("Kal");
//
//        session.save(customer);

        // Update
        Query query2 = session.createQuery("UPDATE Customer c SET c.name = :name WHERE c.cid = :cid");
        query2.setParameter("name", "Kals");
        query2.setParameter("cid", 2);
        query2.executeUpdate();


        // Delete
        Query query3 = session.createQuery("DELETE FROM Customer c WHERE c.cid = :cid");
        query3.setParameter("cid", 2);
        int deleteResult = query3.executeUpdate();
        System.out.println("Rows affected by delete: " + deleteResult);

        // Select All
        Query query4 = session.createQuery("FROM Customer");
        List<Customer> customerList = query4.getResultList();

        for (Customer c : customerList) {
            System.out.println("Customer ID: " + c.getCid());
            System.out.println("Customer Name: " + c.getName());
        }

        // Search by Name
        Query query5 = session.createQuery("FROM Customer c WHERE c.name = :name");
        query5.setParameter("name", "Sam");
        List<Customer> customersByName = query5.getResultList();

        for (Customer c : customersByName) {
            System.out.println("Customer ID: " + c.getCid());
            System.out.println("Customer Name: " + c.getName());
        }

        // Join Query
        Query query6 = session.createQuery("SELECT c.cid, c.name, a.aid, a.no, a.road, a.city  FROM Customer c JOIN c.addresses a");

        List<Object[]> resultList = query6.getResultList();

        for (Object[] row : resultList) {
            System.out.println("Customer ID: " + row[0]);
            System.out.println("Customer Name: " + row[1]);
            System.out.println("Address ID: " + row[2]);
            System.out.println("Address No: " + row[3]);
            System.out.println("Road: " + row[4]);
            System.out.println("City: " + row[5]);
        }




        transaction.commit();
        session.close();

    }
}