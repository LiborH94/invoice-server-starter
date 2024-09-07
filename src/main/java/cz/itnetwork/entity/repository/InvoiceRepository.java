package cz.itnetwork.entity.repository;

import cz.itnetwork.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long>, JpaSpecificationExecutor<InvoiceEntity> {

    @Query("SELECT SUM(i.price) FROM invoice i WHERE YEAR(i.issued) = YEAR(CURRENT_DATE)")
    Integer findCurrentYearSum();

    @Query("SELECT SUM(i.price) FROM invoice i")
    Integer findAllTimeSum();

    @Query("SELECT COUNT(i) FROM invoice i")
    Integer countAllInvoices();

    List<InvoiceEntity> findByBuyerIdentificationNumber(String ic);

    List<InvoiceEntity> findBySellerIdentificationNumber(String ic);

}
