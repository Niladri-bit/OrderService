package com.assignment.order.service.OrderService.service;

import org.springframework.beans.factory.annotation.Autowired;
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
            StockUpdateRequestDTO stockUpdateRequest = new StockUpdateRequestDTO();
            stockUpdateRequest.setStock(newStock);
            restTemplate.put(url, stockUpdateRequest);
        }
        catch ( HttpStatusCodeException e) {
            System.err.println(e.getMessage());
            throw new BookNotFoundException("Book not found while ordering");
        }
    }
}
