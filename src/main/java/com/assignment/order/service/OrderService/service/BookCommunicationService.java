package com.assignment.order.service.OrderService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.assignment.order.service.OrderService.DTO.BookDTO;
import com.assignment.order.service.OrderService.DTO.StockUpdateRequestDTO;
import com.assignment.order.service.OrderService.exceptions.BookNotFoundException;

@Service
public class BookCommunicationService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String BOOK_SERVICE_URL = "http://localhost:8082/books";

    public BookDTO retrieveBookDetailsByBookId(Long id) {

        try {
            return restTemplate.getForObject(BOOK_SERVICE_URL + "/" + id, BookDTO.class);
        }
        catch ( HttpStatusCodeException e) {
            System.err.println(e.getMessage());
            throw new BookNotFoundException("Book not found while ordering");
        }
    }
    
    public void updateStockOfBook(Long id,int newStock) {

        try {
        	String url = BOOK_SERVICE_URL + "/" + id + "/stock";
        	 HttpHeaders headers = new HttpHeaders();
             headers.set("X-INTERNAL", "ROLE_SERVICE_COMMUNICATOR");

             StockUpdateRequestDTO stockUpdateRequest = new StockUpdateRequestDTO();
             stockUpdateRequest.setStock(newStock);

             HttpEntity<StockUpdateRequestDTO> requestEntity = new HttpEntity<>(stockUpdateRequest, headers);

             restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Void.class);
        }
        catch ( HttpStatusCodeException e) {
            System.err.println(e.getMessage());
            throw new BookNotFoundException("Creation of order failed maybe due to book not found or updating stock");
        }
    }
}
