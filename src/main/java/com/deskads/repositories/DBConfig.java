package com.deskads.repositories;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 * Created by Administrator on 02.11.2017.
 */
/*
@Component
public class DBConfig {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Bean
    public EntityManagerFactory getEntityManagerFactory(){
        return this.emf;
    }


    @Bean
    public SessionFactory getSessionFactory() {
        return emf.unwrap(SessionFactory.class);
    }
}
*/