package Team6.BWU5TEAM6.repositories;

import Team6.BWU5TEAM6.entities.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ClientDAO extends JpaRepository<Client, Long> {
    boolean existsByEmail(String email);

    Optional<Client> findByEmail(String Email);
    Page<Client> findAllByOrderByBusinessNameAsc(Pageable pageable);
    Page<Client> findAllByOrderByAnnualTurnoverAsc(Pageable pageable);
    Page<Client> findAllByOrderByInsertionDateAsc(Pageable pageable);
    Page<Client> findAllByOrderByLastContactDateAsc(Pageable pageable);
    Page<Client> findAllByOrderByAddressProvinceAsc(Pageable pageable);

    Page<Client> findByRevenueGreaterThanEqual(double minRevenue, Pageable pageable);

    Page<Client> findByInsertedAfter(LocalDate minInsertedDate, Pageable pageable);

    Page<Client> findByLastContactAfter(LocalDate minLastContactDate, Pageable pageable);

    Page<Client> findByBusinessNameContaining(String namePart, Pageable pageable);

}