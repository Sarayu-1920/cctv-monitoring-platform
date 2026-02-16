package com.cctv.monitoring.controller;

import com.cctv.monitoring.entity.Device;
import com.cctv.monitoring.service.DeviceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
//Controller → Service
@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @PostMapping
    public Device createDevice(@RequestBody Device device) {
        return deviceService.createDevice(device);
    }

    @GetMapping
    public List<Device> getAllDevices() {
        return deviceService.getAllDevices();
    }

    @GetMapping("/{id}")
    public Optional<Device> getDeviceById(@PathVariable Long id) {
        return deviceService.getDeviceById(id);
    }
}
