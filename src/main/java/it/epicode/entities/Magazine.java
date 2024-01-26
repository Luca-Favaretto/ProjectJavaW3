package it.epicode.entities;

import it.epicode.abstractclass.LoanElement;
import it.epicode.enumobj.Periodicity;

import javax.persistence.*;

@Entity
@DiscriminatorValue("magazine")
public class Magazine extends LoanElement {

    @Enumerated(EnumType.STRING)
    private Periodicity periodicity;

    public Magazine() {
    }

    public Magazine(String title, int yearPublication, int numberPAge, Periodicity periodicity) {
        super(title, yearPublication, numberPAge);
        this.periodicity = periodicity;
    }

    public Periodicity getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(Periodicity periodicity) {
        this.periodicity = periodicity;
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "periodicity=" + periodicity +
                ", title='" + title + '\'' +
                ", yearPublication=" + yearPublication +
                ", numberPAge=" + numberPAge +
                '}';
    }
}
