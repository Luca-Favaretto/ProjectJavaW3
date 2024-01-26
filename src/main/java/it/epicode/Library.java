package it.epicode;

import it.epicode.dao.LoanDAO;
import it.epicode.dao.LoanElementDAO;
import it.epicode.dao.UserDAO;
import it.epicode.entities.Book;
import it.epicode.entities.Loan;
import it.epicode.entities.Magazine;
import it.epicode.entities.User;
import it.epicode.utils.Faker;

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

        for (int i = 0; i < 3; i++) {
            Book book = Faker.newBook.get();
            loanElementDAO.save(book);
            Magazine magazine = Faker.newMagazine.get();
            loanElementDAO.save(magazine);
            User user = Faker.newUser.get();
            userDAO.save(user);
            loanDAO.save(new Loan(user, i % 2 == 0 ? book : magazine));
        }


        System.out.println("---------------------------------------------------");

        System.out.println("Find and delete by UUID");
        System.out.println();

        UUID uuidElem = UUID.fromString("0441225b-ab0c-4106-bc67-acb7574ce966");


        System.out.println(loanElementDAO.findById(uuidElem));
        loanElementDAO.findByIdAndDelete(uuidElem);

        System.out.println("---------------------------------------------------");

        System.out.println("Find by Year of Publication");
        System.out.println();

        Faker.isEmpty(loanElementDAO.findByYearPublicatin(1990)).forEach(System.out::println);
        System.out.println();

        System.out.println("---------------------------------------------------");

        System.out.println("Find by Year of Author");
        System.out.println();

        Faker.isEmpty(loanElementDAO.findByYearAuthor("Omer Thompson DDS")).forEach(System.out::println);
        System.out.println();

        System.out.println("---------------------------------------------------");

        System.out.println("Find by Year of Title");
        System.out.println();

        Faker.isEmpty(loanElementDAO.findByYearTitle("the")).forEach(System.out::println);
        System.out.println();

        System.out.println("---------------------------------------------------");

        System.out.println("Find loan by cardNumber");
        System.out.println();

        Faker.isEmpty(loanDAO.findLoanByCardNumber("ac15c67e-bc23-4414-b568-2538be786dbd")).forEach(System.out::println);

        System.out.println();
        System.out.println("---------------------------------------------------");


        System.out.println("Find loan by ExpiredLoan");
        System.out.println();

        Faker.isEmpty(loanDAO.findExpiredLoan()).forEach(System.out::println);


    }

}
