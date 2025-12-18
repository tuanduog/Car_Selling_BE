package com.sec.car_selling.service;

import com.sec.car_selling.dto.response.VehicleCustomerResponse;
import com.sec.car_selling.entity.Vehicle;
import com.sec.car_selling.repository.VehicleRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Data
@AllArgsConstructor
public class VehicleCustomerService {

    VehicleRepository vehicleRepository;

    public List<VehicleCustomerResponse> getAllVehicles(){
        return vehicleRepository.getAllVehicles();
    }

    public VehicleCustomerResponse getById(int id){
        Vehicle v = vehicleRepository.findById(id).orElseThrow(() -> new RuntimeException("Vehicle Not Found"));

        return VehicleCustomerResponse.builder()
                .id(v.getId())
                .imageUrl(v.getImageUrl())
                .code(v.getCode())
                .name(v.getName())
                .price(v.getPrice())
                .version(v.getVersion())
                .releaseDate(v.getReleaseDate())
                .description(v.getDescription())
                .detail(v.getDetail())
                .sizeWeight(v.getSizeWeight())
                .engineOperate(v.getEngineOperate())
                .batteryRange(v.getBatteryRange())
                .interiorFeature(v.getInteriorFeature())
                .build();
    }
}
