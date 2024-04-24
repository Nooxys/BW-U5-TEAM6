package Team6.BWU5TEAM6.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "municipalities")
public class Municipality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int id;
    private String id_district;
    private String id_municipality;
    private String name_municipality;
    private String name_district;
    @ManyToOne
    @JoinColumn(name = "district_sigla")
    private District district;

    public Municipality(String id_district, String id_municipality, String name_municipality, String name_district, District district) {
        this.id_district = id_district;
        this.id_municipality = id_municipality;
        this.name_municipality = name_municipality;
        this.name_district = name_district;
        this.district = district;
    }
}
