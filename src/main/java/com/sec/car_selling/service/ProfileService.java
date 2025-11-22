package com.sec.car_selling.service;

import com.sec.car_selling.dto.request.PasswordRequest;
import com.sec.car_selling.dto.response.BaseResponse;
import com.sec.car_selling.entity.User;
import com.sec.car_selling.repository.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Data
@AllArgsConstructor
public class ProfileService {

    UserRepository userRepository;

    public void updatePassword (PasswordRequest request){
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if(!passwordEncoder.encode(request.getCurrentPassword()).matches(user.getPassword())){
            throw new IllegalArgumentException("Mật khẩu hiện tại không chính xác");
        }
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }
}
