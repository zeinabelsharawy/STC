package com.stc.appointmentmanagement.base.domain.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Persistable;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Getter
@Setter
@MappedSuperclass
@EqualsAndHashCode(of = {"id"})
public abstract class BaseEntity<ID extends Number> implements Persistable<ID> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Nullable ID id;

    @Version
    private Integer version;

    @Transient
    @Override
    public boolean isNew() {
        return null == getId();
    }

    @Override
    public String toString() {
        return String.format("Entity of type %s with id: %s", this.getClass().getName(), getId());
    }
}
