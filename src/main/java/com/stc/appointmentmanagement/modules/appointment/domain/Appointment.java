package com.stc.appointmentmanagement.modules.appointment.domain;

import com.stc.appointmentmanagement.base.domain.entities.BaseEntity;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "appointment")
@EntityListeners(AuditingEntityListener.class)
public class Appointment extends BaseEntity<Long> {

    @NotNull
    @NotBlank(message = "Appointment date and time are mandatories")
    @Column(name = "appointment_date")
    private LocalDateTime appointmentDate;

    @Column(name = "cancelled_at")
    private LocalDateTime cancelledAt;

    @Column(name = "status")
    private String status;

    @Column(name = "log")
    private String log;

    @NotNull
    @NotBlank(message = "Patient Name is mandatory")
    @Column(name = "patient_name")
    private String patientName;

}
