package br.com.livraria.transacao;

import br.com.cdi.api.lib.transaction.Transacionado;
import br.com.cdi.api.lib.transaction.TransactionDefault;

import javax.annotation.Priority;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Intercepted;
import javax.enterprise.inject.Specializes;
import javax.enterprise.inject.Typed;
import javax.inject.Inject;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

//@Specializes // Usado com herança, uma especialização da TransactionDefault
@Alternative // Sobrepoe um bean já existente, ou add no bean.xml ou @Priority
// no bean.xml não fica sendo utilizado em outros bean de outro projeto ou lib
@Priority(Interceptor.Priority.APPLICATION) // add de modo global para todos os outros beans
@Typed(Transacionado.class) // atender apenas a transacionado
public class MeuGerenciadorTransacao implements Transacionado {
    private static final long serialVersionUID = -793331489933550997L;

    @Inject
    private EntityManager manager;

    @Override
    public Object executeTransacao(InvocationContext context) {
        System.out.println("Abrindo uma transação");
        manager.getTransaction().begin();
        try {
            System.out.println("Antes de executar o método interceptado");
            Object result = context.proceed();
            System.out.println("Antes do commit");
            manager.getTransaction().commit();
            System.out.println("Comitou");
            return result;
        } catch (Exception e) {
            System.out.println("Antes de executar o rollback");
            manager.getTransaction().rollback();
            throw new RuntimeException("Erro na class MeuGerenciadorTransacao\n"+e);
        }
    }
}
