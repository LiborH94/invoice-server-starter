package cz.itnetwork.entity.repository;

import cz.itnetwork.dto.StatisticForPersonsDTO;
import cz.itnetwork.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

    List<PersonEntity> findByHidden(boolean hidden);

    @Query("SELECT new cz.itnetwork.dto.StatisticForPersonsDTO(p.id AS personId, p.name AS personName, COALESCE(SUM(i.price), 0) AS revenue) " +
            "FROM person p LEFT JOIN p.purchases i " +
            "WHERE p.hidden = false " +
            "GROUP BY p.id, p.name " +
            "ORDER BY p.id")
    List<StatisticForPersonsDTO> findStatisticsForPersons();
}
