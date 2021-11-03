package com.stc.appointmentmanagement.modules.appointment.rest;


import com.stc.appointmentmanagement.modules.appointment.services.AppointmentService;
import com.stc.appointmentmanagement.modules.appointment.services.dto.AppointmentDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @GetMapping("/today")
    public List<AppointmentDto> findTodayAppointments() {
        return appointmentService.findTodayAppointments();
    }

    @GetMapping(params = {"startDate", "endDate"})
    @ApiOperation(value = "Get the resource identified by id and person")
    public List<AppointmentDto> findComingAppointments(@RequestParam(value = "startDate")
                                                       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                                       @RequestParam(value = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return appointmentService.findAllByAppointmentDateLessThanEqualAndAppointmentDateGreaterThanEqual(
                endDate, startDate);
    }

    @GetMapping(params = "patientName")
    public List<AppointmentDto> findAllByPatientName(@RequestParam(value = "patientName") String patientName) {
        return appointmentService.findAllByPatientName(patientName);
    }

    @GetMapping(params = {"patientName", "endDate"})
    public List<AppointmentDto> findAllByPatientNameAndAppointmentDate(@RequestParam("patientName") String patientName,
                                                                       @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return appointmentService.findAllByPatientNameAndAppointmentDate(patientName, endDate);
    }


    @GetMapping("/{appointmentId}")
    public AppointmentDto findById(@PathVariable("appointmentId") Long appointmentId) {
        return appointmentService.findById(appointmentId);
    }

    @PostMapping()
    public void save(@Valid @RequestBody AppointmentDto dto) {
        appointmentService.save(dto);
    }

    @PutMapping("/cancel/{appointmentId}")
    public void update(@PathVariable Long appointmentId, @RequestBody AppointmentDto dto) {
        appointmentService.update(appointmentId, dto, "cancelled");
    }

    @DeleteMapping("/{appointmentId}")
    public void delete(@PathVariable Long appointmentId) {
        appointmentService.delete(appointmentId);
    }
}
