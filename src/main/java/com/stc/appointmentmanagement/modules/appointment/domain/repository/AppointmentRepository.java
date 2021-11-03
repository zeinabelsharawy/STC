package com.stc.appointmentmanagement.modules.appointment.domain.repository;

import com.stc.appointmentmanagement.modules.appointment.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Transactional(readOnly = true)
    List<Appointment> findAllByAppointmentDateLessThanEqualAndAppointmentDateGreaterThanEqual
            (LocalDateTime endDateTime, LocalDateTime startDateTime);

    @Transactional(readOnly = true)
    List<Appointment> findAllByAppointmentDateLessThanEqual
            (LocalDateTime dateTime);

    @Transactional(readOnly = true)
    List<Appointment> findAllByAppointmentDateGreaterThanEqual
            (LocalDateTime dateTime);

    @Transactional(readOnly = true)
    List<Appointment> findAllByPatientName(String patientName);


    @Transactional(readOnly = true)
    List<Appointment> findAllByPatientNameAndAppointmentDateLessThan(String patientName, LocalDateTime dateTime);

    @Transactional(readOnly = true)
    List<Appointment> findAllByPatientNameAndAppointmentDate(String patientName, LocalDateTime dateTime);
}
