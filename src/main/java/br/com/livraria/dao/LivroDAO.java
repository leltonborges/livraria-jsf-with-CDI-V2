package br.com.livraria.dao;

import br.com.livraria.entity.Livro;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class LivroDAO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;

    @Inject
    public DAO<Livro> livroDAO;

    public void save(Livro entity) {
        livroDAO.save(entity);
    }

    public void saveAll(Collection<Livro> list) {
        livroDAO.saveAll(list);
    }

    public void remove(Livro entity) {
        livroDAO.remove(entity);
    }

    public void update(Livro entity) {
        livroDAO.update(entity);
    }

    public Livro getById(Integer id) {
        return livroDAO.getById(id);
    }

    public List<Livro> getAll() {
        return livroDAO.getAll();
    }
}
