package org.example;

import org.example.config.FactoryConfiguration;
import org.example.entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

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
        address2.setAid(2);
        address2.setNo("8/A");
        address2.setRoad("Galle Rd");
        address2.setCity("Panadura");

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



        //insert,update,delete,Search by name

        //insert
        NativeQuery query1 = session.createNativeQuery("insert into customer (cid, name) values (:cid, :name)");
        query1.setParameter("cid", 2);
        query1.setParameter("name", "Kal");
        query1.executeUpdate();

        //update
        NativeQuery query2 = session.createNativeQuery("update customer set name = :name where cid = :cid");
        query2.setParameter("name","kals");
        query2.setParameter("cid",2);
        query2.executeUpdate();

        //delete
        NativeQuery query3 = session.createNativeQuery("delete from customer where cid = :cid");
        query3.setParameter("cid",2);
        query3.executeUpdate();

        //select all
        NativeQuery query = session.createNativeQuery("select * from customer");

        query.addEntity(Customer.class);

        List<Customer> customerList=query.list();

        for(Customer customer:customerList){

            System.out.println(customer.getCid());
            System.out.println(customer.getName());

        }

        //search by name
        NativeQuery query4 = session.createNativeQuery(" select * from customer where name = :name");
        query4.setParameter("name","sam");
        query4.addEntity(Customer.class);

        List<Customer> customerLists=query4.list();

        for(Customer customer:customerLists){

            System.out.println(customer.getCid());
            System.out.println(customer.getName());

        }

        //Join Query
        NativeQuery query5 = session.createNativeQuery("select c.cid, c.name, a.aid, a.no, a.road, a.city from customer c inner join address a on c.cid = a.customer_ID");


        List<Object[]> resultList = query5.list();

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