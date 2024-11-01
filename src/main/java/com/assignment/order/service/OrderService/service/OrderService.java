package com.assignment.order.service.OrderService.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.order.service.OrderService.DTO.BookDTO;
import com.assignment.order.service.OrderService.DTO.CreateOrderDTO;
import com.assignment.order.service.OrderService.DTO.OrderResponseDTO;
import com.assignment.order.service.OrderService.DTO.TokenInformationDTO;
import com.assignment.order.service.OrderService.entities.OrderEntity;
import com.assignment.order.service.OrderService.exceptions.OrderNotFoundException;
import com.assignment.order.service.OrderService.exceptions.UnauthorizedUserException;
import com.assignment.order.service.OrderService.repositories.OrderRepository;
import  com.assignment.order.service.OrderService.utils.Util;

@Service
public class OrderService {
	
	@Autowired
	private TokenValidationService tokenValidationService;
	@Autowired
	private BookCommunicationService bookCommunicationService;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private OrderRepository orderRepository;
	
	public OrderResponseDTO createOrder(CreateOrderDTO createOrderDTO,String token) {
		TokenInformationDTO userInfo = getTokenInformation(token);
		BookDTO bookDetails = bookCommunicationService.retrieveBookDetailsByBookId(createOrderDTO.getBookId());
		Util.validateOrderQuantity(createOrderDTO.getQuantity(), bookDetails.getStock());
		int newStock = bookDetails.getStock() - createOrderDTO.getQuantity();
		 OrderEntity orderEntity = new OrderEntity();
		    orderEntity.setBookId(createOrderDTO.getBookId());
		    orderEntity.setBookName(bookDetails.getTitle());
		    orderEntity.setQuantity(createOrderDTO.getQuantity());
		    orderEntity.setTotalPrice(Util.calculateTotalPriceOfOrder(createOrderDTO.getQuantity(), bookDetails.getPrice()));
		    orderEntity.setOrderedAt(LocalDateTime.now());
		    orderEntity.setUserId(userInfo.getId());
		    orderEntity.setUserName(userInfo.getUserName());
		    orderEntity.setUserMail(userInfo.getEmail());

		    orderEntity = orderRepository.save(orderEntity);
		    bookCommunicationService.updateStockOfBook(createOrderDTO.getBookId(), newStock);

        orderEntity = orderRepository.save(orderEntity);
        return modelMapper.map(orderEntity, OrderResponseDTO.class);
		
	}
	
	public List<OrderResponseDTO> getAllOrders(String token){
		TokenInformationDTO userInfo = getTokenInformation(token);
		List<OrderEntity> orders = orderRepository.findByUserId(userInfo.getId());
		return orders.stream() 
		        .map(orderEntity -> modelMapper.map(orderEntity, OrderResponseDTO.class))
		        .collect(Collectors.toList()); 
	}
	
	public OrderResponseDTO getOrderById(Long orderId,String token) {
		TokenInformationDTO userInfo = getTokenInformation(token);
		OrderEntity orderEntity = orderRepository.findByIdAndUserId(orderId,userInfo.getId()).orElse(null);
		if(orderEntity == null) {
			throw new OrderNotFoundException(orderId);
		}
		return modelMapper.map(orderEntity, OrderResponseDTO.class);
	}
	
	public void deleteOrder(Long orderId, String token) {
	    TokenInformationDTO userInfo = getTokenInformation(token);
	    OrderEntity orderEntity = orderRepository.findByIdAndUserId(orderId, userInfo.getId()).orElse(null);
	    if(orderEntity == null) {
			throw new OrderNotFoundException(orderId);
		}      
	    BookDTO bookDetails = bookCommunicationService.retrieveBookDetailsByBookId(orderEntity.getBookId());
	    int updatedStock = bookDetails.getStock() + orderEntity.getQuantity();
	    bookCommunicationService.updateStockOfBook(orderEntity.getBookId(), updatedStock);
	    orderRepository.delete(orderEntity);
	}
	
	private TokenInformationDTO getTokenInformation(String token) {		
		try {
            return tokenValidationService.retrieveUserDetailsFromToken(token);           
        } catch (RuntimeException e) {
            throw new UnauthorizedUserException("User is not allowed to perform this action");
        }
	}
	

}
