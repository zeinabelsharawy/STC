package com.stc.appointmentmanagement.base.domain.interfaces;

import org.springframework.lang.Nullable;

public interface Persistable<ID> {

    @Nullable
    ID getId();

    boolean isNew();
}
