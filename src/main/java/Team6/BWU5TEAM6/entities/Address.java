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

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getCivico() {
        return civico;
    }

    public void setCivico(String civico) {
        this.civico = civico;
    }

    public String getLocalita() {
        return localita;
    }

    public void setLocalita(String localita) {
        this.localita = localita;
    }


    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }


    public Comune getComune() {
        return comune;
    }

    public void setComune(Comune comune) {
        this.comune = comune;
    }

    //aggiunto metodo per ottenere indirizzo completo
    public String getIndirizzoCompleto() {
        return via + " " + civico + ", " + localita + ", " + comune.getNome() + " (" + comune.getProvincia().getSigla() + ") " + cap;
    }

}
