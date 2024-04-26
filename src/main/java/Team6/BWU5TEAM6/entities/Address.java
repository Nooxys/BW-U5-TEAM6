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

    private int civicNumber;

    private String locality;

    private int postalCode;

    @JsonIgnore
    @OneToMany(mappedBy = "registered_office", cascade = CascadeType.ALL)
    private List<Client> clientListRegisteredOffice;

    @JsonIgnore
    @OneToMany(mappedBy = "operational_headquarters", cascade = CascadeType.ALL)
    private List<Client> clientListOperationalHeadquarters;


    @ManyToOne
    @JoinColumn(name = "municipality_id", nullable = false)
    private Municipality municipality;

    @ManyToOne
    @JoinColumn(name = "district_id")
    private District district;

    public Address(String street, int civicNumber, String locality, int postalCode, Municipality municipality, District district) {
        this.street = street;
        this.civicNumber = civicNumber;
        this.locality = locality;
        this.postalCode = postalCode;
        this.municipality = municipality;
        this.district = district;
    }


}

