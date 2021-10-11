package br.com.livraria.util;

import br.com.livraria.dao.DAO;
import org.jetbrains.annotations.NotNull;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

public class FactoryDAO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;

    @Produces
    public <T> DAO<T> createDAO(@NotNull InjectionPoint point){
        ParameterizedType parameterizedType = (ParameterizedType) point.getType();
        Class<T> tClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
        return  new DAO<T>(tClass, manager);
    }
}
