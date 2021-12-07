package com.demo.client;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import static com.demo.constants.ApplicationConstants.*;
import com.demo.dto.CustomerDTO;


@Slf4j
public class CustomerRestClient {

	private WebClient webClient;
	
	public CustomerRestClient(WebClient webClient) {
		this.webClient = webClient;
	}
	
    public List<CustomerDTO> retrieveAllCustomers() {
        try {
            return webClient.get().uri(CUSTOMERS_ENDPOINT_URL)
                    .retrieve()
                    .bodyToFlux(CustomerDTO.class)
                    .collectList()
                    .block();
        } catch (WebClientResponseException ex) {
            log.error("Error Response code is : {} and the message is {}", ex.getRawStatusCode(), ex.getResponseBodyAsString());
            log.error("WebClientResponseException in retrieveAllCustomers", ex);
            throw ex;
        } catch (Exception ex) {
            log.error("Exception in retrieveAllCustomers ", ex);
            throw ex;
        }
    }
    
    public CustomerDTO retrieveCustomerById(int customerId) {

        try {
        	String url = CUSTOMERS_ENDPOINT_URL.concat("/{customerId}");
            return webClient.get().uri(url, customerId)
                    .retrieve()
                    .bodyToMono(CustomerDTO.class)
                    .block();
        } catch (WebClientResponseException ex) {
        	System.out.println("WebClientResponseException >>> " + ex);
            log.error("Error Response code is : {} and the message is {}", ex.getRawStatusCode(), ex.getResponseBodyAsString());
            log.error("WebClientResponseException in retrieveCustomerById", ex);
            throw ex;
        } catch (Exception ex) {
            log.error("Exception in retrieveCustomerById ", ex);
            throw ex;
        }
    }
    
    public CustomerDTO addNewCustomer(CustomerDTO customerDTO) {
        try {
            return webClient.post().uri(CUSTOMERS_ENDPOINT_URL)
                    .bodyValue(customerDTO)
                    .retrieve()
                    .bodyToMono(CustomerDTO.class)
                    .block();
        } catch (WebClientResponseException ex) {
            log.error("Error Response code is : {} and the message is {}", ex.getRawStatusCode(), ex.getResponseBodyAsString());
            log.error("WebClientResponseException in addNewCustomer", ex);
            throw ex;
        } catch (Exception ex) {
            log.error("Exception in addNewCustomer ", ex);
            throw ex;
        }
    }
    
    public String deleteCustomerById(int customerId) {
        try {
        	String url = CUSTOMERS_ENDPOINT_URL.concat("/{customerId}");
            return webClient.delete().uri(url , customerId)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        } catch (WebClientResponseException ex) {
            log.error("Error Response code is : {} and the message is {}", ex.getRawStatusCode(), ex.getResponseBodyAsString());
            log.error("WebClientResponseException in deleteCustomerById", ex);
            throw ex;
        } catch (Exception ex) {
            log.error("Exception in deleteCustomerById ", ex);
            throw ex;
        }

    }
    
    public CustomerDTO updateCustomer(int customerId, CustomerDTO customerDTO) {

        try {
        	String url = CUSTOMERS_ENDPOINT_URL.concat("/{customerId}");
            return webClient.put().uri(url, customerId)
                    .bodyValue(customerDTO)
                    .retrieve()
                    .bodyToMono(CustomerDTO.class)
                    .block();
        } catch (WebClientResponseException ex) {
            log.error("Error Response code is : {} and the message is {}", ex.getRawStatusCode(), ex.getResponseBodyAsString());
            log.error("WebClientResponseException in updateCustomer", ex);
            throw ex;
        } catch (Exception ex) {
            log.error("Exception in updateCustomer ", ex);
            throw ex;
        }
    }
    
 
}
