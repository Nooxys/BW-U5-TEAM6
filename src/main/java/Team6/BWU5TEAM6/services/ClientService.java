package Team6.BWU5TEAM6.services;

import Team6.BWU5TEAM6.dto.ClientDTO;
import Team6.BWU5TEAM6.dto.ClientResponseDTO;
import Team6.BWU5TEAM6.dto.ErrorsDTO;
import Team6.BWU5TEAM6.entities.Client;
import Team6.BWU5TEAM6.exceptions.NotFoundException;
import Team6.BWU5TEAM6.repositories.ClientDAO;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ClientService {
@Autowired
    private ClientDAO clientDAO;

private Cloudinary cloudinary;

public ClientService(Cloudinary cloudinary) {
    this.cloudinary = cloudinary;
}


public ClientResponseDTO save(ClientDTO clientDTO) throws IOException {

    Map uploadResult = cloudinary.uploader().upload(clientDTO.companyLogo(), ObjectUtils.emptyMap());
    String companyLogoUrl = (String) uploadResult.get("url");

    Client client = new Client();
    client.setBusinessName(clientDTO.businessName());
    client.setVatNumber(clientDTO.vatNumber());
    client.setEmail(clientDTO.email());
    client.setInsertionDate(clientDTO.insertionDate());
    client.setLastContactDate(clientDTO.lastContactDate());
    client.setAnnualTurnover(clientDTO.annualTurnover());
    client.setPec(clientDTO.pec());
    client.setTelephone(clientDTO.telephone());
    client.setEmailContact(clientDTO.emailContact());
    client.setNameContact(clientDTO.nameContact());
    client.setTelephoneContact(clientDTO.telephoneContact());
    client.setCompanyLogo(companyLogoUrl);

    client = clientDAO.save(client);
    return new ClientResponseDTO(client.getBusinessName(), client.getEmail(), client.getType().toString());
}

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
    public Page<Client> filterClientsByRevenue(double minRevenue, int page, int size){
        PageRequest pageRequest = PageRequest.of(page, size);
        return clientDAO.findByRevenueGreaterThanEqual(minRevenue, pageRequest);
    }
    public Page<Client> filterCustomersByInsertedDate(LocalDate minInsertedDate, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return clientDAO.findByInsertedAfter(minInsertedDate, pageRequest);
    }

    public Page<Client> filterCustomersByLastContactDate(LocalDate minLastContactDate, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return clientDAO.findByLastContactAfter(minLastContactDate, pageRequest);
    }
    public Page<Client> filterCustomersByNamePart(String namePart, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return clientDAO.findByBusinessNameContaining(namePart, pageRequest);
    }


}
