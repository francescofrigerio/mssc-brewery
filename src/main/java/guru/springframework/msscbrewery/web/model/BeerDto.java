package guru.springframework.msscbrewery.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Created by jt on 2019-04-20.
 */
// Data Annoration permette di creare
// in automatico i metodi getter setters ecc.
@Data
@NoArgsConstructor
@AllArgsConstructor
// Buildet annotation permette di lavorare con il
// builder pattern . Vedere la classe BeerServiceImpl
@Builder
public class BeerDto {

    private UUID id;
    private String beerName;
    private String beerStyle;
    private Long upc;
}
