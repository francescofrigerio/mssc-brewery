package guru.springframework.msscbrewery.web.controller.v2;


import guru.springframework.msscbrewery.services.v2.BeerServiceV2;
import guru.springframework.msscbrewery.web.model.v2.BeerDtoV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

// New major version
// LEZIONE 76
@Slf4j
@RequiredArgsConstructor
// LEZIONE 76
@RequestMapping("/api/v2/beer")
@RestController
public class BeerControllerV2 {

    @Autowired
    private final BeerServiceV2 beerServiceV2;

    // LEZIONE 76
    // Viene creato in auto da lombok vedere bytecode sotto target
    //public BeerControllerV2(BeerServiceV2 beerServiceV2) {
    //    this.beerServiceV2 = beerServiceV2;
    //}
    // LEZIONE 76

    @GetMapping({"/{beerId}"})
    public ResponseEntity<BeerDtoV2> getBeer(@PathVariable("beerId") UUID beerId){

        return new ResponseEntity<>(beerServiceV2.getBeerById(beerId), HttpStatus.OK);
    }

    @PostMapping // POST - create new beer
    public ResponseEntity handlePost(BeerDtoV2 beerDto){

        // LEZIONE 76
        //BeerDtoV2 savedDto = beerServiceV2.saveNewBeer(beerDto);
        // HttpHeaders headers = new HttpHeaders();
        log.debug("in handle post modificato il 30082023...");

        val savedDto = beerServiceV2.saveNewBeer(beerDto);
        var headers = new HttpHeaders();
        // LEZIONE 76
        //todo add hostname to url
        headers.add("Location", "/api/v1/beer/" + savedDto.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping({"/{beerId}"})
    public ResponseEntity handleUpdate(@PathVariable("beerId") UUID beerId, BeerDtoV2 beerDto){

        beerServiceV2.updateBeer(beerId, beerDto);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping({"/{beerId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeer(@PathVariable("beerId") UUID beerId){
        beerServiceV2.deleteById(beerId);
    }
}