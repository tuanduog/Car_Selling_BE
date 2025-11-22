package com.sec.car_selling.service;

import com.sec.car_selling.dto.request.RegisterRequest;
import com.sec.car_selling.entity.User;
import com.sec.car_selling.repository.UserRepository;
import com.sec.car_selling.util.enums.Role;
import com.sec.car_selling.util.enums.Status;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Data
@AllArgsConstructor
public class UserService {

    UserRepository userRepository;

    PasswordEncoder passwordEncoder;

    public String register(RegisterRequest request) {
        Optional<User> user = userRepository.findByEmail(request.getEmail());
        if (user.isPresent()) {
            return "Email này đã tồn tại";
        }
        User newUser = new User();
        newUser.setFullName((request.getFullName()));
        newUser.setEmail((request.getEmail()));
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setRoleId(4);
        newUser.setStatus(Status.ACTIVE.getValue());
        userRepository.save(newUser);
        return "Đăng ký thành công";
    }
}
