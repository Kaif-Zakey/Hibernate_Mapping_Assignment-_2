package org.example;

import org.example.config.FactoryConfiguration;
import org.example.entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class Main {
    public static void main(String[] args) {


        Session session= FactoryConfiguration.getInstance().getSession();
        Transaction transaction= session.beginTransaction();

        /*Address address1 = new Address();
        address1.setAid(1);
        address1.setNo("100/A");
        address1.setRoad("Galle Rd");
        address1.setCity("Colombo");

        Address address2 = new Address();
        address1.setAid(2);
        address1.setNo("8/A");
        address1.setRoad("Galle Rd");
        address1.setCity("Panadura");

        List<Address>addresses=new ArrayList<>();
        addresses.add(address1);
        addresses.add(address2);

        Customer customer =new Customer();
        customer.setCid(1);
        customer.setName("Sam");
        customer.setAddresses(addresses);

        address1.setCustomer(customer);
        address2.setCustomer(customer);

        session.save(address1);
        session.save(address2);
        session.save(customer);*/

        //HQL

        //unique one data from customer
        /*Query query = session.createQuery("from Customer where cid = ?1");
        query1.setParameter(1,"1");
        Customer customer=(Customer) query.uniqueResult();
        System.out.println(customer.getCid());*/


        //load only one column ***join query
        /*Query query = session.createQuery("select cid,name from Customer where name=?1");
        query.setParameter("1",1);
        Customer customer=(Customer) query.uniqueResult();
        System.out.println(customer.getCid());*/

        //insert,update,delete,Search by name

        //insert - no HQL Query for insert
        Customer customers = new Customer();
        customers.setCid(2);
        customers.setName("kal");

        session.save(customers);

        //update
        Query query1 = session.createQuery("update Customer c set c.name = :name where c.cid = :cid");
        query1.setParameter("name", "kals");
        query1.setParameter("cid", 2);
        query1.executeUpdate();

        //delete
        Query query2 = session.createQuery("delete from Customer c where c.cid = :cid");
        query2.setParameter("cid", 2);
        query2.executeUpdate();

        //all data from customer
        Query query =session.createQuery("from Customer");
        List<Customer>customerList=query.list();

        for(Customer customer: customerList){
            System.out.println(customer.getCid());
            System.out.println(customer.getName());
        }

        //search by name
        Query query3 = session.createQuery("from Customer c where c.name = :name");
        query3.setParameter("name", "Sam");

        List<Customer>customerLists = query3.list();

        for (Customer customerz:customerLists){
            System.out.println(customerz.getCid());
        }

        //join query
        Query query4 = session.createQuery("select c.cid, c.name, a.aid, a.no, a.road, a.city from Customer c inner join c.addresses a");

        List<Object[]> resultList = query4.list();

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