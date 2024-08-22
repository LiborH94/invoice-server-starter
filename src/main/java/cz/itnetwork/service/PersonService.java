package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.PersonDTO;

import java.util.List;

public interface PersonService {


    PersonDTO addPerson(PersonDTO personDTO);

    void removePerson(Long id);

    List<PersonDTO> getAll();

    PersonDTO getPerson(Long personId);

    PersonDTO editPerson(Long personId, PersonDTO personDTO);

    List<InvoiceDTO> findSalesByIC(String ic);

    List<InvoiceDTO> findPurchasesByIC(String ic);



}
