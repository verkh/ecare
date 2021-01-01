package com.ecare.models.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * This class describes basic field for every entity in
 * database which has a 'name' column
 */
@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public class AbstractNamedPO extends AbstractPO {
    @Column(name = "name")
    private String name;
}
