package com.stc.appointmentmanagement.modules.appointment.services.mapper;

import com.stc.appointmentmanagement.modules.appointment.domain.Appointment;
import com.stc.appointmentmanagement.modules.appointment.services.dto.AppointmentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AppointmentMapper {

    public AppointmentDto toDto(Appointment entity) {
        return new AppointmentDto(entity);
    }

    public List<AppointmentDto> toDto(List<Appointment> entities) {
        return entities.stream().map(AppointmentDto::new).collect(Collectors.toList());
    }

    public Appointment toEntity(AppointmentDto AppointmentDto) {
        return toEntity(AppointmentDto, new Appointment());
    }

    public Appointment toEntity(AppointmentDto dto, Appointment entity) {
        if (dto.getAppointmentDate() != null) {
            entity.setAppointmentDate(dto.getAppointmentDate());
        }
        entity.setCancelledAt(dto.getCancelledAt());
        entity.setStatus(dto.getStatus());
        entity.setLog(dto.getLog());
        if (dto.getPatientName() != null) {
            entity.setPatientName(dto.getPatientName());
        }
        return entity;
    }
}
