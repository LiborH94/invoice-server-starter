package cz.itnetwork.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticForPersonsDTO {

    private Long id;
    private String personName;
    private Long revenue;

}
