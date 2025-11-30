package com.sec.car_selling.service;

import com.sec.car_selling.dto.response.AccountResponse;
import com.sec.car_selling.entity.User;
import com.sec.car_selling.repository.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Data
@AllArgsConstructor
public class AccountService {

    UserRepository userRepository;

    public Page<AccountResponse> getList(Pageable pageable, String keyword, Integer status, String role) {
        if (keyword != null) {
            keyword = "%" + keyword.trim().toLowerCase() + "%";
        }
        else {
            keyword = "%%";
        }
        if (role != null && role.isBlank()) {
            role = null;
        }
        return userRepository.findAllByKeywordAndStatus(pageable, keyword, status, role);
    }

    public void update(int id){
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(u -> {
            if(u.getStatus() == 1){
                u.setStatus(0);
            } else {
                u.setStatus(1);
            }
            userRepository.save(u);
        });
    }
}
