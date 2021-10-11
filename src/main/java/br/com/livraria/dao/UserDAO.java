package br.com.livraria.dao;

import br.com.cdi.api.lib.jpa.annotation.Query;
import br.com.livraria.entity.User;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.io.Serializable;

public class UserDAO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;

    public boolean isExists(@NotNull User user) {
        TypedQuery<User> query = this.manager.createQuery("SELECT  U FROM User U " +
                        "WHERE U.email = :pEmail AND U.pass = :pPass", User.class)
                .setParameter("pEmail", user.getEmail())
                .setParameter("pPass", user.getPass());
        try {
            query.getSingleResult();
        } catch (NoResultException e) {
            return false;
        }
        return true;
    }


//    @Inject
//    @Query("SELECT  U FROM User U WHERE U.email = :pEmail AND U.pass = :pPass")
//    private TypedQuery<User> query;
//
//    public boolean isExists(User user) {
//        query.setParameter("pEmail", user.getEmail());
//        query.setParameter("pPass", user.getPass());
//        try {
//            query.getSingleResult();
//        } catch (NoResultException e) {
//            return false;
//        }
//        return true;
//    }
}
