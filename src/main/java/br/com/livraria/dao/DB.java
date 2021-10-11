package br.com.livraria.dao;

import br.com.livraria.entity.Autor;
import br.com.livraria.entity.Livro;
import br.com.livraria.entity.User;
import br.com.cdi.api.lib.DAO.DAO;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DB {
    public static void main(String[] args) {
        EntityManager manager = Persistence
                .createEntityManagerFactory("livraria").createEntityManager();
        DAO<Livro> livrosDAO = new DAO<Livro>(Livro.class, manager);
        DAO<Autor> autorDAO = new DAO<Autor>(Autor.class, manager);
        DAO<User> userDAO = new DAO<User>(User.class, manager);

        Autor autor1 = new Autor(null, "Machado de assis", "assis@gmail.com");
        Autor autor2 = new Autor(null, "Jorge Amado", "jorge@gmail.com");
        Autor autor3 = new Autor(null, "Paulo Coelho", "paulo@gmail.com");

        List<Autor> autors = Arrays.asList(autor1, autor2, autor3);

        Livro livros1 = new Livro(null, "Capitões da Areia","978-8-50-831169-1", 98.8);
        livros1.addAutor(autor2);
        livros1.setDataLancamento(parseDate("02/01/2021 23:14"));

        Livro livros2 = new Livro(null, "Dona Flor e Seus dois maridos","978-8-53-592569-9", 18.8);
        livros2.addAutor(autor2);
        livros2.setDataLancamento(parseDate("10/10/2008 10:50"));


        Livro livros3 = new Livro(null, "Livro 3","978-8-50-863169-1", 85.5);
        livros3.addAllAutor(autor3, autor1);
        livros3.setDataLancamento(parseDate("28/05/2016 08:35"));

        Livro livros4 = new Livro(null, "Livro 4","978-8-50-83797-1", 2.5);
        livros4.addAllAutor(autor1, autor2,autor3);
        livros4.setDataLancamento(parseDate("09/08/2000 00:00"));

        List<Livro> livros = Arrays.asList(livros1, livros2, livros3, livros4);

        User user1 = new User(null, "bia@gmail.com", "123");
        User user2 = new User(null, "alex@gmail.com", "123");
        User user3 = new User(null, "ana@gmail.com", "123");
        User user4 = new User(null, "lia@gmail.com", "123");
        List<User> userList = Arrays.asList(user1, user2, user3, user4);

        autorDAO.saveAll(autors);
        livrosDAO.saveAll(livros);
        userDAO.saveAll(userList);
    }
    private static Calendar parseDate(String data){
        try {
            Date date = new SimpleDateFormat("dd/MM/YYYY HH:mm").parse(data);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return  calendar;
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }

    }
}
