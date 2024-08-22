package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.PersonDTO;
import cz.itnetwork.dto.mapper.InvoiceMapper;
import cz.itnetwork.dto.mapper.PersonMapper;
import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.PersonEntity;
import cz.itnetwork.entity.repository.InvoiceRepository;
import cz.itnetwork.entity.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceMapper invoiceMapper;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private PersonRepository personRepository;


    @Override
    public InvoiceDTO addInvoice(InvoiceDTO invoiceDTO) {
        InvoiceEntity invoice = invoiceMapper.toEntity(invoiceDTO);
        invoice = invoiceRepository.save(invoice);
        setBuyerAndSeller(invoice, invoiceDTO);

        return invoiceMapper.toDTO(invoice); // vypíše invoice kompletně i s hodnotami o sellerovi a buyerovi
    }
    @Override
    public List<InvoiceDTO> getAll() {
        return invoiceRepository.findAll()
                .stream()
                .map(i -> invoiceMapper.toDTO(i))
                .collect(Collectors.toList());
    }

    @Override
    public InvoiceDTO getInvoice(Long id) {
        InvoiceEntity invoice = invoiceRepository.getReferenceById(id);
        return invoiceMapper.toDTO(invoice);
    }

    @Override
    public InvoiceDTO editInvoice(Long Id, InvoiceDTO invoiceDTO) {
        if (!invoiceRepository.existsById(Id)) {
            throw new EntityNotFoundException("Invoice with id " + Id + " wasn't found in the database.");
        }
        InvoiceEntity invoice = invoiceMapper.toEntity(invoiceDTO);
        InvoiceEntity saved = invoiceRepository.save(invoice);
        setBuyerAndSeller(invoice, invoiceDTO);

        return invoiceMapper.toDTO(saved);
    }

    @Override
    public void removeInvoice(Long id) {
            InvoiceEntity invoice = fetchInvoiceById(id);
            invoiceRepository.delete(invoice);

    }

    public Map<String, Integer> getStatistics() {

        Integer currentYearSum = invoiceRepository.findCurrentYearSum();
        Integer allTimeSum = invoiceRepository.findAllTimeSum();
        Integer invoicesCount = invoiceRepository.countAllInvoices();

        Map<String, Integer> statistics = new LinkedHashMap<>();

        statistics.put("currentYearSum", currentYearSum);
        statistics.put("allTimeSum", allTimeSum);
        statistics.put("invoicesCount", invoicesCount);


        return statistics;
    }

    private InvoiceEntity fetchInvoiceById(long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Invoice with id " + id + " wasn't found in the database."));
    }
    //pomocná metoda, abychom zachovali DRY u metod addInvoice a editInvoice
    private void setBuyerAndSeller(InvoiceEntity invoice, InvoiceDTO invoiceDTO) {
        PersonEntity buyer = personRepository.getReferenceById(invoiceDTO.getBuyer().getId());
        PersonEntity seller = personRepository.getReferenceById(invoiceDTO.getSeller().getId());
        invoice.setBuyer(buyer);//vezme si data o buyerovi, kterého zjistí podle id a přiřadí hodnoty
        invoice.setSeller(seller);//vezme si data o sellerovi, kterého zjistí podle id a přiřadí hodnoty
    }

}

