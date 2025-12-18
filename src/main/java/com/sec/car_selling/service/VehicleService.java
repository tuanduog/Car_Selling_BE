package com.sec.car_selling.service;

import com.sec.car_selling.dto.request.*;
import com.sec.car_selling.dto.response.VehicleResponse;
import com.sec.car_selling.entity.*;
import com.sec.car_selling.repository.VehicleRepository;
import com.sec.car_selling.util.enums.Status;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Data
@AllArgsConstructor
public class VehicleService {

    private final FileService fileService;

    private final VehicleRepository vehicleRepository;

    public void addVehicle(VehicleRequest request, MultipartFile image) {
        Vehicle vehicle = new Vehicle();

        Optional<Vehicle> v = vehicleRepository.findByCode(request.getCode());
        if(v.isPresent()) {
            throw new RuntimeException("Xe này đã tồn tại rồi");
        }
        // Lưu ảnh nếu có
        if (image != null && !image.isEmpty()) {
            String imageUrl = fileService.uploadFile(image);
            vehicle.setImageUrl(imageUrl);
        }

        // Thông tin cơ bản
        vehicle.setCode(request.getCode());
        vehicle.setName(request.getName());
        vehicle.setDetail(request.getDetail());
        vehicle.setPrice(request.getPrice());
        vehicle.setReleaseDate(request.getReleaseDate());
        vehicle.setDescription(request.getDescription());
        vehicle.setVersion(request.getVersion());
        vehicle.setStatus(Status.ACTIVE.getValue());

        // SizeWeight
        SizeWeight sizeWeight = new SizeWeight();
        SizeWeightRequest swReq = request.getSizeWeight();
        sizeWeight.setDimension(new SpecificationItem("dimension", swReq.getDimension()));
        sizeWeight.setWheelBase(new SpecificationItem("wheelBase", swReq.getWheelBase()));
        sizeWeight.setGroundClearance(new SpecificationItem("groundClearance", swReq.getGroundClearance()));
        sizeWeight.setCurbWeight(new SpecificationItem("curbWeight", swReq.getCurbWeight()));
        sizeWeight.setSeat(new SpecificationItem("seat", swReq.getSeat()));
        sizeWeight.setTrunkVolume(new SpecificationItem("trunkVolume", swReq.getTrunkVolume()));
        vehicle.setSizeWeight(sizeWeight);

        // EngineOperation
        EngineOperationRequest eoReq = request.getEngineOperation();
        EngineOperate engine = new EngineOperate();
        engine.setMotorType(new SpecificationItem("motorType", eoReq.getMotorType()));
        engine.setMaxPower(new SpecificationItem("maxPower", eoReq.getMaxPower()));
        engine.setDriveMode(new SpecificationItem("driveMode", eoReq.getDriveMode()));
        engine.setMaxSpeed(new SpecificationItem("maxSpeed", eoReq.getMaxSpeed()));
        vehicle.setEngineOperate(engine);

        // BatteryRange
        BatteryRangeRequest brReq = request.getBatteryRange();
        BatteryRange battery = new BatteryRange();
        battery.setBatteryCapacity(new SpecificationItem("batteryCapacity", brReq.getBatteryCapacity()));
        battery.setRange(new SpecificationItem("range", brReq.getRange()));
        battery.setNormalChargeTime(new SpecificationItem("normalChargeTime", brReq.getNormalChargeTime()));
        battery.setFastChargeSupport(new SpecificationItem("fastChargeSupport", brReq.getFastChargeSupport()));
        vehicle.setBatteryRange(battery);

        // InteriorFeature
        InteriorFeatureRequest ifReq = request.getInteriorFeature();
        InteriorFeature interior = new InteriorFeature();
        interior.setCentralScreen(new SpecificationItem("centralScreen", ifReq.getCentralScreen()));
        interior.setOperatingSystem(new SpecificationItem("operatingSystem", ifReq.getOperatingSystem()));
        interior.setAirConditioning(new SpecificationItem("airConditioning", ifReq.getAirConditioning()));
        interior.setSeats(new SpecificationItem("seats", ifReq.getSeats()));
        interior.setConnectivity(new SpecificationItem("connectivity", ifReq.getConnectivity()));
        vehicle.setInteriorFeature(interior);

        // Lưu Vehicle vào DB
        vehicleRepository.save(vehicle);
    }

    public Page<VehicleResponse> getList(Pageable pageable, String keyword) {
        if (keyword != null) {
            keyword = "%" + keyword.trim().toLowerCase() + "%";
        }
        else {
            keyword = "%%";
        }

        return vehicleRepository.findAllByKeyword(pageable, keyword);
    }

    public void deleteVehicle(Integer id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        vehicle.setStatus(Status.DELETED.getValue());
        vehicleRepository.save(vehicle);
    }

