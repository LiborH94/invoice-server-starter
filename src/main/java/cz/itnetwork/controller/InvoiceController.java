package cz.itnetwork.controller;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.mapper.InvoiceMapper;
import cz.itnetwork.entity.filter.InvoiceFilter;
import cz.itnetwork.entity.repository.InvoiceRepository;
import cz.itnetwork.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceMapper invoiceMapper;

    @PostMapping("/invoices")
    public InvoiceDTO addInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        return invoiceService.addInvoice(invoiceDTO);
    }

    @GetMapping("/invoices")
    public List<InvoiceDTO> getAllInvoices(InvoiceFilter invoiceFilter){
        return invoiceService.getAll(invoiceFilter);
    }

    @GetMapping("/invoices/{id}")
    public InvoiceDTO getInvoice(@PathVariable Long id) {
        return invoiceService.getInvoice(id);
    }

    @PutMapping("/invoices/{id}")
    public InvoiceDTO editInvoice(@PathVariable Long id, @RequestBody InvoiceDTO invoiceDTO) {
        return invoiceService.editInvoice(id, invoiceDTO);
    }

    @DeleteMapping("/invoices/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long id) {
        invoiceService.removeInvoice(id);
        return ResponseEntity.noContent().build(); // Vrátí kód 204 (No Content)
    }
}