package org.example.config;


import org.example.entity.Address;
import org.example.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {

    private static FactoryConfiguration factoryConfiguaration;

    private SessionFactory sessionFactory;

    private FactoryConfiguration(){
        Configuration configuration = new Configuration().configure().addAnnotatedClass(Customer.class).addAnnotatedClass(Address.class);
        sessionFactory= configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance(){
        return (factoryConfiguaration==null)? factoryConfiguaration=new FactoryConfiguration():factoryConfiguaration;
    }

    public Session getSession(){
        return sessionFactory.openSession();
    }

}
