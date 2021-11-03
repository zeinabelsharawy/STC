/*
* APPOINTMENT Table
*/
CREATE TABLE appointment
(
    id                     BIGINT  NOT NULL AUTO_INCREMENT,
    appointment_date  DATETIME NOT NULL,
    cancelled_at           DATETIME ,
    status                 VARCHAR  NOT NULL,
    log                    VARCHAR  ,
    patient_name           VARCHAR   NOT NULL,
    version INTEGER NOT NULL,
    CONSTRAINT PK_APPOINTMENT PRIMARY KEY (id)
) ENGINE = InnoDB;


/*
* CREATE APPOINTMENT
*/
INSERT INTO appointment(appointment_date, status, patient_name,version)
values ('2021-11-03 08:13:30' , 'accepted', 'Ahmed',0);
