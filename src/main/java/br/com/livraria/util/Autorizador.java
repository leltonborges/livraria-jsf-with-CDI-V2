package br.com.livraria.util;

import br.com.cdi.api.lib.jsf.annotation.Phase;
import br.com.cdi.api.lib.jsf.phaselistener.After;
import br.com.livraria.entity.User;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;
import java.util.Map;

public class Autorizador {

    @Inject
    private FacesContext context;
    @Inject
    private NavigationHandler handler;

    private Map<String, Object> sessionMap;

    @PostConstruct
    public void init() {
        this.sessionMap = context.getExternalContext().getSessionMap();
    }

    public void autoriza(@Observes @After @Phase(Phase.Phases.RESTORE_VIEW) PhaseEvent event) {
        String view = context.getViewRoot().getViewId();
        if (view.equals("/login.xhtml")) {
            return;
        }
        User userIsLogin = (User) sessionMap.get("userIsLogin");
        if (userIsLogin != null) {
            return;
        } else {
            handler.handleNavigation(context, null, "/login?faces-redirect=true");
            context.renderResponse();
        }
    }

}
