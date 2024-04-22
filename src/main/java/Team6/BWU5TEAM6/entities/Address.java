package Team6.BWU5TEAM6.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "addresses")
@Data
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String via;
    private String civico;
    private String localita;
    private String cap;

    @ManyToOne
    @JoinColumn(name = "comune_id", nullable = false)
    private Comune comune;

    public Address(String via, String civico, String localita, String cap, Comune comune){
       this.via = via; 
       this.civico = civico;
       this.localita= localita;
       this.cap = cap;
       this.comune = comune;
    }


}
