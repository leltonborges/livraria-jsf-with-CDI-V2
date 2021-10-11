package br.com.livraria.dao;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import java.util.Collection;
import java.util.List;

public class DAO<T> extends HibernateDAO {
    protected EntityManager manager;
    private Class<T> tClass;

    public DAO(Class<T> tClass, EntityManager manager) {
        this.tClass = tClass;
        this.manager = manager;
    }

    public void save(T entity) {
        if(entity == null) return;

        this.manager = getManager();
        beginTransitaion();
        this.manager.persist(entity);
        commitAndClose();
    }

    public void saveAll(Collection<T> list) {
        for (T t : list) {
            save(t);
        }
    }

    public void remove(T entity) {
        if(entity == null) return;

        this.manager = getManager();
        beginTransitaion();
        this.manager.remove(this.manager.merge(entity));
        commitAndClose();
    }

    public void update(T entity) {
        if(entity == null) return;

        this.manager = getManager();
        beginTransitaion();
        this.manager.merge(entity);
        commitAndClose();
    }

    public T getById(Integer id) {
        this.manager = getManager();
        T result = manager.find(tClass, id);
        closeManager();
        return result;
    }

    public List<T> getAll() {
        List<T> list;
        this.manager = getManager();
        CriteriaQuery<T> query = manager.getCriteriaBuilder().createQuery(tClass);
        query.select(query.from(tClass));
        list = manager.createQuery(query).getResultList();
        closeManager();
        return list;
    }

    private void commitAndClose() {
        this.commitTransitaion();
        this.closeManager();
    }

    private void beginTransitaion() {
        this.manager.getTransaction().begin();
    }

    private void commitTransitaion() {
        this.manager.getTransaction().commit();
    }

    private void closeManager() {
        this.manager.close();
    }
}
