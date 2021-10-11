package br.com.livraria.bean;

import br.com.cdi.api.lib.helper.MessageHelper;
import br.com.cdi.api.lib.jsf.annotation.SessionMap;
import br.com.livraria.dao.UserDAO;
import br.com.livraria.entity.User;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;

@Named
@ViewScoped
public class LoginBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private User user;
    private UserDAO userDAO;
    private MessageHelper helper;
    private FacesContext context;
    private Map<String, Object> sessionMap;

    @Inject
    public LoginBean(UserDAO userDAO, MessageHelper helper, FacesContext context) {
        this.userDAO = userDAO;
        this.helper = helper;
        this.context = context;
    }

    //TODO add sessionMap with Qualifier in construtor

    @PostConstruct
    public void init() {
        this.user = new User();
        this.sessionMap = context.getExternalContext().getSessionMap();
    }

    public User getUser() {
        return user;
    }

    public String login() {
        boolean isExist = userDAO.isExists(this.user);

        if (isExist) {
            sessionMap.put("userIsLogin", this.user);
            return "livro?faces-redirect=true";
        } else {
            helper.onFlash().addMessage(new FacesMessage("Usuario ou senha invalido"));
            return "login?faces-redirect=true";
        }
    }

    public String signOut() {
        sessionMap.remove("userIsLogin");
        return "login?faces-redirect=true";
    }
}
