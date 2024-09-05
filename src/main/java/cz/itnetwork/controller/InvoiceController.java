package cz.itnetwork.controller;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.StatisticForInvoicesDTO;
import cz.itnetwork.dto.mapper.InvoiceMapper;
import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.filter.InvoiceFilter;
import cz.itnetwork.entity.repository.InvoiceRepository;
import cz.itnetwork.entity.repository.specification.InvoiceSpecification;
import cz.itnetwork.service.InvoiceService;
import cz.itnetwork.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private StatisticService statisticService;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceMapper invoiceMapper;

    @PostMapping("/invoices")
    public InvoiceDTO addInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        return invoiceService.addInvoice(invoiceDTO);
    }

    @GetMapping("/invoices")
    public List<InvoiceDTO> getInvoices(
            @RequestParam(required = false) Long buyerID,
            @RequestParam(required = false) Long sellerID,
            @RequestParam(required = false) String product,
            @RequestParam(required = false) Integer minPrice,
            @RequestParam(required = false) Integer maxPrice,
            @RequestParam(defaultValue = "10") Integer limit) {

        // Vytvoření filtru na základě parametrů
        InvoiceFilter filter = new InvoiceFilter();
        filter.setBuyerID(buyerID);
        filter.setSellerID(sellerID);
        filter.setProduct(product);
        filter.setMinPrice(minPrice);
        filter.setMaxPrice(maxPrice);

        // Vytvoření specifikace s filtrem
        InvoiceSpecification specification = new InvoiceSpecification(filter);

        // Vyhledávání faktur podle specifikace bez stránkování
        List<InvoiceEntity> invoices = invoiceRepository.findAll(specification);

        // Omezit počet výsledků podle parametru limit
        return invoices.stream()
                .limit(limit)
                .map(invoiceMapper::toDTO)
                .collect(Collectors.toList());
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
        return ResponseEntity.noContent().build(); // Vrátí status 204 (No Content)
    }

    @GetMapping("/invoices/statistics")
    public StatisticForInvoicesDTO statisticForInvoices() {
        return statisticService.statisticForInvoices();
    }
}