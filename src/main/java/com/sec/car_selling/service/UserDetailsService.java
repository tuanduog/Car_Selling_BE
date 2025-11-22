package com.sec.car_selling.service;

import com.sec.car_selling.entity.User;
import com.sec.car_selling.repository.UserRepository;
import com.sec.car_selling.util.enums.Role;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isPresent()) {
            User u = user.get();
            String role;
            switch (u.getRoleId()){
                case 1: {
                    role = Role.ADMIN.getValue();
                    break;
                }
                case 2: {
                    role = Role.MANAGER.getValue();
                    break;
                }
                case 3: {
                    role = Role.STAFF.getValue();
                    break;
                }
                case 4: {
                    role = Role.CUSTOMER.getValue();
                    break;
                }
                default: {
                    throw new UsernameNotFoundException("Username not found");
                }
            }

            return org.springframework.security.core.userdetails.User.builder()
                    .username(u.getEmail())
                    .password(u.getPassword())
                    .roles(role)
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
    }
}
