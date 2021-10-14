package br.com.livraria.bean;

import br.com.cdi.api.lib.helper.MessageHelper;
import br.com.livraria.dao.UserDAO;
import br.com.livraria.entity.User;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;

@Model
public class LoginBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private User user;
    @Inject
    private UserDAO userDAO;
    @Inject
    private MessageHelper helper;

    //TODO não esta respondendo ao metodo
//    @Inject
//    @ScopeMap(ScopeMap.Scope.SESSION)
//    private Map<String, Object> sessionMap;

    @PostConstruct
    public void init() {
        this.user = new User();
    }

    public User getUser() {
        return user;
    }

    public String login() {
        boolean isExist = userDAO.isExists(this.user);
        //TODO remover depois
        FacesContext context = FacesContext.getCurrentInstance();
        if (isExist) {
            context.getExternalContext().getSessionMap().put("userIsLogin", this.user);
            return "livro?faces-redirect=true";
        } else {
            helper.onFlash().addMessage(new FacesMessage("Usuario ou senha invalido"));
            return "login?faces-redirect=true";
        }
    }

    public String signOut() {
        //TODO remover depois
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().remove("userIsLogin");
        return "login?faces-redirect=true";
    }
}
