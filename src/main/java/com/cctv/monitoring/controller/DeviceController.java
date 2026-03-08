package com.cctv.monitoring.controller;

import com.cctv.monitoring.entity.Device;
import com.cctv.monitoring.service.DeviceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

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
    public ResponseEntity<?> createDevice(@RequestBody Device device, HttpSession session) {

        if (!canCreate(session)) {
            return ResponseEntity.status(403).body("Access Denied");
        }

        return ResponseEntity.ok(deviceService.createDevice(device));
    }

    @GetMapping
    public List<Device> getAllDevices() {
        return deviceService.getAllDevices();
    }

    @GetMapping("/{id}")
    public Optional<Device> getDeviceById(@PathVariable Long id) {
        return deviceService.getDeviceById(id);
    }

    private boolean canCreate(HttpSession session) {
        String role = (String) session.getAttribute("role");
        return "ADMIN".equals(role) || "OPERATOR".equals(role);
    }

    private boolean canEdit(HttpSession session) {
        String role = (String) session.getAttribute("role");
        return "ADMIN".equals(role);
    }

    private boolean canDelete(HttpSession session) {
        String role = (String) session.getAttribute("role");
        return "ADMIN".equals(role);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDevice(@PathVariable Long id,
                                          @RequestBody Device device,
                                          HttpSession session) {

        if (!canEdit(session)) {
            return ResponseEntity.status(403).body("Access Denied");
        }

        return ResponseEntity.ok(deviceService.updateDevice(id, device));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDevice(@PathVariable Long id,
                                          HttpSession session) {

        if (!canDelete(session)) {
            return ResponseEntity.status(403).body("Access Denied");
        }

        deviceService.deleteDevice(id);
        return ResponseEntity.ok("Deleted");
    }
}
