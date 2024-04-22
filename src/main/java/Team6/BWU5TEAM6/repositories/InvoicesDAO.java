package Team6.BWU5TEAM6.repositories;
import Team6.BWU5TEAM6.entities.Invoices;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.UUID;

public interface InvoicesDAO extends JpaRepository<Invoices, Long> {
}
