package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.web.model.BeerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by jt on 2019-04-20.
 */
// E' meglio mettere un codice minimale nel Controller
// e la logica di business nel servizio.
    // In caso di utilizzo del servizio
    // da parte di una sola classe va benissimi
    // usare il suffisso Impl come nome della classe
    // altrimenti in caso di piu' classi dare un nome
    // meno generico.
@Slf4j
@Service
public class BeerServiceImpl implements BeerService {
    @Override
    public BeerDto getBeerById(UUID beerId) {
        // La classe Pojo è annotata con Builder
        return BeerDto.builder().id(UUID.randomUUID())
                .beerName("Galaxy Cat")
                .beerStyle("Pale Ale")
                .build();
    }

    @Override
    // In questo metodo implementiamo uno strato
    // persistente che determina il valore del campo id
    public BeerDto saveNewBeer(BeerDto beerDto) {
        return BeerDto.builder()
                .id(UUID.randomUUID())
                .build();
    }

    @Override
    public void updateBeer(UUID beerId, BeerDto beerDto) {
        //todo impl - would add a real impl to update beer
        log.debug("[BaseServiceImpl] START updateBeer " + beerDto.getBeerName());
    }

    @Override
    public void deleteById(UUID beerId) {
        log.debug("[BaseServiceImpl] START deleteById " + beerId.toString());
        //todo impl - would add a real impl to update beer
    }


}
