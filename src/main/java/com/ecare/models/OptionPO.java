package com.ecare.models;

import com.ecare.models.base.AbstractNamedPO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * OptionsPO is a class which describes an option represented in database.
 */
@Entity
@Table (name = "options")
@Getter
@Setter
@NoArgsConstructor
public class OptionPO extends AbstractNamedPO {
    @Column(name = "price")
    private Double price;

    @Column(name = "description")
    private String description;

    @Column(name = "turn_on_price")
    private Double turnOnPrice;
}
