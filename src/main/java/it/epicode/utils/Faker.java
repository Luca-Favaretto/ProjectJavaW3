package it.epicode.utils;

import it.epicode.dao.LoanDAO;
import it.epicode.dao.LoanElementDAO;
import it.epicode.dao.UserDAO;
import it.epicode.entities.Book;
import it.epicode.entities.Loan;
import it.epicode.entities.Magazine;
import it.epicode.entities.User;
import it.epicode.enumobj.Periodicity;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.function.Supplier;

public class Faker {
    public static Supplier<Book> newBook = () -> {
        com.github.javafaker.Faker faker = new com.github.javafaker.Faker(Locale.ENGLISH);
        Random rndm = new Random();
        return new Book(faker.book().title(), rndm.nextInt(1980, 2024), rndm.nextInt(50, 1000), faker.book().author(), faker.book().author());
    };
    public static Supplier<Magazine> newMagazine = () -> {
        com.github.javafaker.Faker faker = new com.github.javafaker.Faker(Locale.ENGLISH);
        Random rndm = new Random();
        Periodicity[] values = Periodicity.values();
        return new Magazine(faker.book().title(), rndm.nextInt(1980, 2024), rndm.nextInt(15, 40), values[rndm.nextInt(values.length)]);
    };
    public static Supplier<User> newUser = () -> {
        com.github.javafaker.Faker faker = new com.github.javafaker.Faker(Locale.ENGLISH);
        Random rndm = new Random();
        LocalDate now = LocalDate.now();
        return new User(faker.dragonBall().character(), faker.animal().name(), now.minusDays(rndm.nextInt(4380, 29200)));
    };

    public static void fullTable(LoanElementDAO loanElementDAO, UserDAO userDAO, LoanDAO loanDAO) {
        for (int i = 0; i < 5; i++) {
            Book book = Faker.newBook.get();
            loanElementDAO.save(book);
            Magazine magazine = Faker.newMagazine.get();
            loanElementDAO.save(magazine);
            User user = Faker.newUser.get();
            userDAO.save(user);
            loanDAO.save(new Loan(user, i % 2 == 0 ? book : magazine));
        }
    }

    public static List<?> isEmpty(List<?> lista) {
        if (lista.isEmpty()) {
            System.out.println("Is empty");
        }
        return lista;
    }

};
