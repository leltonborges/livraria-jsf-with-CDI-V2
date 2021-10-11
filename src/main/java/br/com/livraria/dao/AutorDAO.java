package br.com.livraria.dao;

import br.com.cdi.api.lib.DAO.DAO;
import br.com.livraria.entity.Autor;


import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

public class AutorDAO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private DAO<Autor> autorDAO;

    public void save(Autor entity) {
        autorDAO.save(entity);
    }

    public void remove(Autor entity) {
        autorDAO.remove(entity);
    }

    public void update(Autor entity) {
        autorDAO.update(entity);
    }

    public Autor getById(Integer id) {
        return autorDAO.getById(id);
    }

    public List<Autor> getAll() {
        return autorDAO.getAll();
    }
}
