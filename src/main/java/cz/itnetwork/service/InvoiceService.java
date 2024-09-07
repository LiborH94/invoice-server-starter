package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.entity.filter.InvoiceFilter;

import java.util.List;

public interface InvoiceService {

    InvoiceDTO addInvoice (InvoiceDTO invoiceDTO);

    List <InvoiceDTO> getAll(InvoiceFilter invoiceFilter);

    void removeInvoice(Long id);

    InvoiceDTO editInvoice (Long id, InvoiceDTO invoiceDTO);

    InvoiceDTO getInvoice (Long id);

}

