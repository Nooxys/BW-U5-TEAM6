package Team6.BWU5TEAM6.controllers;

import Team6.BWU5TEAM6.dto.NewInvoicesDTO;
import Team6.BWU5TEAM6.dto.NewInvoicesRespDTO;
import Team6.BWU5TEAM6.entities.Invoices;
import Team6.BWU5TEAM6.exceptions.BadRequestException;
import Team6.BWU5TEAM6.services.InvoicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/invoices")
public class InvoicesController {
    @Autowired
    private InvoicesService invoicesService;
    @GetMapping("/{invoicesId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    //URL: /invoices/{invoicesId}
    public Invoices getInvoicesById(@PathVariable Long id) {
        return invoicesService.findById(id);
    }
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    //URL: /invoices
    private Page<Invoices> getAllInvoices(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size,
                                    @RequestParam(defaultValue = "id") String sortBy){
        return this.invoicesService.getInvoicesList(page, size, sortBy);
    }
    @GetMapping("/byClient")
    @PreAuthorize("hasAuthority('ADMIN')")
    //URL: /invoices/byClient?clientId={clientId}
    public Page<Invoices> getInvoicesByClient(@RequestParam Long clientId,
                                              @RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "10") int size,
                                              @RequestParam(defaultValue = "id") String sortBy) {
        return (Page<Invoices>) invoicesService.findByClient(clientId, page, size, sortBy);
    }
    @GetMapping("/byState")
    @PreAuthorize("hasAuthority('ADMIN')")
    //URL: /invoices/byState?state={state}
    public Page<Invoices> getInvoicesByState(@RequestParam String state,
                                             @RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size,
                                             @RequestParam(defaultValue = "id") String sortBy) {
        return (Page<Invoices>) invoicesService.findByState(state, page, size, sortBy);
    }
    @GetMapping("/byDate")
    @PreAuthorize("hasAuthority('ADMIN')")
    //URL: /invoices/byDate?date={date}
    public Page<Invoices> getInvoicesByDate(@RequestParam Date date,
                                            @RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size,
                                            @RequestParam(defaultValue = "id") String sortBy) {
        return (Page<Invoices>) invoicesService.findByDate(date, page, size, sortBy);
    }
    @GetMapping("/byYear")
    @PreAuthorize("hasAuthority('ADMIN')")
    //URL: /invoices/byYear?year={year}
    public Page<Invoices> getInvoicesByYear(@RequestParam int year,
                                            @RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size,
                                            @RequestParam(defaultValue = "id") String sortBy) {
        return (Page<Invoices>) invoicesService.findByYear(year, page, size, sortBy);
    }
    @GetMapping("/byAmountRange")
    @PreAuthorize("hasAuthority('ADMIN')")
    //URL: /invoices/byAmountRange?minAmount={minAmount}&maxAmount={maxAmount}
    public Page<Invoices> getInvoicesByAmountRange(@RequestParam double minAmount,
                                                   @RequestParam double maxAmount,
                                                   @RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "10") int size,
                                                   @RequestParam(defaultValue = "id") String sortBy) {
        return (Page<Invoices>) invoicesService.findByAmountRange(minAmount, maxAmount, page, size, sortBy);
    }
    @PostMapping
    //URL: /invoices + payload
    public NewInvoicesRespDTO createInvoices(@RequestBody @Validated NewInvoicesDTO body, BindingResult validation) {
        if(validation.hasErrors()){
            System.out.println(validation.getAllErrors());
            throw new BadRequestException(validation.getAllErrors());
        }
        return new NewInvoicesRespDTO(this.invoicesService.saveInvoices(body).getId()) ;
    }
    @PutMapping("/{invoicesId}")
    //URL: /invoices/{invoicesId} + payload
    public Invoices updateInvoices(@PathVariable Long id, @RequestBody Invoices updatedInvoices) {
        return invoicesService.findByIdAndUpdate(id, updatedInvoices);
    }
    @DeleteMapping("/{invoicesId}")
    //URL: /invoices/{invoicesId}
    public void deleteInvoices(@PathVariable Long id) {
        invoicesService.findByIdAndDelete(id);
    }
    private NewInvoicesDTO convertToNewInvoicesDTO(Invoices invoices) {
        return new NewInvoicesDTO(invoices.getDate(), invoices.getAmount(),
                invoices.getNumber(), invoices.getState(), invoices.getClient());
    }
    @PatchMapping("/{invoicesId}")
    //URL: /invoices/{invoicesId} + payload
    public Invoices updateInvoicesState(@PathVariable Long id, @RequestBody Map<String, String> updates) {
        Invoices modInvoices = invoicesService.findById(id);
        if (updates.containsKey("state")) {
            modInvoices.setState(updates.get("state"));
        }
        NewInvoicesDTO ConvertModInvoices= convertToNewInvoicesDTO(modInvoices);
        return invoicesService.saveInvoices(ConvertModInvoices);
    }

}
