package Team6.BWU5TEAM6.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.UUID;
@Entity
@Table(name = "invoices")
@Data
@NoArgsConstructor
@ToString
public class Invoices {
    @Id
    @GeneratedValue
    private long invoicesId;
    private Date date;
    private double amount;
    private int number;
    private String state;
    @ManyToOne
    private Customer customer;


    public Invoices( Date date, double amount, int number, String state, Customer customer) {
        this.date = date;
        this.amount = amount;
        this.number = number;
        this.state = state;
        this.customer = customer;
    }
}
