package com.sec.car_selling.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sec.car_selling.dto.request.PaymentRequest;
import com.sec.car_selling.dto.response.BaseResponse;
import com.sec.car_selling.entity.Payment;
import com.sec.car_selling.service.PaymentService;
import com.sec.car_selling.util.enums.PaymentStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.payos.PayOS;
import vn.payos.type.CheckoutResponseData;
import vn.payos.type.PaymentData;
import vn.payos.type.Webhook;
import vn.payos.type.WebhookData;

import java.math.RoundingMode;
import java.util.Map;

@RestController
@RequestMapping("/api/payment")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class PaymentController {

    PaymentService paymentService;

    PayOS payOS;

    ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/v1")
    public ResponseEntity<?> addPayment(@RequestBody PaymentRequest request){
        paymentService.addPayment(request);
        return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(), null, "add successful"));
    }

    @GetMapping("/v1")
    public ResponseEntity<?> getList(
            @PageableDefault(page = 0, size = 5) Pageable pageable,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Integer paymentStatus,
            @RequestParam(required = false) Integer paymentType
    ){
        return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(), paymentService.getList(pageable, keyword, paymentStatus, paymentType), "list successful"));
    }

    @GetMapping("/v1/{id}")
    public ResponseEntity<?> getById(@PathVariable int id){
        return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(), paymentService.getById(id), "get successful"));
    }

    @PutMapping("/cancelled/v1/{id}")
    public ResponseEntity<?> cancelledById(@PathVariable int id){
        paymentService.cancelledById(id);
        return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(), null, "cancelled successful"));
    }

    @PutMapping("/accepted/v1/{id}")
    public ResponseEntity<?> acceptedById(@PathVariable int id){
        paymentService.acceptedById(id);
        return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(), null, "accepted successful"));
    }

    @PostMapping("/v1/{id}/pay")
    public ResponseEntity<?> createPayment(@PathVariable int id) {

        Payment payment = paymentService.getIdForPayOs(id);

        if (payment.getPaymentStatus() == PaymentStatus.PAID.getValue()) {
            return ResponseEntity.badRequest().body("Payment already paid");
        }

        paymentService.updatePaymentStatus(id, PaymentStatus.IN_PROGRESS.getValue());

        int amount = payment.getPrice()
                .setScale(0, RoundingMode.DOWN)
                .intValue();
        long orderCode = Long.parseLong(String.valueOf(id));
        PaymentData paymentData = PaymentData.builder()
                .orderCode(orderCode)
                .amount(amount)
                .description("Thanh toan don hang" + payment.getCarName())
                .returnUrl("https://nontopographical-cordially-christene.ngrok-free.dev/payment-success.html")
                .cancelUrl("https://nontopographical-cordially-christene.ngrok-free.dev/payment-cancel.html")
                .build();

        try {
            CheckoutResponseData response = payOS.createPaymentLink(paymentData);

            return ResponseEntity.ok(
                    Map.of("checkoutUrl", response.getCheckoutUrl())
            );

        } catch (Exception e) {

            System.out.println("PayOS SDK error (ignored): " + e.getMessage());

            return ResponseEntity.ok(
                    Map.of(
                            "message", "Payment created, waiting for PayOS webhook",
                            "orderCode", orderCode
                    )
            );
        }
    }

    @PostMapping("/v1/payos_transfer_handler")
    public ResponseEntity<ObjectNode> handlePayOSWebhook(@RequestBody ObjectNode body) {

        ObjectNode response = objectMapper.createObjectNode();

        try {
            ObjectNode dataNode = (ObjectNode) body.get("data");

            int paymentId = dataNode.get("orderCode").asInt();
            String code = dataNode.get("code").asText();

            if ("00".equals(code)) {
                paymentService.updatePaymentStatus(
                        paymentId,
                        PaymentStatus.PAID.getValue()
                );
            } else {
                paymentService.updatePaymentStatus(
                        paymentId,
                        PaymentStatus.CANCELLED.getValue()
                );
            }

            response.put("error", 0);
            response.put("message", "Webhook processed");
            response.set("data", null);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("error", -1);
            response.put("message", e.getMessage());
            response.set("data", null);
            return ResponseEntity.ok(response);
        }
    }
}
