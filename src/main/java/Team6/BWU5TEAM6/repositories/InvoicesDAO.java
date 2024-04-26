package Team6.BWU5TEAM6.repositories;
import Team6.BWU5TEAM6.entities.Invoices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Date;

public interface InvoicesDAO extends JpaRepository<Invoices, Long> {
    Page<Invoices> findByClientId(Long clientId, Pageable pageable);
    Page<Invoices> findByState(String state, Pageable pageable);
    Page<Invoices> findByDate(Date date, Pageable pageable);
    @Query("SELECT i FROM Invoices i WHERE YEAR(i.date) = :year")
    Page<Invoices> findByYear(@Param("year") int year, Pageable pageable);
    Page<Invoices> findByAmountBetween(double minAmount, double maxAmount, Pageable pageable);

}
