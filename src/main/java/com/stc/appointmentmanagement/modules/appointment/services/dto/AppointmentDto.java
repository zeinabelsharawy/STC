package com.stc.appointmentmanagement.modules.appointment.services.dto;

import com.stc.appointmentmanagement.modules.appointment.domain.Appointment;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class AppointmentDto {

    private long id;
    private LocalDateTime appointmentDate;
    private LocalDateTime cancelledAt;
    private String status;
    private String log;
    private String patientName;

    public AppointmentDto(Appointment appointment) {
        this.id= appointment.getId();
        this.appointmentDate = appointment.getAppointmentDate();
        this.cancelledAt = appointment.getCancelledAt();
        this.status = appointment.getStatus();
        this.log = appointment.getLog();
        this.patientName = appointment.getPatientName();
    }


}
