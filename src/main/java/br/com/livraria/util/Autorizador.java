package br.com.livraria.util;

import br.com.livraria.entity.User;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class Autorizador implements PhaseListener {
    @Override
    public void afterPhase(PhaseEvent event) {
        FacesContext context = event.getFacesContext();
        String view = context.getViewRoot().getViewId();
        if (view.equals("/login.xhtml")) {
            return;
        }
        User userTmp = new User(3, "test", "123");
        context.getExternalContext().getSessionMap().put("userIsLogin", userTmp);

        User userIsLogin = (User) context.getExternalContext().getSessionMap().get("userIsLogin");
        if (userIsLogin != null) {
            return;
        } else {
            NavigationHandler handler = context.getApplication().getNavigationHandler();
            handler.handleNavigation(context, null, "/login?faces-redirect=true");
            context.renderResponse();
        }
    }

    @Override
    public void beforePhase(PhaseEvent event) {

    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
}
