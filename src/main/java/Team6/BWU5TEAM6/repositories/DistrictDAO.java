package Team6.BWU5TEAM6.repositories;

import Team6.BWU5TEAM6.entities.District;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistrictDAO extends JpaRepository<District, Integer> {
    District findBySigla(String sigla);

}
