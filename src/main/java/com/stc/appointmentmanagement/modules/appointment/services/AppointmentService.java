package com.stc.appointmentmanagement.modules.appointment.services;


import com.stc.appointmentmanagement.base.exception.AppointmentConflictsException;
import com.stc.appointmentmanagement.base.exception.InvalidInputsException;
import com.stc.appointmentmanagement.modules.appointment.domain.Appointment;
import com.stc.appointmentmanagement.modules.appointment.domain.repository.AppointmentRepository;
import com.stc.appointmentmanagement.modules.appointment.helpers.DateHelper;
import com.stc.appointmentmanagement.modules.appointment.services.dto.AppointmentDto;
import com.stc.appointmentmanagement.modules.appointment.services.mapper.AppointmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentMapper appointmentMapper;
    private final AppointmentRepository appointmentRepository;

    public List<AppointmentDto> findAll() {
        return appointmentMapper.toDto(appointmentRepository.findAll());
    }

    public AppointmentDto findById(Long id) {
        return appointmentMapper.toDto(appointmentRepository.findById(id).orElseThrow());
    }

    public List<AppointmentDto> findTodayAppointments() {
        LocalDateTime startDate = DateHelper.atStartOfDay(new Date());
        LocalDateTime endDate = DateHelper.atEndOfDay(new Date());
        System.out.println("AppointmentService:: findTodayAppointments, startDate '" + startDate + "' - endDate '" + endDate + "'");
        return appointmentMapper.toDto(appointmentRepository.
                findAllByAppointmentDateLessThanEqualAndAppointmentDateGreaterThanEqual(
                        endDate, startDate));
    }

    public List<AppointmentDto> findAllByAppointmentDateLessThanEqualAndAppointmentDateGreaterThanEqual(LocalDateTime endDateTime,
                                                                                                        LocalDateTime startDateTime) {
        return appointmentMapper.toDto(appointmentRepository.
                findAllByAppointmentDateLessThanEqualAndAppointmentDateGreaterThanEqual(endDateTime, startDateTime));
    }

    public List<AppointmentDto> findAllByAppointmentDateLessThanEqual(LocalDateTime endDateTime) {
        return appointmentMapper.toDto(appointmentRepository.
                findAllByAppointmentDateLessThanEqual(endDateTime));
    }

    public List<AppointmentDto> findAllByAppointmentDateGreaterThanEqual(LocalDateTime startDateTime) {
        return appointmentMapper.toDto(appointmentRepository.
                findAllByAppointmentDateGreaterThanEqual(startDateTime));
    }

    public List<AppointmentDto> findAllByPatientName(String patientName) {
        if (patientName.isEmpty() || patientName.isBlank()) {
            throw new InvalidInputsException("Patient Name Is Mandatory !");
        }
        return appointmentMapper.toDto(appointmentRepository.
                findAllByPatientName(patientName));
    }

    public List<AppointmentDto> findAllByPatientNameAndAppointmentDate(String patientName, LocalDateTime dateTime) {
        if (patientName.isEmpty() || patientName.isBlank()) {
            throw new InvalidInputsException("Patient Name and Date Are Mandatories !");
        }
        return appointmentMapper.toDto(appointmentRepository.
                findAllByPatientNameAndAppointmentDateLessThan(patientName, dateTime));
    }

    public void save(AppointmentDto dto) {
        System.out.println("AppointmentService:: Save, Patient Name:" + dto.getPatientName() + "  Appointment Date:" + dto.getAppointmentDate());
//        System.out.println(appointmentRepository.findAllByPatientNameAndAppointmentDate(dto.getPatientName(), dto.getAppointmentDate()));
        if (appointmentRepository.findAllByPatientNameAndAppointmentDate(dto.getPatientName(), dto.getAppointmentDate()).size() > 1) {
            throw new AppointmentConflictsException("patient already have an appointment in this time !");
        }
        Appointment appointment = appointmentMapper.toEntity(dto);
        validateInputs(appointment, "save");
        appointment.setStatus("accepted");
        System.out.println(appointment.getId());
        appointmentRepository.save(appointment);
    }

    public void update(Long id, AppointmentDto dto, String status) {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow();
        appointment = appointmentMapper.toEntity(dto, appointment);
        System.out.println(appointment.getAppointmentDate());
        appointment.setStatus(status);
        appointment.setCancelledAt(LocalDateTime.now());
        validateInputs(appointment, "cancel");
        appointmentRepository.save(appointment);
    }


    public void delete(Long id) {
        appointmentRepository.deleteById(id);
    }

    private void validateInputs(Appointment appointment, String action) {
        switch (action) {
            case "save":
                if (appointment.getAppointmentDate() == null) {
                    throw new InvalidInputsException("Appointment Date is mandatory");
                }
                if (appointment.getPatientName() == null || appointment.getPatientName().isEmpty()) {
                    throw new InvalidInputsException("Patient Name is mandatory");
                }
                break;
            case "cancel":
                if (appointment.getLog() == null || appointment.getLog().isEmpty()) {
                    throw new InvalidInputsException("Log is mandatory");
                }
                break;
        }
    }


}
