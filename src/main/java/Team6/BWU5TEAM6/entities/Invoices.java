package Team6.BWU5TEAM6.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "invoices")
@Data
@NoArgsConstructor
@ToString
public class Invoices {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private long id;
    private Date date;
    private double amount;
    private int number;
    private String state;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;


    public Invoices(Date date, double amount, int number, String state, Client client) {
        this.date = date;
        this.amount = amount;
        this.number = number;
        this.state = state;
        this.client = client;
    }
}
