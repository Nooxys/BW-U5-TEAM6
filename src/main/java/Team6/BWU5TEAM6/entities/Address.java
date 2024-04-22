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
    private String via;
    @Getter
    @Setter
    private String civico;
    @Getter
    @Setter
    private String localita;
    @Getter
    @Setter
    private String cap;

    @Getter
    @Setter
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


    //aggiunto metodo per ottenere indirizzo completo
    public String getIndirizzoCompleto() {
        return via + " " + civico + ", " + localita + ", " + comune.getNome() + " (" + comune.getProvincia().getSigla() + ") " + cap;
    }

}
