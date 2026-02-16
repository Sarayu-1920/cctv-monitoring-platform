package com.cctv.monitoring.service;

import com.cctv.monitoring.entity.Device;
import com.cctv.monitoring.repository.DeviceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
//Service → Repository
@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }


    public Device createDevice(Device device) {
        device.setCreatedAt(LocalDateTime.now());
        device.setStatus("OFFLINE");  // default status
        return deviceRepository.save(device);
    }


    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }


    public Optional<Device> getDeviceById(Long id) {
        return deviceRepository.findById(id);
    }
}
