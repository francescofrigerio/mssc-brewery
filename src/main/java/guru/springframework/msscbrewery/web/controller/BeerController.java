package guru.springframework.msscbrewery.web.controller;

import guru.springframework.msscbrewery.services.BeerService;
import guru.springframework.msscbrewery.web.model.BeerDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Created by jt on 2019-04-20.
 */
// Versione deprecata
@Deprecated
 // Per testare il tutto da postman
 // http://localhost:8080/api/v1/beer/02804930-1b12-11ee-be56-0242ac120002
 // dopo aver generato un UUID online https://www.uuidgenerator.net/version1
 // invece 
 // http://localhost:8080/api/v1/beer/foo -> badrequest  "status": 400,
// Senza questa annotazoione il Controller non espone
    // i suoi servizi
@RequestMapping("/api/v1/beer")
// Alias dell'annotazione Controller
// che definisce e raggruppa alcune annotazioni
// tra cui ResponseBody è quella più significativa
// Potremmo riassumere RestController=Controller+ResponseBody
// Vedere l'interfaccia cliccando su Go To Implementation
// Vecchia versione di Thompson
// Annotazione valida e pubblica per l'intera classe
// mentre sotto lo trasformo in valido e pubblico
// solo per il metodo getBeer
@RestController
public class BeerController {

    // E' meglio mettere un codice minimale nel Controller
    // e la logica di business nel servizio.
    private final BeerService beerService;

    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    // alternativa a RequestMapping ? a livello di classe
    // Noi ritorniamo un oggetto pojo BeerDto
    // e SpringMvc lo serializza e trasforma in un json
    // La classe ResponseEntity permette di avere un
    // maggior controllo . Le parentesi grafe
    // indicano a Spring che beerid è una variabile
    @GetMapping({"/{beerId}"})
    // Dichiaro esplicitamente per renderlo piu chiaro
    // anche se SpringBoot si comporterebbe in automatico
    // allo stesso modo che beerId è il nome della variabile
    // passata nell'url alla chiamata
    public ResponseEntity<BeerDto> getBeer(@PathVariable("beerId") UUID beerId){

        // Senza il servizio dovremmo scrivere direttamente
        // return new ResponseEntity<>(BeerDto.builder()..build(),HttpStatus_OK);
        return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
    }


    // PostMapping gestisce la create di una nuova birra
    // In questo modo facciamo una post
    // di un oggetto Json in un oggetto BeerDto
    // L'operazione POST NON viene considerata IDEMPOTENTE
    // perche' più chiamate produrranno sempre
    // un nuovo risultato con nuovi oggetto creati
    // NOTA PER IL TEST
    // Attenzione a non dimenticare l'annotazione
    // @RequestBody altrimento non funzionera il test
    // e neanche runtime
    // NOTA PER IL TEST
    @PostMapping // POST - create new beer (senza sapere UUID)
    //public ResponseEntity handlePost(BeerDto beerDto){
    public ResponseEntity handlePost(@RequestBody BeerDto beerDto){

        // NOTA PER IL TEST
        // Se metto un break point ed eseguo
        // il metodo handleTest vedo in debug
        // che l'oggetto non contiene nulla
        // motivo per cui la successiva get sempre
        // nel test va in errore
        // NOTA PER IL TEST
        BeerDto savedDto = beerService.saveNewBeer(beerDto);

        HttpHeaders headers = new HttpHeaders();
        //todo add hostname to url
        // Ritorna come fare a trovare questa risorsa lato client
        headers.add("Location", "/api/v1/beer/" + savedDto.getId().toString());

        // STATUS 201 Created
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    // Update an existint entity
    // La proprieta id può essere
    // considerata una proprieta in solo lettura
    // per cui i client non possono modificarlo
    // quando manipoliamo un update passando un valore
    // di UUID e un oggetto Dto in realtà lo Spring Framework
    // andrà a prendere una rappresentazione in Json
    // dell'oggetto BeerDto costruita nell'oggetto BeerDto
    // che sarà passato al servizio chiamato il quale
    // dovrebbe mandare avanti la persistenza dell'oggetto
    // passato cosa che al momento non abbiamo
    // implementato
    // L'operazione PUT viene considerata IDEMPOTENTE
    // perche' più chiamate produrranno lo stesso
    // risultato con la modifica dello stesso oggetto
    // identica alle precedenti.
    // Attenzione a non dimenticare l'annotazione
    // @RequestBody altrimento non funzionera il test
    // e neanche runtime
    // NOTA PER IL TEST
    @PutMapping({"/{beerId}"})
    //public ResponseEntity<BeerDto> handleUpdate(@PathVariable("beerId") UUID beerId , BeerDto beerDto)
    public ResponseEntity<BeerDto> handleUpdate(@PathVariable("beerId") UUID beerId , @RequestBody BeerDto beerDto)
    {

        beerService.updateBeer(beerId, beerDto);
        // status NO_CONTENT = 204
        // significa che x il momento
        // non abbiamo modificato alcun contenuto
        // perchè lato backend non abbiamo fatto nulla.
        // non esiste alcun contenuto nel corpo(body)
        return new ResponseEntity(HttpStatus.NO_CONTENT);


    }

    @DeleteMapping({"/{beerId}"})
    // Per Testare il client
    // vedere il progetto mssc-brewery-client
    // Il test semplice sulla delete
    // dovrebbe dare un eccezzione num 400
    // basta settare BAD_REQUEST come status
    @ResponseStatus(HttpStatus.NO_CONTENT)
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    public void deleteBeer(@PathVariable("beerId") UUID beerId){
        beerService.deleteById(beerId);
    }



}
