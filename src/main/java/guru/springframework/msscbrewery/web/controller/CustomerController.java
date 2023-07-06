package guru.springframework.msscbrewery.web.controller;


import guru.springframework.msscbrewery.services.CustomerService;
import guru.springframework.msscbrewery.web.model.BeerDto;
import guru.springframework.msscbrewery.web.model.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

// Per testare il tutto da postman
// http://localhost:8080/api/v1/customer/02804930-1b12-11ee-be56-0242ac120002
// dopo aver generato un UUID online https://www.uuidgenerator.net/version1
// invece
// http://localhost:8080/api/v1/customer/foo -> badrequest  "status": 400,
@Slf4j
@RequestMapping("api/v1/customer")
@RestController
public class CustomerController {

    final private CustomerService  customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable("customerId") UUID customerId)
    {
        log.debug("[CustomerController] START getCustomer");
        return new ResponseEntity<>(customerService.getCustomerById(customerId), HttpStatus.OK);
    }

    // Prendiamo un oggetto CustomerDto
    // nella forma di un oggetto Json contro
    // le Api V1
    // Attenzione a non dimenticare l'annotazione @RequestBody
    // come indicato nella lezione che tratta della classe
    // dedicata al test altrimenti l'oggetto CustomerDto avrà dei campi
    // vuoti runtime
    @PostMapping // POST - create new beer
    //public ResponseEntity handlePost(CustomerDto customerDto){
        public ResponseEntity handlePost(@RequestBody  CustomerDto customerDto){

        CustomerDto savedDto = customerService.saveNewCustomer(customerDto);

        HttpHeaders headers = new HttpHeaders();
        //todo add hostname to url
        headers.add("Location", "/api/v1/customer/" + savedDto.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    // Update an existint entity
    // Attenzione a non dimenticare l'annotazione @RequestBody
    // come indicato nella lezione che tratta della classe
    // dedicata al test altrimenti l'oggetto CustomerDto avrà dei campi
    // vuoti runtime
    // L'annotazione RequestBody dice a SpringMvc
    // quando abbiamo un contenuto nel corpo(body)
    // del Json che dovremmo avere il corpo
    // della richiesta che abbiamo intenzione
    // di costruire mediante una bind
    // usando  la libreria jackson sull'oggetto Dto
    @PutMapping({"/{customerId}"})
    //public ResponseEntity<CustomerDto> handleUpdate(@PathVariable("beerId") UUID beerId , CustomerDto customerDto)  {
    public ResponseEntity<CustomerDto> handleUpdate(@PathVariable("beerId") UUID beerId , @RequestBody  CustomerDto customerDto)  {

        customerService.updateCustomer(beerId, customerDto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);


    }

    @DeleteMapping({"/{customerId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeer(@PathVariable("customerId") UUID beerId){

        customerService.deleteById(beerId);
    }
}
