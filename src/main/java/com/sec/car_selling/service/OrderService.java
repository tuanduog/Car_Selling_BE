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
import java.util.Optional;

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

        Customer customer = customerRepository.findByUserId(user.getId());
        if (customer == null) {
            throw new RuntimeException("Customer not found");
        }

        return paymentRepository.findAllOrders(customer.getId());
    }

    public OrderResponse getById(int id) {
        return paymentRepository.findOrdersById(id);
    }
}
