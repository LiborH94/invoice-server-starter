package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;

import java.util.List;
import java.util.Map;

public interface InvoiceService {

    InvoiceDTO addInvoice (InvoiceDTO invoiceDTO);

    List <InvoiceDTO> getAll();

    void removeInvoice(Long id);

    InvoiceDTO editInvoice (Long id, InvoiceDTO invoiceDTO);

    InvoiceDTO getInvoice (Long id);

}

