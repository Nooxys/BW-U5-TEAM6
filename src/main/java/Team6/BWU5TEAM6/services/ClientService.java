package Team6.BWU5TEAM6.services;

import Team6.BWU5TEAM6.dto.ClientDTO;
import Team6.BWU5TEAM6.dto.ClientResponseDTO;
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
import java.util.Map;

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
        return new ClientResponseDTO(client.getBusinessName(), client.getEmail());
    }

    public Page<Client> getClients(int page, int size, String sortBy) {
        if (size > 100) size = 100;


        Sort sort = switch (sortBy) {
            case "businessName" -> Sort.by("businessName").ascending();
            case "annualTurnover" -> Sort.by("annualTurnover").ascending();
            case "insertionDate" -> Sort.by("insertionDate").ascending();
            case "lastContactDate" -> Sort.by("lastContactDate").ascending();
            case "addressProvince" -> Sort.by("address").ascending();
            default -> throw new NotFoundException("nessun elemento trovato");
        };
        Pageable pageable = PageRequest.of(page, size, sort);
        return clientDAO.findAll(pageable);
    }

    public Page<Client> filterClientsByRevenue(double minRevenue, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return clientDAO.findByAnnualTurnoverGreaterThanEqual(minRevenue, pageRequest);
    }

    public Page<Client> filterCustomersByInsertedDate(LocalDate minInsertedDate, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return clientDAO.findByInsertionDateAfter(minInsertedDate, pageRequest);
    }

    public Page<Client> filterCustomersByLastContactDate(LocalDate minLastContactDate, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return clientDAO.findByLastContactDateAfter(minLastContactDate, pageRequest);
    }

    public Page<Client> filterCustomersByNamePart(String namePart, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return clientDAO.findByBusinessNameContaining(namePart, pageRequest);
    }

    public Client findbyId(long id) {
        return this.clientDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Client findByIdAndUpdate(long id, ClientDTO body) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(body.companyLogo(), ObjectUtils.emptyMap());
        String companyLogoUrl = (String) uploadResult.get("url");

        Client found = this.findbyId(id);
        found.setBusinessName(body.businessName());
        found.setVatNumber(body.vatNumber());
        found.setEmail(body.email());
        found.setInsertionDate(body.insertionDate());
        found.setLastContactDate(body.lastContactDate());
        found.setAnnualTurnover(body.annualTurnover());
        found.setPec(body.pec());
        found.setTelephone(body.telephone());
        found.setEmail(body.email());
        found.setEmailContact(body.emailContact());
        found.setNameContact(body.nameContact());
        found.setTelephoneContact(body.telephoneContact());
        found.setCompanyLogo(companyLogoUrl);
        this.clientDAO.save(found);
        return found;
    }

    public void findByIdAndDelete(long id) {
        Client found = this.findbyId(id);
        this.clientDAO.delete(found);
    }
}
