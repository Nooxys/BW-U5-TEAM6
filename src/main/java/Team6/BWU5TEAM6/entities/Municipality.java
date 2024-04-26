package Team6.BWU5TEAM6.entities;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
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
    @JoinColumn(name = "district_sigla", referencedColumnName = "sigla")
    private District district;
}
