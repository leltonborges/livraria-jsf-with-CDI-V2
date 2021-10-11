package br.com.livraria.dao;

import org.jetbrains.annotations.NotNull;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateDAO {
    private static final String PERSISTENCE = "livraria";
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE);

    @Produces
    public EntityManager getManager() {
        return factory.createEntityManager();
    }

    public void closeManager(@Disposes @NotNull EntityManager manager){
        manager.close();
    }

}
