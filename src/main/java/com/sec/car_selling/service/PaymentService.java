package com.sec.car_selling.service;

import com.sec.car_selling.dto.request.PaymentRequest;
import com.sec.car_selling.dto.response.PaymentResponse;
import com.sec.car_selling.entity.*;
import com.sec.car_selling.repository.*;
import com.sec.car_selling.util.GeneratePaymentCode;
import com.sec.car_selling.util.enums.PaymentStatus;
import com.sec.car_selling.util.enums.PaymentType;
import com.sec.car_selling.util.enums.Status;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Data
@AllArgsConstructor
public class PaymentService {

    PaymentRepository paymentRepository;

    ShowRoomRepository showRoomRepository;

    UserRepository userRepository;

    CustomerRepository customerRepository;

    InstallmentRespository installmentRespository;

    public void addPayment(PaymentRequest request){
        Payment payment = new Payment();
        payment.setCode(GeneratePaymentCode.generatePaymentCode());
        payment.setCarId(request.getCarId());
        payment.setCarName(request.getCarName());
        payment.setCarColor(request.getCarColor());
        payment.setCarVersion(request.getCarVersion());
        payment.setPrice(request.getPrice());
        payment.setStatus(Status.ACTIVE.getValue());
        payment.setPaymentStatus(PaymentStatus.PENDING.getValue());
        payment.setType(request.getPaymentType());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()){
            Customer customer = new Customer();
            customer.setUserId(user.get().getId());
            customer.setPaymentEmail(request.getPaymentEmail());
            customer.setPaymentName(request.getPaymentName());
            customer.setIdentityNumber(request.getIdentityNumber());
            customer.setPhone(request.getPhone());
            customerRepository.save(customer);
            payment.setCustomerId(customer.getId());
        }

        Showroom showroom = new Showroom();
        showroom.setCity(request.getShowRoomCity());
        showroom.setName(request.getShowRoomName());
        showRoomRepository.save(showroom);
        payment.setShowRoomId(showroom.getId());

        paymentRepository.save(payment);

        if(request.getPaymentType() == PaymentType.INSTALLMENT.getValue()){
            Installment installment = new Installment();
            installment.setPaymentId(payment.getId());
            installment.setLoanDuration(request.getInstallment().getLoanDuration());
            installment.setDownPayment(request.getInstallment().getDownPayment());
            installment.setBankId(request.getInstallment().getBankId());
            installmentRespository.save(installment);
        }
    }

    public Page<PaymentResponse> getList(Pageable pageable, String keyword, Integer paymentStatus, Integer paymentType){
        if (keyword != null) {
            keyword = "%" + keyword.trim().toLowerCase() + "%";
        }
        else {
            keyword = "%%";
        }

        return paymentRepository.findAllByKeyword(pageable, keyword, paymentStatus, paymentType);
    }

    public PaymentResponse getById(int id) {

        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy payment id = " + id));

        int type = payment.getType();

        if (type == PaymentType.INSTALLMENT.getValue()) {
            return paymentRepository.findByIdForInstallment(id);

        } else if (type == PaymentType.FULL.getValue()) {
            return paymentRepository.findByIdForFull(id);
        }

        throw new RuntimeException("Loại payment không hợp lệ");
    }

    public void cancelledById(int id){
        Payment payment = paymentRepository.findById(id).get();
        payment.setPaymentStatus(PaymentStatus.CANCELLED.getValue());
        paymentRepository.save(payment);
    }

    public void acceptedById(int id){
        Payment payment = paymentRepository.findById(id).get();
        payment.setPaymentStatus(PaymentStatus.IN_PROGRESS.getValue());
        paymentRepository.save(payment);
    }
}
