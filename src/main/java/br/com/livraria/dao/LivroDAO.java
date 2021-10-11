package br.com.livraria.dao;

import br.com.livraria.entity.Livro;
import br.com.livraria.transaction.TransactionCDI;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

public class LivroDAO implements Serializable {
    private static final long serialVersionUID = 1L;


    @Inject
    public DAO<Livro> livroDAO;

    @TransactionCDI
    public void save(Livro entity) {
        livroDAO.save(entity);
    }

    @TransactionCDI
    public void remove(Livro entity) {
        livroDAO.remove(entity);
    }

    @TransactionCDI
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
