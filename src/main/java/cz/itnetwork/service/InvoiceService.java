package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;

import java.util.List;

public interface InvoiceService {

    InvoiceDTO addInvoice (InvoiceDTO invoiceDTO);

    List <InvoiceDTO> getAll();

    void removeInvoice(Long id);

    InvoiceDTO editInvoice (Long id, InvoiceDTO invoiceDTO);

    InvoiceDTO getInvoice (Long id);

    List<InvoiceDTO> findSalesByIC(String ic);

    List<InvoiceDTO> findPurchasesByIC(String ic);

}

