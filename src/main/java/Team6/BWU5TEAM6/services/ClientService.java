package Team6.BWU5TEAM6.services;

import Team6.BWU5TEAM6.dto.ErrorsDTO;
import Team6.BWU5TEAM6.entities.Client;
import Team6.BWU5TEAM6.exceptions.NotFoundException;
import Team6.BWU5TEAM6.repositories.ClientDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
@Autowired
    private ClientDAO clientDAO;






public Page<Client> getClients(int page, int size, String sortBy){
   if(size > 100) size = 100;

   Pageable pageable= PageRequest.of(page, size, Sort.by( sortBy));
    return switch (sortBy) {
        case "businessName" -> clientDAO.findAllByOrderByBusinessNameAsc(pageable);
        case "annualTurnover" -> clientDAO.findAllByOrderByAnnualTurnoverAsc(pageable);
        case "insertionDate" -> clientDAO.findAllByOrderByInsertionDateAsc(pageable);
        case "lastContactDate" -> clientDAO.findAllByOrderByLastContactDateAsc(pageable);
        case "addressProvince" -> clientDAO.findAllByOrderByAddressProvinceAsc(pageable);
        default -> throw new NotFoundException("nessun elemento trovato");
    };

}
}
