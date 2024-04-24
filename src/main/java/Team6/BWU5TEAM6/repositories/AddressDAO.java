package Team6.BWU5TEAM6.repositories;

import Team6.BWU5TEAM6.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface AddressDAO extends JpaRepository <Address, Long>{
}
