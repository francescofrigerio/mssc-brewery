package guru.springframework.msscbrewery.services;


import guru.springframework.msscbrewery.web.model.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    @Override
    public CustomerDto getCustomerById(UUID customerId) {
        // La classe Pojo è annotata con Builder
        System.out.println("[CustomerServiceImpl] START getCustomerById");
        return CustomerDto.builder().id(UUID.randomUUID())
                .name("Joe Buck")
                .build();
    }

    @Override
    public CustomerDto saveNewCustomer(CustomerDto customerDto) {
        return CustomerDto.builder()
                .id(UUID.randomUUID())
                .build();
    }


    @Override
    public void updateCustomer(UUID beerId, CustomerDto customerDto) {
        log.debug("[CustomerServiceImpl] START updateCustomer " + customerDto.getName());
        //todo impl - would add a real impl to update beer
    }

    @Override
    public void deleteById(UUID customerId) {
        log.debug("[CustomerServiceImpl] START deleteById " + customerId.toString());
        //todo impl - would add a real impl to update beer
    }


}
