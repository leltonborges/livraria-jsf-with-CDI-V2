package br.com.livraria.dao;

import br.com.livraria.entity.User;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.io.Serializable;

public class UserDAO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;

    public boolean isExists(@NotNull User user) {
        try {
            User singleResult = this.manager.createQuery("SELECT  U FROM User U " +
                            "WHERE U.email = :pEmail AND U.pass = :pPass", User.class)
                    .setParameter("pEmail", user.getEmail())
                    .setParameter("pPass", user.getPass())
                    .getSingleResult();

            return true;
        } catch (NoResultException e) {
            return false;
        }
    }
}
