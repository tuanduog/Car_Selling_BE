package com.sec.car_selling.service;

import com.sec.car_selling.dto.request.AddEmployeeRequest;
import com.sec.car_selling.dto.request.UpdateEmployeeRequest;
import com.sec.car_selling.dto.response.StaffResponse;
import com.sec.car_selling.entity.Staff;
import com.sec.car_selling.entity.User;
import com.sec.car_selling.repository.StaffRepository;
import com.sec.car_selling.repository.UserRepository;
import com.sec.car_selling.util.PasswordGenerator;
import com.sec.car_selling.util.enums.Role;
import com.sec.car_selling.util.enums.Status;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Data
@AllArgsConstructor
public class StaffService {

    StaffRepository staffRepository;

    UserRepository userRepository;

    PasswordGenerator passwordGenerator;

    public Page<StaffResponse> getList(Pageable pageable, String keyword, Integer status, String role){
        if (keyword != null) {
            keyword = "%" + keyword.trim().toLowerCase() + "%";
        }
        else {
            keyword = "%%";
        }

        if(role.equalsIgnoreCase(Role.TEAM_LEADER.getValue())){
            return staffRepository.findAllLeaderByKeywordAndStatus(pageable, keyword, status, role);
        }
        return staffRepository.findAllStaffByKeywordAndStatus(pageable, keyword, status, role);
    }

    @Transactional
    public void addStaff(AddEmployeeRequest request){
        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setRole(request.getRole());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(passwordGenerator.generatePassword(12));
        user.setPassword(password);
        user.setStatus(Status.ACTIVE.getValue());
        userRepository.save(user);

        Staff staff = new Staff();
        staff.setUserId(user.getId());
        staff.setCode(request.getCode());
        staff.setPhone(request.getPhone());
        staff.setBirthday(request.getBirthDay());
        staff.setGender(request.getGender());
        staff.setAddress(request.getAddress());
        staff.setManagerId(request.getManagerId());
        staffRepository.save(staff);
    }

    public void updateStaff(UpdateEmployeeRequest request, int id){
        Optional<Staff> staff = staffRepository.findById(id);
        if(staff.isPresent()){
            Optional<User> user = userRepository.findById(staff.get().getUserId());
            if(user.isPresent()){
                User u = user.get();
                u.setFullName(request.getFullName());
                u.setEmail(request.getEmail());
                userRepository.save(u);
            }

            Staff st = staff.get();
            st.setCode(request.getCode());
            st.setBirthday(request.getBirthDay());
            st.setGender(request.getGender());
            st.setAddress(request.getAddress());
            st.setPhone(request.getPhone());
            staffRepository.save(st);
        }
    }

    public void deleteStaff(int id){
        Optional<Staff> staff = staffRepository.findById(id);
        if(staff.isPresent()){
            Staff st = staff.get();
            Optional<User> user = userRepository.findById(st.getUserId());

            if(user.isPresent()) {
                User u = user.get();
                u.setStatus(Status.DELETED.getValue());
                userRepository.save(u);
            }
        }
    }

    public StaffResponse getStaffById(int id){
        return staffRepository.getStaffById(id);
    }
}
