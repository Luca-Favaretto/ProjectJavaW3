package it.epicode;

import it.epicode.dao.LoanDAO;
import it.epicode.dao.LoanElementDAO;
import it.epicode.dao.UserDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.UUID;

public class Library {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("loanmanagement");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();

        LoanElementDAO loanElementDAO = new LoanElementDAO(em);
        UserDAO userDAO = new UserDAO(em);
        LoanDAO loanDAO = new LoanDAO(em);

        System.out.println();
        System.out.println("Save loanElement and user");

//        for (int i = 0; i < 20; i++) {
//            loanElementDAO.save(Faker.newBook.get());
//            loanElementDAO.save(Faker.newMagazine.get());
//            userDAO.save(Faker.newUser.get());
//        }
        System.out.println("---------------------------------------------------");

        System.out.println("Find and delete by UUID");
        System.out.println();

        UUID uuidElem = UUID.fromString("07bdd312-23c3-4c15-ac81-d3d517eaa736");

        System.out.println(loanElementDAO.findById(uuidElem));
        loanElementDAO.findByIdAndDelete(uuidElem);

        System.out.println("---------------------------------------------------");

        System.out.println("Find by Year of Publication");
        System.out.println();

        loanElementDAO.findByYearPublicatin(2011).forEach(System.out::println);
        System.out.println();
        System.out.println("---------------------------------------------------");
        System.out.println("Find by Year of Author");
        System.out.println();
        loanElementDAO.findByYearAuthor("Dr. Ulysses Balistreri").forEach(System.out::println);

    }

}
