package it.epicode.entities;

import it.epicode.abstractclass.LoanElement;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@Entity
@Table(name = "loan")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "loan_element_id")
    private LoanElement loanElement;


    @Column(nullable = false)
    private LocalDate loanDate;

    private LocalDate expectedReturnDate;

    private LocalDate returDate;

    public Loan() {
    }

    public Loan(User user, LoanElement loanElement) {
        Random rnd = new Random();
        this.user = user;
        this.loanElement = loanElement;
        this.loanDate = LocalDate.now().minusDays(rnd.nextInt(20, 40));
        this.expectedReturnDate = loanDate.plusDays(30);
    }

    public UUID getId() {
        return id;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public void setExpectedReturnDate(LocalDate expectedReturnDate) {
        this.expectedReturnDate = expectedReturnDate;
    }

    public LocalDate getReturDate() {
        return returDate;
    }

    public void setReturDate(LocalDate returDate) {
        this.returDate = returDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LoanElement getLoanElement() {
        return loanElement;
    }

    public void setLoanElement(LoanElement loanElement) {
        this.loanElement = loanElement;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "user=" + user.getName() +
                ", loanElement=" + loanElement +
                ", loanDate=" + loanDate +
                ", expectedReturnDate=" + expectedReturnDate +
                ", returDate=" + returDate +
                '}';
    }
}
