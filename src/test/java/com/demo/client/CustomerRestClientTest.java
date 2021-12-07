package com.demo.client;

import java.util.List;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static com.demo.constants.ApplicationConstants.*;
import com.demo.dto.CustomerDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomerRestClientTest {
	
	WebClient webClient = WebClient.create(BASE_SERVICE_URL);
	CustomerRestClient customerRestClient = new CustomerRestClient(webClient);
	
  @Test
    void retrieveAllCustomers(){
        List<CustomerDTO> customerList = customerRestClient.retrieveAllCustomers();
       System.out.println("retrieveAllCustomers >>> " + customerList);
        assertTrue(customerList.size()>0);
    }
    
    @Test
    void retrieveCustomerById(){
    	 int customerId = 11;
       CustomerDTO customer = customerRestClient.retrieveCustomerById(customerId);
       System.out.println("retrieveCustomerById >>> " + customer);
       assertEquals("sachin3", customer.getName());
    } 
    
  @Test
    void retrieveCustomerById_NotFound(){
        int customerId = 100;
        Assertions.assertThrows(WebClientResponseException.class, () -> customerRestClient.retrieveCustomerById(customerId));

    }
  
  @Test
  void addNewCustomer(){
	  CustomerDTO customer = new CustomerDTO(null,"James", "james@gmail.com", "1991-01-25");

	  CustomerDTO respCustomer = customerRestClient.addNewCustomer(customer);
      System.out.println("respCustomer  : " + respCustomer);
      assertTrue(respCustomer.getId()!=null);

  }
  
  @Test
  void deleteCustomer(){
	  int customerId = 2;
      String message = customerRestClient.deleteCustomerById(2);
      String expectedMessage = "Employee deleted successfully.";
      assertEquals(expectedMessage,message);
  }
  
  @Test
  void updateCustomer(){

      int customerId = 2;
      CustomerDTO customer = new CustomerDTO(null,"Jack", null, null);
      CustomerDTO updatedCustomer = customerRestClient.updateCustomer(customerId, customer);
      assertEquals("Jack", updatedCustomer.getName());

  }


}