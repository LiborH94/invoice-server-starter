package cz.itnetwork.service;

import cz.itnetwork.dto.StatisticForPersonsDTO;
import cz.itnetwork.dto.StatisticForInvoicesDTO;

import java.util.List;

public interface StatisticService{

    List <StatisticForPersonsDTO> statisticForPersons();

    StatisticForInvoicesDTO statisticForInvoices();
}
