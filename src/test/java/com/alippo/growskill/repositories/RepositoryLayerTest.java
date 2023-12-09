package com.alippo.growskill.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import com.alippo.growskill.entities.Order;
import com.alippo.growskill.repository.OrderRepository;

@SpringBootTest
@DataJpaTest
public class RepositoryLayerTest {
	
    @Autowired
    private TestEntityManager entityManager;
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Test
    public void saveOrder() {
    	
        // given
//        User testUser = new User();
//        testUser.setUsername("testuser");
//        testUser.setEmail("testuser@example.com");
//        entityManager.persist(testUser);
//        entityManager.flush();
    	
    	Order order = new Order();
    	order.setBillingAddress(null);	
    	order.setOrderTrackingNumber("123");
    	orderRepository.save(order);
    	
        // when
    	Order result = orderRepository.findByOrderTrackingNumber("123");

        // then
        assertEquals(result.getOrderTrackingNumber(), "123");
    }

}
