package it.epicode.dao;

import it.epicode.abstractclass.LoanElement;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.UUID;

public class LoanElementDAO {
    private final EntityManager em;

    public LoanElementDAO(EntityManager em) {
        this.em = em;
    }

//    public List<Loan> findExpiredLoan() {
//        return em.createQuery("SELECT lo FROM ", Loan.class);
//
//
//    }

    public List<LoanElement> findLoanByCardNumber(String cardNumber) {
        UUID uuid = UUID.fromString(cardNumber);
        TypedQuery<LoanElement> query = em.createQuery("SELECT le FROM LoanElement le JOIN le.loans lo WHERE lo.user.cardNumber = :uuid AND lo.returDate IS NULL", LoanElement.class);
        query.setParameter("uuid", uuid);
        return query.getResultList();
    }

    public List<LoanElement> findByYearTitle(String title) {
        return em.createNamedQuery("find_by_title", LoanElement.class)
                .setParameter("title", "%" + title + "%").getResultList();
    }

    public List<LoanElement> findByYearAuthor(String author) {
        return em.createNamedQuery("find_by_author", LoanElement.class)
                .setParameter("author", author).getResultList();
    }

    public List<LoanElement> findByYearPublicatin(int yearPublication) {
        return em.createNamedQuery("find_by_year", LoanElement.class)
                .setParameter("yearPublication", yearPublication).getResultList();
    }

    public void save(LoanElement loanElement) {
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.persist(loanElement);

        transaction.commit();

        System.out.println("LoanElement " + loanElement.getTitle() + " add with success!");
    }

    public LoanElement findById(UUID id) {

        return em.find(LoanElement.class, id);
    }

    public void findByIdAndDelete(UUID id) {

        LoanElement found = this.findById(id);

        if (found != null) {

            EntityTransaction transaction = em.getTransaction();


            transaction.begin();


            em.remove(found);


            transaction.commit();

            System.out.println("LoanElement " + found.getTitle() + " delete with success !");

        } else {

            System.out.println("LoanElement with UUID :" + id + " didn't found");

        }

    }
}