package Team6.BWU5TEAM6.controllers;

import Team6.BWU5TEAM6.dto.ClientDTO;
import Team6.BWU5TEAM6.dto.ClientResponseDTO;
import Team6.BWU5TEAM6.entities.Client;
import Team6.BWU5TEAM6.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping("/create")
    public ClientResponseDTO createClient(@RequestBody ClientDTO clientDTO) throws IOException{
        return clientService.save(clientDTO);
    }

    @GetMapping("")
    public Page<Client> getClients(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "businessName") String sortBy){
        return clientService.getClients(page, size, sortBy);
    }

    @GetMapping("/filter/revenue")
    public Page<Client> filterClientsByRevenue(
            @RequestParam double minRevenue,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return clientService.filterClientsByRevenue(minRevenue, page, size);
    }

    @GetMapping("/filter/insertedDate")
    public Page<Client> filterClientsByInsertedDate(
            @RequestParam String minInsertedDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        LocalDate date = LocalDate.parse(minInsertedDate);
        return clientService.filterCustomersByInsertedDate(date, page, size);
    }

    @GetMapping("/filter/lastContactDate")
    public Page<Client> filterClientsByLastContactDate(
            @RequestParam String minLastContactDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        LocalDate date = LocalDate.parse(minLastContactDate);
        return clientService.filterCustomersByLastContactDate(date, page, size);
    }

    @GetMapping("/filter/namePart")
    public Page<Client> filterClientsByNamePart(
            @RequestParam String namePart,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return clientService.filterCustomersByNamePart(namePart, page, size);
    }

}
