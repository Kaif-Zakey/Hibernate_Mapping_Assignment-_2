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

        //Insert
//
        NativeQuery<?> insertQuery=session.createNativeQuery("INSERT INTO  customer(cid,name)values (?,?)");
        insertQuery.setParameter(1,1);
        insertQuery.setParameter(2,"zaki");
//        int insertedRow =insertQuery.executeUpdate();
//        System.out.println("Inserted Row: "+insertedRow);

        NativeQuery<?> insertQuery2=session.createNativeQuery("INSERT INTO  customer(cid,name)values (?,?)");
        insertQuery2.setParameter(1,2);
        insertQuery2.setParameter(2,"Kalana");
//        int insertedRow =insertQuery2.executeUpdate();
//        System.out.println("Inserted Row: "+insertedRow);

        NativeQuery<?> insertQuery1=session.createNativeQuery("INSERT INTO address(aid,no,road,city,Customer_Id)VALUES (?,?,?,?,?)");
        insertQuery1.setParameter(1,1);
        insertQuery1.setParameter(2,"312/11");
        insertQuery1.setParameter(3,"Main Rd");
        insertQuery1.setParameter(4,"Colombo");
        insertQuery1.setParameter(5,1);
//        int insertdRow1=insertQuery1.executeUpdate();
//        System.out.println("Inserted Row: "+insertdRow1);



        //Select

        NativeQuery<Customer>selectQuery= session.createNativeQuery("SELECT * FROM customer", Customer.class);
        List<Customer>customerList=selectQuery.list();
        for (Customer customer : customerList){
            System.out.println(customer.getCid()+" : "+customer.getName());
        }



        //update

        NativeQuery<?>upadteQuery=session.createNativeQuery("UPDATE  customer SET name=? WHERE cid=?");
        upadteQuery.setParameter(1,"kaif");
        upadteQuery.setParameter(2,1);
//        int updatedRow=upadteQuery.executeUpdate();
//        System.out.println("updated Row"+updatedRow);



        //Delete
        NativeQuery<?>deleteQuery=session.createNativeQuery("DELETE FROM customer WHERE cid=?",Customer.class);
        deleteQuery.setParameter(1,2);
//        int deleted=deleteQuery.executeUpdate();
//        System.out.println("Deleted : "+deleted);



        //Search by name
//        NativeQuery<Customer>serchByName=session.createNativeQuery("SELECT * FROM customer WHERE name=?", Customer.class);
//        serchByName.setParameter(1,"kaif");
//        List<Customer>customerList1=serchByName.list();
//        for (Customer customers:customerList1){
//            System.out.println("Searched By Name = "+"name : "+customers.getName()+" "+"id : "+customers.getCid());
//        }

        //Search By ID
//        NativeQuery<Customer> searchByIdQuery = session.createNativeQuery("SELECT * FROM customer WHERE cid = ?", Customer.class);
//        searchByIdQuery.setParameter(1, 1);
//        Customer customerById = (Customer) searchByIdQuery.uniqueResult();
//        if (customerById != null) {
//            System.out.println("Founded : " + customerById.getCid() + "  " + customerById.getName());
//        } else {
//            System.out.println("No customer found !");
//        }

        //Join Query
        String joinQuery ="SELECT c.cid, c.name, a.aid, a.no, a.road, a.city " +
                "FROM customer c " +
                "LEFT JOIN address a ON c.cid = a.customer_id";

        NativeQuery<?>joinNativeQuery = session.createNativeQuery(joinQuery);
        List<Object[]> joinResults = (List<Object[]>) joinNativeQuery.list();

        System.out.println("Join Query Result : ");
        for (Object[] result:joinResults){
            Integer customerId=(Integer) result[0];
            String customerName = (String) result[1];
            Integer addressId = (Integer) result[2];
            String no = (String) result[3];
            String road = (String) result[4];
            String city = (String) result[5];


            System.out.println(
                    "Customer ID: " + customerId +
                    ", Name: " + customerName +
                    ", Address ID: " + (addressId != null ? addressId : "N/A") +
                    ", No: " + (no != null ? no : "N/A") +
                    ", Road: " + (road != null ? road : "N/A") +
                    ", City: " + (city != null ? city : "N/A"));

        }



        transaction.commit();
        session.close();

    }
}