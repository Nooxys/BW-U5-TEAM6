package Team6.BWU5TEAM6.entities;

import Team6.BWU5TEAM6.enums.ClientType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
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

    @ManyToOne
    @JoinColumn(name = "registered_office_id")
    private Address registered_office;

    @ManyToOne
    @JoinColumn(name = "operational_headquarters_id")
    private Address operational_headquarters;

    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private List<Invoices> invoices;


    public Client(String email) {
        this.email = email;
    }

    public Client(String businessName, int vatNumber, String email, LocalDate insertionDate, LocalDate lastContactDate, double annualTurnover, String pec, int telephone, String emailContact, String nameContact, int telephoneContact, String companyLogo, ClientType type, String address, User user) {
        this.businessName = businessName;
        this.vatNumber = vatNumber;
        this.email = email;
        this.insertionDate = insertionDate;
        this.lastContactDate = lastContactDate;
        this.annualTurnover = annualTurnover;
        this.pec = pec;
        this.telephone = telephone;
        this.emailContact = emailContact;
        this.nameContact = nameContact;
        this.telephoneContact = telephoneContact;
        this.companyLogo = companyLogo;
        this.type = type;
        this.address = address;
        this.user = user;
    }
}
