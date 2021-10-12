package br.com.livraria.bean;

import br.com.cdi.api.lib.transaction.TransactionCDI;
import br.com.livraria.dao.AutorDAO;
import br.com.livraria.dao.LivroDAO;
import br.com.livraria.entity.Autor;
import br.com.livraria.entity.Livro;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;

//@RequestScoped
@Named
@ViewScoped
public class LivroBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private LivroDAO livroDAO;
    @Inject
    private AutorDAO autorDAO;

    private Livro livro;
    private Integer autorId;
    private Integer livroId;

    private List<Livro> livroList;

    @PostConstruct
    public void init() {
        this.livro = new Livro();
    }

    public Livro getLivro() {
        return livro;
    }

    public Integer getAutorId() {
        return autorId;
    }

    public void setAutorId(Integer autorId) {
        this.autorId = autorId;
    }

    public Integer getLivroId() {
        return livroId;
    }

    public void setLivroId(Integer livroId) {
        this.livroId = livroId;
    }

    public void setAutor() {
        Autor autor = autorDAO.getById(autorId);
        System.out.println("\n\nAutor: "+ autor.getName());
        System.out.println("Email: "+ autor.getName()+"\n\n");
        this.livro.addAutor(autor);
    }

    public void comecaComDigitoUm(FacesContext facesContext, UIComponent component, Object value) throws ValidatorException {
        String valor = value.toString();
        if (!valor.startsWith("1")) {
            throw new ValidatorException(new FacesMessage("Deveria ISBN começa com 1"));
        }
    }

    public List<Autor> getAutoresDoLivro() {
        return this.livro.getAutors();
    }

    public List<Livro> getAllLivros() {
        if (livroList == null) {
            this.livroList = livroDAO.getAll();
        }
        return livroList;
    }

    @TransactionCDI
    public void save() {
        if (livro.getAutors().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage("autor", new FacesMessage("Livro deve ter pelo menos um Autor"));
        }
        if (this.livro.getId() == null) {
            livroDAO.save(livro);
        } else {
            livroDAO.update(livro);
        }
        livroList = livroDAO.getAll();
        this.livro = new Livro();
    }

    public void getLivroById() {
        if (this.livroId == null) return;

        this.livro = livroDAO.getById(this.livroId);
    }

    public List<Autor> getAutores() {
        return autorDAO.getAll();
    }

    @TransactionCDI
    public void remove(Livro livro) {
        livroDAO.remove(livro);
        livroList = livroDAO.getAll();
    }

    public void carregar(Livro livro) {
        this.livro = livro;
    }

    public String formAutor() {
        return "autor?faces-redirect=true";
    }

    public void removeAutorDoLivro(Autor autor) {
        this.livro.removeAutor(autor);
    }

    private Livro getLivroId(Livro livro) {
        return livroDAO.getById(livro.getId());
    }

    public boolean precoEhMenor(Object valorColuma, Object filtrodigitado, Locale locale) {
        String txtdigitado = (filtrodigitado == null) ? null : filtrodigitado.toString().trim();

        System.out.println("Filtrando pelo " + txtdigitado + ", valor: " + valorColuma);
        if (txtdigitado == null || txtdigitado.equals("")) {
            return true;
        }
        if (valorColuma == null) {
            return false;
        }
        try{
            Double precoDigitado = Double.parseDouble(txtdigitado);
            Double precoColuna = (Double) valorColuma;

            return precoColuna.compareTo(precoDigitado) < 0;
        }catch (NumberFormatException e){

            return  false;
        }

    }
}
