package Team6.BWU5TEAM6.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "districts")
public class District {
    @Id
    private String sigla;
    private String provincia;
    private String regione;
    @OneToMany(mappedBy = "district", cascade = CascadeType.ALL)
    private List<Municipality> municipalities;

    public District(String sigla, String provincia, String regione) {
        this.sigla = sigla;
        this.provincia = provincia;
        this.regione = regione;
    }
}

