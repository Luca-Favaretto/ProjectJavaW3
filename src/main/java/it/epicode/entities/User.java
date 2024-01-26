package it.epicode.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "card_number", nullable = false)
    private UUID cardNumber;
    @Column(nullable = false, length = 30)
    private String name;
    @Column(nullable = false, length = 30)

    private String surname;
    private LocalDate birthDate;

    @OneToMany(mappedBy = "user")
    private Set<Loan> loans;

    public User() {
    }

    public User(String name, String surname, LocalDate birthDate) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
    }

    public void setLoans(Set<Loan> loans) {
        this.loans = loans;
    }

    public UUID getCardNumber() {
        return cardNumber;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "cardNumber=" + cardNumber +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate=" + birthDate +
                ", loans=" + loans +
                '}';
    }
}
