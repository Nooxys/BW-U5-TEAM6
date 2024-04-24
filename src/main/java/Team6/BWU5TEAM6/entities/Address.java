package Team6.BWU5TEAM6.entities;

import jakarta.persistence.*;
import lombok.*;

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


    @ManyToOne
    @JoinColumn(name = "comune_id", nullable = false)
    private Municipality municipality;

    public Address(String street, String civicNumber, String locality, String postalCode, Municipality municipality){
        this.street = street;
        this.civicNumber = civicNumber;
        this.locality = locality;
        this.postalCode = postalCode;
        this.municipality = municipality;
    }

}

