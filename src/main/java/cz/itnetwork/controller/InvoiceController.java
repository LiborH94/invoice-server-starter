package cz.itnetwork.controller;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.StatisticForInvoicesDTO;
import cz.itnetwork.service.InvoiceService;
import cz.itnetwork.service.StatisticService;
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
    private StatisticService statisticService;

    @PostMapping("/invoices")
    public InvoiceDTO addInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        return invoiceService.addInvoice(invoiceDTO);
    }

    @GetMapping("/invoices")
    public List<InvoiceDTO> getAll() {
        return invoiceService.getAll();
    }
    @GetMapping("/invoices/{id}")
    public InvoiceDTO getInvoice (@PathVariable Long id) {
        return invoiceService.getInvoice(id);
    }
    @PutMapping("/invoices/{id}")
    public InvoiceDTO editInvoice (@PathVariable Long id, @RequestBody InvoiceDTO invoiceDTO) {
        return invoiceService.editInvoice(id, invoiceDTO);
    }

    @DeleteMapping("/invoices/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long id) {
        invoiceService.removeInvoice(id);
        return ResponseEntity.noContent().build(); // Vrátí status 204 (No Content)
    }

    @GetMapping("/invoices/statistics")
    public StatisticForInvoicesDTO statisticForInvoices() {
        return statisticService.statisticForInvoices();
    }
}
