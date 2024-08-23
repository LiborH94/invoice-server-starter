package cz.itnetwork.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticsForInvoicesDTO {

    private Integer currentYearSum;
    private Integer allTimeSum;
    private Integer invoicesCount;
}
