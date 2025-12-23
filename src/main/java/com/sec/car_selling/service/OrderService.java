package com.sec.car_selling.service;

import com.sec.car_selling.dto.response.OrderResponse;
import com.sec.car_selling.entity.Customer;
import com.sec.car_selling.entity.User;
import com.sec.car_selling.repository.CustomerRepository;
import com.sec.car_selling.repository.PaymentRepository;
import com.sec.car_selling.repository.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Data
@AllArgsConstructor
public class OrderService {

    UserRepository userRepository;

    CustomerRepository customerRepository;

    PaymentRepository paymentRepository;

    public List<OrderResponse> getList() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Customer> customers = customerRepository.findAllByUserId(user.getId());
        List<OrderResponse> orders = customers.stream()
                .flatMap(c -> paymentRepository.findAllOrders(c.getId()).stream())
                .toList();

        return orders;
    }

    public OrderResponse getById(int id) {
        List<OrderResponse> res = paymentRepository.findOrdersById(id);
        return res.stream()
                .findFirst()
                .orElse(null);
    }
}
