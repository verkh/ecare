package com.ecare.models;

import com.ecare.models.base.AbstractNamedPO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * OptionsPO is a class which describes an option represented in database.
 */
@Entity
@Table (name = "options")
@Getter
@Setter
@NoArgsConstructor
public class OptionPO extends AbstractNamedPO {
// FIXME: add equals method

    @Column(name = "price")
    private Double price;

    @Column(name = "description")
    private String description;

    @Column(name = "turn_on_price")
    private Double turnOnPrice;

    @Column(name = "deprecated")
    private boolean deprecated;

    @OneToMany(mappedBy="option", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OptionRestrictionPO> restrictions = new ArrayList<>();
}
