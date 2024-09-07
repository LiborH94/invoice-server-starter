package cz.itnetwork.controller;

import cz.itnetwork.dto.StatisticForInvoicesDTO;
import cz.itnetwork.dto.StatisticForPersonsDTO;
import cz.itnetwork.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class StatisticsController {

    @Autowired
    private StatisticService statisticService;

    @GetMapping("/invoices/statistics")
    public StatisticForInvoicesDTO statisticForInvoices() {
        return statisticService.statisticForInvoices();
    }

    @GetMapping("persons/statistics")
    public List<StatisticForPersonsDTO> statisticForPersons() {
        return statisticService.statisticForPersons();
    }


}
