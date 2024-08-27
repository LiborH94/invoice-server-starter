package cz.itnetwork.service;

import cz.itnetwork.dto.StatisticForPersonsDTO;
import cz.itnetwork.dto.StatisticForInvoicesDTO;
import cz.itnetwork.entity.repository.InvoiceRepository;
import cz.itnetwork.entity.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticServiceImpl implements StatisticService{

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private PersonRepository personRepository;

    @Override
    public StatisticForInvoicesDTO statisticForInvoices() {
        // Získání statistik z databáze
        Integer totalInvoices = invoiceRepository.countAllInvoices();
        Integer currentYearSum = invoiceRepository.findCurrentYearSum();
        Integer allTimeSum = invoiceRepository.findAllTimeSum();

        return new StatisticForInvoicesDTO(totalInvoices, currentYearSum, allTimeSum);
    }

    @Override
    public List<StatisticForPersonsDTO> statisticForPersons() {
        return personRepository.findStatisticsForPersons();


    }
}