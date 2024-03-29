package it.epicode.dao;


import it.epicode.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.UUID;

public class UserDAO {
    private final EntityManager em;


    public UserDAO(EntityManager em) {
        this.em = em;
    }

    public void save(User user) {
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.persist(user);

        transaction.commit();

        System.out.println("User " + user.getName() + " add with success!");
    }

    public User findById(UUID id) {

        return em.find(User.class, id);
    }

    public void findByIdAndDelete(UUID id) {

        User found = this.findById(id);

        if (found != null) {

            EntityTransaction transaction = em.getTransaction();


            transaction.begin();


            em.remove(found);


            transaction.commit();

            System.out.println("User " + found.getName() + " delete with success !");

        } else {

            System.out.println("User with id :" + id + " didn't found");
        }
    }
}
