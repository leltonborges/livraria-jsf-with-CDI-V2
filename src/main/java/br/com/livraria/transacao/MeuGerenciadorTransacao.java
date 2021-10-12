package br.com.livraria.transacao;

import br.com.cdi.api.lib.transaction.TransactionDefault;

import javax.enterprise.inject.Specializes;
import javax.interceptor.InvocationContext;

@Specializes
public class MeuGerenciadorTransacao extends TransactionDefault {
    private static final long serialVersionUID = -793331489933550997L;

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
