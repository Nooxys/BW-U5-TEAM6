package Team6.BWU5TEAM6.repositories;

import Team6.BWU5TEAM6.entities.District;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DistrictDAO extends JpaRepository<District, Integer> {
    District findByName(String name_district);

}
