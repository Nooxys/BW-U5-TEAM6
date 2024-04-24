package Team6.BWU5TEAM6.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "addresses")
@Data
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;

    private String street;

    private String civicNumber;

    private String locality;

    private String postalCode;

    @JsonIgnore
    @OneToMany(mappedBy = "registered_office", cascade = CascadeType.ALL)
    private List<Client> clientListRegisteredOffice;

    @JsonIgnore
    @OneToMany(mappedBy = "operational_headquarters", cascade = CascadeType.ALL)
    private List<Client> clientListOperationalHeadquarters;


    @ManyToOne
    @JoinColumn(name = "municipality_id", nullable = false)
    private Municipality municipality;

    public Address(String street, String civicNumber, String locality, String postalCode, Municipality municipality) {
        this.street = street;
        this.civicNumber = civicNumber;
        this.locality = locality;
        this.postalCode = postalCode;
        this.municipality = municipality;
    }

}

