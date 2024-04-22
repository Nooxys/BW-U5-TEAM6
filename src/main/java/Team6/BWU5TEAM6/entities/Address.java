package Team6.BWU5TEAM6.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "addresses")
@Data
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Getter
    @Setter
    private String street;
    @Getter
    @Setter
    private String civicNumber;
    @Getter
    @Setter
    private String locality;
    @Getter
    @Setter
    private String postalCode;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "comune_id", nullable = false)
    private Comune comune;

    public Address(String street, String civicNumber, String locality, String postalCode, Comune comune){
        this.street = street;
        this.civicNumber = civicNumber;
        this.locality = locality;
        this.postalCode = postalCode;
        this.comune = comune;
    }

    public String getFullAddress() {
        return street + " " + civicNumber + ", " + locality + ", " + comune.getName() + " (" + comune.getProvince().getAcronym() + ") " + postalCode;
    }

}

