package it.epicode.dao;

import it.epicode.entities.Loan;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.UUID;

public class LoanDAO {
    private final EntityManager em;


    public LoanDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Loan loan) {
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.persist(loan);

        transaction.commit();

        System.out.println("LoanElement " + loan.getLoanElement().getTitle() + " add with success!");
    }

    public Loan findById(UUID id) {

        return em.find(Loan.class, id);
    }

    public void findByIdAndDelete(UUID id) {

        Loan found = this.findById(id);

        if (found != null) {

            EntityTransaction transaction = em.getTransaction();


            transaction.begin();


            em.remove(found);


            transaction.commit();

            System.out.println("LoanElement " + found.getLoanElement().getTitle() + " delete with success !");

        } else {

            System.out.println("Loan with id :" + id + " didn't found");
        }
    }
}
