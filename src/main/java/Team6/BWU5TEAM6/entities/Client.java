package Team6.BWU5TEAM6.entities;

import Team6.BWU5TEAM6.enums.ClientType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private long id;
    private String businessName;
    private int vatNumber;
    private String email;
    private LocalDate insertionDate;
    private LocalDate lastContactDate;
    private double annualTurnover;
    private String pec;
    private int telephone;
    private String emailContact;
    private String nameContact;
    private int telephoneContact;
    private String companyLogo;
    @Enumerated(EnumType.STRING)
    private ClientType type;
    private String address;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Client(String email) {
        this.email = email;
    }
}