    public VehicleResponse getById(Integer id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        return new VehicleResponse(
                vehicle.getId(),
                vehicle.getCode(),
                vehicle.getName(),
                vehicle.getVersion(),
                vehicle.getPrice(),
                vehicle.getReleaseDate(),
                vehicle.getDescription(),
                vehicle.getDetail(),
                vehicle.getImageUrl(),
                vehicle.getSizeWeight(),
                vehicle.getEngineOperate(),
                vehicle.getBatteryRange(),
                vehicle.getInteriorFeature()
        );
    }

    public void updateVehicle(int id, VehicleRequest request, MultipartFile image) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
        if(image != null) {
            String url = fileService.uploadFile(image);
            vehicle.setImageUrl(url);
        }
        vehicle.setCode(request.getCode());
        vehicle.setName(request.getName());
        vehicle.setDetail(request.getDetail());
        vehicle.setPrice(request.getPrice());
        vehicle.setReleaseDate(request.getReleaseDate());
        vehicle.setDescription(request.getDescription());
        vehicle.setVersion(request.getVersion());

        SizeWeightRequest swReq = request.getSizeWeight();
        if(swReq != null) {
            SizeWeight sizeWeight = vehicle.getSizeWeight();
            if(swReq.getDimension() != null) {
                sizeWeight.setDimension(new  SpecificationItem("dimension", swReq.getDimension()));
            }
            if(swReq.getWheelBase() != null) {
                sizeWeight.setWheelBase(new SpecificationItem("wheelBase", swReq.getWheelBase()));
            }
            if(swReq.getSeat() != null) {
                sizeWeight.setSeat(new SpecificationItem("seat", swReq.getSeat()));
            }
            if(swReq.getGroundClearance() != null) {
                sizeWeight.setGroundClearance(new SpecificationItem("groundClearance", swReq.getGroundClearance()));
            }
            if(swReq.getCurbWeight() != null) {
                sizeWeight.setCurbWeight(new SpecificationItem("curbWeight", swReq.getCurbWeight()));
            }
            if(swReq.getTrunkVolume() != null) {
                sizeWeight.setTrunkVolume(new SpecificationItem("trunkVolume", swReq.getTrunkVolume()));
            }
            vehicle.setSizeWeight(sizeWeight);
        }

        EngineOperationRequest engineReq = request.getEngineOperation();
        if (engineReq != null) {
            EngineOperate engine = vehicle.getEngineOperate();
            if (engine == null) engine = new EngineOperate();

            if (engineReq.getMotorType() != null)
                engine.setMotorType(new SpecificationItem("motorType", engineReq.getMotorType()));

            if (engineReq.getMaxPower() != null)
                engine.setMaxPower(new SpecificationItem("maxPower", engineReq.getMaxPower()));

            if (engineReq.getDriveMode() != null)
                engine.setDriveMode(new SpecificationItem("driveMode", engineReq.getDriveMode()));

            if (engineReq.getMaxSpeed() != null)
                engine.setMaxSpeed(new SpecificationItem("maxSpeed", engineReq.getMaxSpeed()));

            vehicle.setEngineOperate(engine);
        }

        BatteryRangeRequest brReq = request.getBatteryRange();
        if (brReq != null) {
            BatteryRange battery = vehicle.getBatteryRange();
            if (battery == null) battery = new BatteryRange();

            if (brReq.getBatteryCapacity() != null)
                battery.setBatteryCapacity(new SpecificationItem("batteryCapacity", brReq.getBatteryCapacity()));

            if (brReq.getRange() != null)
                battery.setRange(new SpecificationItem("range", brReq.getRange()));

            if (brReq.getNormalChargeTime() != null)
                battery.setNormalChargeTime(new SpecificationItem("normalChargeTime", brReq.getNormalChargeTime()));

            if (brReq.getFastChargeSupport() != null)
                battery.setFastChargeSupport(new SpecificationItem("fastChargeSupport", brReq.getFastChargeSupport()));

            vehicle.setBatteryRange(battery);
        }

        InteriorFeatureRequest ifReq = request.getInteriorFeature();
        if (ifReq != null) {
            InteriorFeature interior = vehicle.getInteriorFeature();
            if (interior == null) interior = new InteriorFeature();

            if (ifReq.getCentralScreen() != null)
                interior.setCentralScreen(new SpecificationItem("centralScreen", ifReq.getCentralScreen()));

            if (ifReq.getOperatingSystem() != null)
                interior.setOperatingSystem(new SpecificationItem("operatingSystem", ifReq.getOperatingSystem()));

            if (ifReq.getAirConditioning() != null)
                interior.setAirConditioning(new SpecificationItem("airConditioning", ifReq.getAirConditioning()));

            if (ifReq.getSeats() != null)
                interior.setSeats(new SpecificationItem("seats", ifReq.getSeats()));

            if (ifReq.getConnectivity() != null)
                interior.setConnectivity(new SpecificationItem("connectivity", ifReq.getConnectivity()));

            vehicle.setInteriorFeature(interior);
        }

        vehicleRepository.save(vehicle);
    }
}

