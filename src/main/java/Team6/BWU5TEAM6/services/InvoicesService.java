package Team6.BWU5TEAM6.services;

import Team6.BWU5TEAM6.dto.NewInvoicesDTO;
import Team6.BWU5TEAM6.entities.Invoices;
import Team6.BWU5TEAM6.exceptions.NotFoundException;
import Team6.BWU5TEAM6.repositories.InvoicesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class InvoicesService {

    @Autowired
    private InvoicesDAO invoicesDAO;

    @Autowired
    private ClientService cs;

    public Page<Invoices> getInvoicesList(int page, int size, String sortBy) {
        if (size > 50) size = 50;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.invoicesDAO.findAll(pageable);
    }

    public Invoices saveInvoices(NewInvoicesDTO body) {
        Invoices newInvoices = new Invoices(body.date(), body.amount(), body.number(), body.state(), cs.findbyId(body.client().getId()));
        return invoicesDAO.save(newInvoices);
    }

    public Invoices findById(Long invoicesId) {
        return this.invoicesDAO.findById(invoicesId).orElseThrow(() -> new NotFoundException(invoicesId));
    }

    public Invoices findByIdAndUpdate(Long invoicesId, Invoices updatedInvoices) {
        Invoices found = this.findById(invoicesId);
        found.setDate(updatedInvoices.getDate());
        found.setAmount(updatedInvoices.getAmount());
        found.setNumber(updatedInvoices.getNumber());
        found.setState(updatedInvoices.getState());
        found.setClient(updatedInvoices.getClient());
        return this.invoicesDAO.save(found);
    }

    public void findByIdAndDelete(Long invoicesId) {
        Invoices found = this.findById(invoicesId);
        this.invoicesDAO.delete(found);
    }

    public Page<Invoices> findByClient(Long clientId, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return invoicesDAO.findByClientId(clientId, pageable);
    }

    public Page<Invoices> findByState(String state, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return invoicesDAO.findByState(state, pageable);
    }

    public Page<Invoices> findByDate(Date date, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return invoicesDAO.findByDate(date, pageable);
    }

    public Page<Invoices> findByYear(int year, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return invoicesDAO.findByYear(year, pageable);
    }

    public Page<Invoices> findByAmountRange(double minAmount, double maxAmount, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return invoicesDAO.findByAmountBetween(minAmount, maxAmount, pageable);
    }
}
