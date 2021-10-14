package br.com.livraria.util;

import br.com.cdi.api.lib.jsf.annotation.Phase;
import br.com.cdi.api.lib.jsf.phaselistener.After;

import javax.enterprise.event.Observes;
import javax.faces.event.PhaseEvent;

public class LogPhase {

    public void log(@Observes @After PhaseEvent event){
        System.out.println("FASE: "+ event.getPhaseId());
    }
}
