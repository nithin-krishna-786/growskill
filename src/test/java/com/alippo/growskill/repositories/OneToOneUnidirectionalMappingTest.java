package com.alippo.growskill.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import com.alippo.growskill.entities.Address;
import com.alippo.growskill.entities.Order;
import com.alippo.growskill.repository.OrderRepository;

@SpringBootTest
@DataJpaTest
public class OneToOneUnidirectionalMappingTest {

    @Autowired
    private OrderRepository orderRepository;
    

    @Test
    void saveOrderMethod(){
        Order order = new Order();
        order.setOrderTrackingNumber("1000ABC");
        order.setTotalQuantity(5);
        order.setStatus("IN PROGRESS");
        order.setTotalPrice(new BigDecimal(1000));

        Address address = new Address();
        address.setCity("Pune");
        address.setStreet("Kothrud");
        address.setState("Maharashtra");
        address.setCountry("India");
        address.setZipCode("411047");

        order.setBillingAddress(address);

        orderRepository.save(order);

    }

    @Test
    void getOrderMethod(){
        Order order = orderRepository.findById(2L).get();
        System.out.println(order.toString());
    }

    @Test
    void updateOrderMethod(){
        Order order = orderRepository.findById(1L).get();
        order.setStatus("DELIVERED");
        order.getBillingAddress().setZipCode("411087");
        orderRepository.save(order);
    }

    @Test
    void deleteOrderMethod(){
        orderRepository.deleteById(1L);
    }
    

}

    