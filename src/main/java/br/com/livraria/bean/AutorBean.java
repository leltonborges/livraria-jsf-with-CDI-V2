package br.com.livraria.bean;

import br.com.livraria.dao.AutorDAO;
import br.com.livraria.dao.DAO;
import br.com.livraria.entity.Autor;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class AutorBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private AutorDAO autorDAO;

    private Autor autor;
    private Integer autorId;

    public AutorBean() {
        this.autor = new Autor();
    }

    public AutorBean(Autor autor) {
        this.autor = autor;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Integer getAutorId() {
        return autorId;
    }

    public void setAutorId(Integer autorId) {
        this.autorId = autorId;
    }

    public String gravar() {

        if (this.autor.getId() == null) {
            autorDAO.save(this.autor);
        } else {
            autorDAO.update(this.autor);
        }

        this.autor = new Autor();
        return "livro?faces-redirect=true";
    }

    public List<Autor> getAllAutores() {
        return autorDAO.getAll();
    }

    public void carregar(Autor autor) {
        this.autor = autor;
    }

    public void removeAutor(Autor autor) {
        autorDAO.remove(autor);
    }

    public void carregarAutorId() {
        this.autor = autorDAO.getById(this.autorId);
    }
}
